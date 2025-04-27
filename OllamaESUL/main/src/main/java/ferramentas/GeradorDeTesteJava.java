package ferramentas;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.utils.OptionsBuilder;
import io.github.ollama4j.utils.Options;
import GUI.TelaResposta;

public class GeradorDeTesteJava {

    private final OllamaAPI ollamaAPI;
    private final String model;
    private final Options options;

    public GeradorDeTesteJava(String host, String model, float temperature) {
        this.ollamaAPI = new OllamaAPI(host);
        this.ollamaAPI.setRequestTimeoutSeconds(120);
        this.model = model;

        this.options = new OptionsBuilder()
                .setTemperature(temperature)
                .build();
    }

    public void gerarTestes(String codigoJava, TelaResposta telaResposta) throws Exception {
        String prompt = gerarPrompt(codigoJava);
        boolean raw = false;

        OllamaResult result = ollamaAPI.generate(model, prompt, raw, options);
        String respostaFormatada = result.getResponse().replace("\n", "<br>");

        telaResposta.resposta.setText("<html><body style='width: 400px;'>" + respostaFormatada + "</body></html>");
    }

    private String gerarPrompt(String codigo) {
        return """
        You are an expert in Java code testing and JUnit.
        Your Task:
        Analyze the code below and perform two types of tests:
        1. Unit Testing: 
            Verify that each method works correctly, independently of the rest of the system.
            Identify all logic and syntax errors.
            Each method must be fully optimized to handle all relevant variables in the best possible way.
            If a method is inefficient or could be optimized (e.g., manual sorting instead of Collections.sort()), mark the test result as "false".

        2. Integration Testing: 
            Verify that all methods in the class interact and communicate correctly.
            If any method has logic errors or fails to work properly with others, return "false".
            For each method, return *"true"* if the test passed or *"false"* if the test failed.
            For the integration testing, return a single boolean indicating whether all methods work together correctly.
            It should only return "true" if all individual methods returned "true".

        Answer format:
        (Everything in parentheses is an instruction.)                         
        (Strictly follow this format. Do not add characters or symbols outside the quotes.)
            '' Teste Realizado ''
            '' Resultado do Teste (methodNameN) : (true or false) '' 
            (And so on, according to how many methods you have. (N))
            ''Resultado do Teste de Integração: (true or false)''
        Rules:
        - Be clear and direct.
        - Translate the entire output to Brazilian Portuguese, only Brazilian Portuguese. 
        - Do not add extra explanations beyond what was requested.
               """ + codigo;
    }
}
