/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interfaceapi;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Rod Rodrigues
 */
public class InterfaceAPI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Cria uma GRAPHIC USER INTERFACE, seta visivel, tamanho e titulo
        JFrame guidasul = new JFrame();                            
        guidasul.setVisible(true);                                 
        guidasul.setSize(400,200);    
        guidasul.setTitle("GUI TESTE GRUPO SUL");    
        //Encerra Aplicação ao fechar GUI
        guidasul.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Define um tipo de Layout pra GUI, pode ser: BorderLayout, FlowLayout, GridLayout, CardLayout
        guidasul.setLayout(new FlowLayout());
        
        
        //Criando uma caixa de texto, setando borda interna, inserindo texto e adicionando à JFrame
        JLabel caixa = new JLabel();
        caixa.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        caixa.setText("INSIRA ABAIXO SEU CÓDIGO PARA ANÁLISE");
        guidasul.add(caixa);
        
        //Criando caixa de texto para inserção de código,
        JTextField caixaTexto = new JTextField(200);
        caixaTexto.setVisible(true);
        caixaTexto.setSize(100,100);
        caixaTexto.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        guidasul.add(caixaTexto);
        
        //criando uma borda para uma Label (não utilizado)
        //Border borda = BorderFactory.createLineBorder(Color.red,2);
        //caixa.setBorder(borda);
        
        //criando botão com ação
        JButton botaorodar = new JButton("Iniciar análise de Código");
        guidasul.add(botaorodar);
        
        //Painel de resposta, criando, setando posição, adicionando label e textfield.
        JPanel painelresposta = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        painelresposta.add(new JLabel("Resultado de análise de código:"));
        painelresposta.add(new JTextField("RESPOSTA DA IA SERÁ INSERIDA NESSE CAMPO"));
         // Adicionar ação ao botão
        botaorodar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para abrir o painel
                JFrame janelaPainel = new JFrame("Painel");
                janelaPainel.add(painelresposta);
                janelaPainel.setSize(400, 200);
                janelaPainel.getContentPane().setBackground(new Color(0,0,0));
                janelaPainel.setVisible(true);
            }
        });
        guidasul.getContentPane().setBackground(new Color(80,80,95));
        
    }
    
}
