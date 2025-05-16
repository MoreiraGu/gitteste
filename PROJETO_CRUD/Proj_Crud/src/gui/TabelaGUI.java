/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import dao.ClienteDAO;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import modelo.Cliente;

public class TabelaGUI extends javax.swing.JFrame {
    
    
    public TabelaGUI() {
        initComponents();
        carregarTabela();
        setLocationRelativeTo(null);
    }


public void carregarTabela() {
    ClienteDAO dao = new ClienteDAO();
    List<Cliente> lista = dao.getLista();

    DefaultTableModel modelo = (DefaultTableModel) tabelaGUI.getModel();
    modelo.setRowCount(0); // limpa a tabela

    for (Cliente c : lista) {
        modelo.addRow(new Object[]{
            
            c.getCodigo(),
            c.getNome(),
            c.getCpf(),
            c.getEmail(),
            c.getTel(),
            c.getEnd(),
            c.getData_nasc()
        });
    }
}
    public void buscarCliente() {
    String texto = campoBusca.getText().trim();
    if (texto.isEmpty()) return;

    ClienteDAO dao = new ClienteDAO();
    List<Cliente> lista = dao.buscarPorNomeOuCpf(texto);

    DefaultTableModel modelo = (DefaultTableModel) tabelaGUI.getModel();
    modelo.setRowCount(0);

    for (Cliente c : lista) {
        modelo.addRow(new Object[]{
            c.getCodigo(),
            c.getNome(),
            c.getCpf(),
            c.getEmail(),
            c.getTel(),
            c.getEnd(),
            c.getData_nasc()
        });
    }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaGUI = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        campoBusca = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnMostrarTabela = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabelaGUI.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "Email", "Telefone", "Endereço", "Data de nascimento"
            }
        ));
        jScrollPane1.setViewportView(tabelaGUI);

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setText("Nome/CPF :");

        campoBusca.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoBuscaActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnMostrarTabela.setText("Mostrar Todos");
        btnMostrarTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTabelaActionPerformed(evt);
            }
        });

        btnAtualizar.setText("Atualizar tabela");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        btnDeletar.setText("Deletar");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMostrarTabela)
                        .addGap(139, 139, 139)
                        .addComponent(btnAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeletar)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(btnAtualizar)
                        .addComponent(btnDeletar)
                        .addComponent(btnMostrarTabela)
                        .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarCliente();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void campoBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoBuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoBuscaActionPerformed

    private void btnMostrarTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTabelaActionPerformed
        carregarTabela();
    }//GEN-LAST:event_btnMostrarTabelaActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
       carregarTabela();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
    ClienteDAO dao = new ClienteDAO();
    dao.deletarSelecionado(tabelaGUI); // tabelaGUI é sua JTable
    carregarTabela(); // Atualiza a tabela após deletar
    }//GEN-LAST:event_btnDeletarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TabelaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TabelaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TabelaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TabelaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TabelaGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnMostrarTabela;
    private javax.swing.JTextField campoBusca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaGUI;
    // End of variables declaration//GEN-END:variables
}
