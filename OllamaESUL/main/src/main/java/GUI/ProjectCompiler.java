package GUI;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.tools.*;
import javax.swing.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ProjectCompiler {
    private final JTree jTreeArquivos;
    private final JTextArea outputArea;
    private final File outputDir;
    private String singleFileContent;

    public ProjectCompiler(JTree jTreeArquivos, JTextArea outputArea) {
        this.jTreeArquivos = jTreeArquivos;
        this.outputArea = outputArea;
        this.outputDir = new File("bin");
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }
    }

    public ProjectCompiler(JTextArea outputArea, String fileContent) {
        this.jTreeArquivos = null;
        this.outputArea = outputArea;
        this.outputDir = new File("bin");
        this.singleFileContent = fileContent;
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }
    }

    public void executarProjeto(File selectedDirectory) {
        try {
            List<File> javaFiles;

            // Se não há JTree ou está vazia, compila o conteúdo do CaixaTexto
            if (jTreeArquivos == null || jTreeArquivos.getModel().getRoot() == null) {
                if (singleFileContent == null || singleFileContent.trim().isEmpty()) {
                    outputArea.append("Nenhum código para compilar.\n");
                    return;
                }

                // Criar arquivo temporário com o conteúdo do CaixaTexto
                File tempFile = createTempJavaFile();
                javaFiles = Collections.singletonList(tempFile);
                outputArea.append("Compilando código do editor...\n");
            } else {
                // Compilar arquivos do diretório selecionado na JTree
                if (selectedDirectory == null || !selectedDirectory.isDirectory()) {
                    outputArea.append("Nenhum diretório selecionado para executar.\n");
                    return;
                }
                javaFiles = collectJavaFilesFromDirectory(selectedDirectory);
                if (javaFiles.isEmpty()) {
                    outputArea.append("Nenhum arquivo .java encontrado no diretório selecionado: " + selectedDirectory.getAbsolutePath() + "\n");
                    return;
                }

                outputArea.append("Arquivos .java encontrados no diretório selecionado:\n");
                for (File file : javaFiles) {
                    outputArea.append("- " + file.getAbsolutePath() + "\n");
                }
            }

            // Compilar os arquivos
            boolean compilationSuccess = compileFiles(javaFiles);
            if (!compilationSuccess) {
                outputArea.append("Compilação falhou. Verifique os erros acima.\n");
                return;
            }

            // Mostrar arquivos compilados
            outputArea.append("\nArquivos compilados em " + outputDir.getAbsolutePath() + ":\n");
            listCompiledFiles(outputDir, "");

            // Encontrar e executar a classe principal no diretório selecionado
            String mainClassName = findMainClass(javaFiles);
            if (mainClassName == null) {
                outputArea.append("Nenhuma classe principal encontrada no diretório selecionado. Certifique-se de que existe uma classe com método main.\n");
                return;
            }

            executeMainClass(mainClassName);

        } catch (Exception e) {
            outputArea.append("Erro durante a execução do projeto: " + e.getMessage() + "\n");
            e.printStackTrace(new PrintStream(new TextAreaOutputStream(outputArea)));
        }
    }

    private File createTempJavaFile() throws IOException {
        // Criar diretório temporário se não existir
        File tempDir = new File("temp");
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }

        // Extrair nome da classe do código fonte
        String className = extractClassName(singleFileContent);
        if (className == null) {
            throw new IOException("Não foi possível encontrar uma classe pública no código fonte");
        }

        // Criar arquivo temporário com o nome da classe
        File tempFile = new File(tempDir, className + ".java");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(singleFileContent);
        }

        return tempFile;
    }

    private String extractClassName(String sourceCode) {
        // Procura por uma declaração de classe pública
        int classIndex = sourceCode.indexOf("public class ");
        if (classIndex == -1) {
            return null;
        }

        // Encontra o início do nome da classe
        int nameStart = classIndex + "public class ".length();

        // Encontra o fim do nome da classe (espaço, chave ou parêntese)
        int nameEnd = sourceCode.length();
        for (int i = nameStart; i < sourceCode.length(); i++) {
            char c = sourceCode.charAt(i);
            if (c == ' ' || c == '{' || c == '(') {
                nameEnd = i;
                break;
            }
        }

        return sourceCode.substring(nameStart, nameEnd).trim();
    }

    private String findMainClass(List<File> javaFiles) {
        for (File file : javaFiles) {
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                if (content.contains("public static void main(String[]")) {
                    // Encontrou um arquivo com método main
                    String packageName = extractPackageName(content);
                    String className = file.getName().replace(".java", "");
                    return packageName.isEmpty() ? className : packageName + "." + className;
                }
            } catch (IOException e) {
                outputArea.append("Erro ao ler arquivo " + file.getName() + ": " + e.getMessage() + "\n");
            }
        }
        return null;
    }

    private String extractPackageName(String content) {
        // Procura por "package" no início do arquivo
        int packageStart = content.indexOf("package ");
        if (packageStart == -1) return "";

        int packageEnd = content.indexOf(";", packageStart);
        if (packageEnd == -1) return "";

        return content.substring(packageStart + 8, packageEnd).trim();
    }

    private void listCompiledFiles(File dir, String indent) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                outputArea.append(indent + "- " + file.getName() + "\n");
                if (file.isDirectory()) {
                    listCompiledFiles(file, indent + "  ");
                }
            }
        }
    }

    private List<File> collectJavaFilesFromDirectory(File directory) {
        List<File> javaFiles = new ArrayList<>();
        if (directory != null && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".java")) {
                        javaFiles.add(file);
                    }
                }
            }
        }
        return javaFiles;
    }

    private boolean compileFiles(List<File> javaFiles) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            outputArea.append("Erro: JDK não encontrado. Certifique-se de estar usando o JDK, não apenas o JRE.\n");
            return false;
        }

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

        try {
            // Configurar opções de compilação
            List<String> options = new ArrayList<>();
            options.add("-d");
            options.add(outputDir.getAbsolutePath());
            options.add("-sourcepath");
            options.add(javaFiles.get(0).getParentFile().getAbsolutePath()); // Define o sourcepath para o diretório dos arquivos

            // Criar lista de arquivos para compilação
            Iterable<? extends JavaFileObject> compilationUnits =
                    fileManager.getJavaFileObjectsFromFiles(javaFiles);

            // Executar compilação
            JavaCompiler.CompilationTask task = compiler.getTask(
                    new PrintWriter(new TextAreaOutputStream(outputArea)),
                    fileManager,
                    diagnostics,
                    options,
                    null,
                    compilationUnits
            );

            boolean success = task.call();

            // Exibir diagnósticos
            for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                outputArea.append(diagnostic.getMessage(null) + "\n");
            }

            return success;

        } catch (Exception e) {
            outputArea.append("Erro durante a compilação: " + e.getMessage() + "\n");
            e.printStackTrace(new PrintStream(new TextAreaOutputStream(outputArea)));
            return false;
        } finally {
            try {
                fileManager.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void executeMainClass(String mainClassName) throws Exception {
        // Criar classloader com o diretório de saída
        URLClassLoader classLoader = new URLClassLoader(
                new URL[] { outputDir.toURI().toURL() },
                getClass().getClassLoader()
        );

        outputArea.append("\nTentando carregar a classe: " + mainClassName + "\n");

        // Carregar e executar a classe principal
        Class<?> mainClass = classLoader.loadClass(mainClassName);
        Method mainMethod = mainClass.getMethod("main", String[].class);
        mainMethod.invoke(null, (Object) new String[0]);
    }
}