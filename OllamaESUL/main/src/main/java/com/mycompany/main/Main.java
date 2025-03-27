import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.types.OllamaModelType;
import io.github.ollama4j.utils.OptionsBuilder;
import io.github.ollama4j.utils.PromptBuilder;
import io.github.ollama4j.utils.Options;


public class Main {
    public static void main(String[] args) throws Exception {

        String host = "http://localhost:11434/";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(120);

        String model = "llama3.2";
        
        Options options =
                new OptionsBuilder()
                        .setTemperature(0.2f)
                        .build();

        String codigoParaComentar = """
                                       public double calculateHypotenuse(int a, int b) {
                                               return Math.sqrt(power(a, 2) + power(b, 2));
                                           }
                                       
                                           public double calculateAreaCircle(int radius) {
                                               if (radius < 0) {
                                                   throw new IllegalArgumentException("O raio não pode ser negativo");
                                               }
                                               return Math.PI * power(radius, 2);
                                           }
                                       
                                           public double calculatePerimeterCircle(int radius) {
                                               if (radius < 0) {
                                                   throw new IllegalArgumentException("O raio não pode ser negativo");
                                               }
                                               return 2 * Math.PI * radius;
                                           }
                                       
                                           public double calculateAreaRectangle(int length, int width) {
                                               return length * width;
                                           }
                                       
                                           public double calculatePerimeterRectangle(int length, int width) {
                                               return 2 * (length + width);
                                           }
                                    """;
        
        
        
        String prompt = """
                        Analise o seguinte código Java:
                        %s
                        Pense sobre cada metodo                        
                        True quando o metodo estiver totalmente correto
                        False quando o metodo estiver errado
                        Me devolva uma lista igual essa abaixo
                        Resultado do Teste(nome do metodo): boolean True or False
                        com os resultados dos testes um embaixo do outro
                        não quero explicações
                                       Exemplo:
                        Teste Realizado
                        Resultado do Teste (add): true
                        Resultado do Teste (subtract): false
                      
                        
                                                                                                 
         """.formatted(codigoParaComentar);

        boolean raw = false;
        OllamaResult response = ollamaAPI.generate(model, prompt, raw, options);
        System.out.println(response.getResponse());
    }
}