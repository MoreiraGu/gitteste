package ferramentas;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.utils.OptionsBuilder;
import io.github.ollama4j.utils.Options;
import GUI.TelaResposta;

public class MelhoradorDeCodigo {

    private final OllamaAPI ollamaAPI;
    private final String model;
    private final Options options;

    public MelhoradorDeCodigo(String host, String model, float temperature) {
        this.ollamaAPI = new OllamaAPI(host);
        this.ollamaAPI.setRequestTimeoutSeconds(500);
        this.model = model;

        this.options = new OptionsBuilder()
                .setTemperature(temperature)
                .build();
    }

    public void melhorarCodigo(String codigoJava, TelaResposta telaResposta) throws Exception {
        String prompt = gerarPrompt(codigoJava);
        boolean raw = false;

        OllamaResult result = ollamaAPI.generate(model, prompt, raw, options);
        String respostaFormatada = result.getResponse().replace("\n", "<br>");

        telaResposta.resposta.setText("<html><body style='width: 400px;'>" + respostaFormatada + "</body></html>");
    }

    private String gerarPrompt(String codigo) {
        return """
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
        """ + codigo;
    }
}
