package ferramentas;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.utils.Options;
import io.github.ollama4j.utils.OptionsBuilder;



public class MelhoradorDeCodigo {
    public static void main(String[] args) throws Exception {

        String host = "http://localhost:11434/";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(500);

        String model = "qwen2.5-coder:3b";
        
        Options options =
                new OptionsBuilder()
                        .setTemperature(0.5f)
                        .build();

         String codigoParaComentar = """
            public class Continhas {
                      public static void main(String[] args) {
                          int numero1 = 10;
                          int numero2 = 5;
                          int resultado;

                          System.out.println(numero1 + numero2)
                          System.out.println(resultado);
                          System.out.println(numero1 * numero2)
                          System.out.println(numero1 / numero2);
                      }
                  }
                                    """;
        
        
        String prompt = """
        You are an expert in Java and object orientation.
        Your task:
        1. Read the code of a Java class provided.
        2. Identify and correct possible problems:
        - Logic errors;
        - Performance problems;
        - Good coding practices.

        Answer format:
        - What was changed: [describe each change made]
        - Why it was changed: [explain the reason for the change]
        - How it was changed: [explain the solution applied]
        - Code with the changes: [code]

        Rules:
        - Be clear and direct.
        - Translate the entire output to Brazilian Portuguese, only Brazilian Portuguese.
        - Summarize the information objectively.
        - Do not add extra explanations beyond what was requested.
         """ + codigoParaComentar;

        boolean raw = false;
        OllamaResult response = ollamaAPI.generate(model, prompt, raw, options);
        String resposta = response.getResponse();

        // Exibe a resposta no terminal
        System.out.println("=== RESPOSTA DO OLLAMA ===");
        System.out.println(resposta);
        System.out.println("==========================");
    }
}