/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.notafiscal;

import GUI.SistemaCadastro;
import javax.swing.SwingUtilities;
/**
 *
 * @author santo
 */

public class Notafiscal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaCadastro tela = new SistemaCadastro();
            tela.setVisible(true);
        });
    }
}