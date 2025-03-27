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
            Você é um especialista em testes de código Java e em testes do JUnit. Sua tarefa é analisar o código abaixo e realizar dois tipos de testes:
            1. Teste de Unidade: Verificar se os métodos da classe funcionam corretamente, independentemente de outros componentes do sistema, incluindo
               todos tipos de erros lógicos e de sintaxe.
               Qualquer método tem que estar cem por cento otimizado, da melhor forma possivel para abranger todas as variáveis.
               Se um método for ineficiente ou puder ser otimizado (ex.: ordenação manual em vez de Collections.sort()), marque como "false".
            2. Teste de Integração: O teste de integração deve verificar se todos os métodos da classe interagem e comunicam corretamente entre si.
            Caso qualquer método falhe ou tenha erros lógicos, o teste de integração deve retornar "false".
            Para cada método, retorne *"true"* se o teste passou ou *"false"* se o teste falhou. 
            Para o teste de integração, forneça um resultado booleano que indique se a interação entre os métodos está funcionando corretamente.
            Só retornará true caso TODOS os métodos validem em true
           
               Estrutura de resposta : (Aparecer exatamente como está, sem adicionar caracteres especiais antes ou depois das aspas)
               '' Teste Realizado ''
               '' Resultado do Teste (metodoN) : (Resultado do teste (MetodoN) '' 
               (assim sucessivamente, de acordo com quantos metodos tiver (N))
               ''Resultado do Teste de Integração: true ou false''
               """ + codigo;
    }
}
