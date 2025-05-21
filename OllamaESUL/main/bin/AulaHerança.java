/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aulaherança;

/**
 *
 * @author Rod Rodrigues
 */
public class AulaHerança {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cachorro caramelo = new Cachorro ("Caramelo", 3, "vira-lata", "Amarelo");
        Gato mimi = new Gato ("Mimi", 2, "Persa", "Branco");
        
        System.out.println(caramelo.nome + " é um" + caramelo.raca + " " + caramelo.cor);
        System.out.println(mimi.nome + " é um" + mimi.raca + " " + mimi.cor);
        
        caramelo.abanarRabo();
        mimi.arranhar();
        
        Animal[] animais = {caramelo, mimi};
        
        for (Animal a: animais){
            a.fazerSom();
        }
                
    }
    
}
