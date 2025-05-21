package helloworld;
import javax.swing.*;
public class HelloWorld {
    static void Nome(){
        String nome = "";
        nome = JOptionPane.showInputDialog(null, "Insira seu nome: ");
        JOptionPane.showMessageDialog(null, "Seu nome é: " + nome);
    }
    static void AreaRetangulo(){
        float altura = 0;
        float largura = 0;
        altura = Float.parseFloat(JOptionPane.showInputDialog("Insira a altura do retangulo: "));
        largura = Float.parseFloat(JOptionPane.showInputDialog("Insira a largura do retangulo: "));
        JOptionPane.showMessageDialog(null,"A área do retângulo é: " + altura * largura);
    }
    
    public static void main(String[] args) {
        AreaRetangulo();
    }
}
