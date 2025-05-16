package com.mycompany.main;

import GUI.*;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
        // Aplica o tema FlatLaf
        UIManager.setLookAndFeel(new FlatDarkLaf());
//
//
//        // Cor das linhas alternadas (zebradas)
        UIManager.put("Table.alternateRowColor", new Color(244,244,244)); 
//
//        // Cor da linha selecionada
//        UIManager.put("Table.selectionBackground", new Color(255, 204, 128)); // #FFCC80
//        UIManager.put("sFont", new Font("Segoe UI", Font.PLAIN, 13));
//
//        // Cor das linhas
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("Table.showVerticalLines", false);
        UIManager.put("Table.gridColor", new Color(224, 224, 224)); // Linhas sutis
//
//        // Cabeçalho centralizado
        UIManager.put("TableHeader.centerTableColumnLabels", true);
//
//        // Margem compacta entre células
        UIManager.put("Table.intercellSpacing", new Dimension(0, 1));
//
//        // Fonte moderna (opcional)
//        UIManager.put("defaultFont", new Font("Segoe UI", Font.PLAIN, 13));
//        UIManager.put("Button.arc", 10); // Bordas arredondadas (0 = quadrado)
//        UIManager.put("Button.borderWidth", 1.5); // Largura da borda
//        UIManager.put("Button.borderColor", new Color(0, 0, 0));
//        UIManager.put("TextComponent.arc", 10); // Bordas arredondadas só nos campos de texto
//        UIManager.put("Button.hoverBackground", new Color(255, 204, 128));
//        UIManager.put("Button.hoverBorderColor", new Color(0, 0, 0));
//        UIManager.put("Button.pressedBackground", new Color(255, 171, 64));
//        UIManager.put("Button.focusedBackground", new Color(255,204,128));
//        UIManager.put("Button.focusedBorderColor", new Color(0, 0, 0));
        
        
            
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Erro ao aplicar FlatLaf: " + e.getMessage());
        }
         TelaPrincipalEsul tp = new TelaPrincipalEsul();
         tp.setVisible(true);
    }
}
