/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author santo
 */
public class TelaHistNFP extends javax.swing.JFrame {

    
    
    /**
     * Creates new form SistemaNotaFiscal
     */
    public TelaHistNFP() {
        initComponents();
        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tabela.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // ID
        tabela.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // DataHora
        tabela.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // DataHora
        setLocationRelativeTo(null);
        preencherTabela();
        
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(modelo);
        tabela.setRowSorter(sorter);
    }

    private void preencherTabela() {
        // Definindo as colunas da tabela
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0); // Limpa os dados antigos

        // Conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/sistemas_notas"; // Altere para o nome do seu banco
        String usuario = "root"; // Seu usuário do banco
        String senha = "fatec"; // Sua senha do banco

        // Consulta SQL
        String query = "SELECT numero_nota, data_emissao, valor_impostos, cliente, descricao FROM notas_produtos";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            // Preenche a tabela com os dados do ResultSet
            while (rs.next()) {
                String numeroNota = rs.getString("numero_nota");
                String dataEmissao = rs.getString("data_emissao");
                String valorImpostos = rs.getString("valor_impostos");
                String cliente = rs.getString("cliente");
                String descricao = rs.getString("descricao");

                // Adiciona uma linha na tabela
                model.addRow(new Object[]{numeroNota, dataEmissao, valorImpostos, cliente, descricao});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados: " + ex.getMessage());
        }
    }

    private void buscarPorNumeroNota(String numeroNota) {
    DefaultTableModel model = (DefaultTableModel) tabela.getModel();
    model.setRowCount(0); // Limpa os dados antigos

    // Conexão com o banco de dados
    String url = "jdbc:mysql://localhost:3306/sistemas_notas"; // Altere para o nome do seu banco
    String usuario = "root"; // Seu usuário do banco
    String senha = "fatec"; // Sua senha do banco

    // Consulta SQL para buscar pelo número da nota
    String query = "SELECT numero_nota, data_emissao, valor_impostos, cliente, descricao FROM notas_produtos WHERE numero_nota LIKE ?"; 

    try (Connection conn = DriverManager.getConnection(url, usuario, senha);
         PreparedStatement pst = conn.prepareStatement(query)) {

        // Define o parâmetro para a busca do número da nota
        pst.setString(1, numeroNota);

        try (ResultSet rs = pst.executeQuery()) {
            // Preenche a tabela com os dados obtidos
            while (rs.next()) {
                String numeroNotaDB = rs.getString("numero_nota");
                String dataEmissao = rs.getString("data_emissao");
                String valorImpostos = rs.getString("valor_impostos");
                String cliente = rs.getString("cliente");
                String descricao = rs.getString("descricao");

                // Adiciona uma linha na tabela
                model.addRow(new Object[]{numeroNotaDB, dataEmissao, valorImpostos, cliente, descricao});
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados: " + ex.getMessage());
    }
}
    
    private void buscarPorDataIntervalo(String dataInicio, String dataFim) {
    DefaultTableModel model = (DefaultTableModel) tabela.getModel();
    model.setRowCount(0); // Limpa os dados antigos

    // Conexão com o banco de dados
    String url = "jdbc:mysql://localhost:3306/sistemas_notas"; // Altere para o nome do seu banco
    String usuario = "root"; // Seu usuário do banco
    String senha = "fatec"; // Sua senha do banco

    // Consulta SQL para buscar notas dentro do intervalo de datas
    String query = "SELECT numero_nota, data_emissao, valor_impostos, cliente, descricao FROM notas_produtos WHERE data_emissao BETWEEN ? AND ?";

    try (Connection conn = DriverManager.getConnection(url, usuario, senha);
         PreparedStatement pst = conn.prepareStatement(query)) {

        // Define os parâmetros para o intervalo de datas
        pst.setString(1, dataInicio);
        pst.setString(2, dataFim);

        try (ResultSet rs = pst.executeQuery()) {
            // Preenche a tabela com os dados obtidos
            while (rs.next()) {
                String numeroNotaDB = rs.getString("numero_nota");
                String dataEmissao = rs.getString("data_emissao");
                String valorImpostos = rs.getString("valor_impostos");
                String cliente = rs.getString("cliente");
                String descricao = rs.getString("descricao");

                // Adiciona uma linha na tabela
                model.addRow(new Object[]{numeroNotaDB, dataEmissao, valorImpostos, cliente, descricao});
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados: " + ex.getMessage());
    }
    
}
    private void deletarNotaFiscal(String numeroNota) {
    // Conexão com o banco de dados
    String url = "jdbc:mysql://localhost:3306/sistemas_notas"; // Altere para o nome do seu banco
    String usuario = "root"; // Seu usuário do banco
    String senha = "fatec"; // Sua senha do banco

    // Consulta SQL para deletar a nota fiscal
    String query = "DELETE FROM notas_produtos WHERE numero_nota = ?";

    try (Connection conn = DriverManager.getConnection(url, usuario, senha);
         PreparedStatement pst = conn.prepareStatement(query)) {

        // Define o número da nota fiscal que será deletada
        pst.setString(1, numeroNota);

        // Executa a operação de exclusão
        int rowsAffected = pst.executeUpdate();
        
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Nota fiscal deletada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Nota fiscal não encontrada.");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao deletar nota fiscal: " + ex.getMessage());
    }
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    public double calcularSomaImpostos(String campoInicio, String campoFim) {
        // Conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/sistemas_notas";
        String usuario = "root";
        String senha = "fatec";

        // Consulta SQL para buscar as notas dentro do intervalo de datas
        String query = "SELECT valor_impostos FROM notas_produtos WHERE data_emissao BETWEEN ? AND ?";

        double somaImpostos = 0.0;

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, campoInicio);
            pst.setString(2, campoFim);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String valorImpostos = rs.getString("valor_impostos");
                    if (valorImpostos != null) {
                        try {
                            somaImpostos += Double.parseDouble(valorImpostos);
                        } catch (NumberFormatException e) {
                            // Caso o valor de imposto não seja numérico, ignora
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + ex.getMessage());
        }

        return somaImpostos;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        campoBusca = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnDeletar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        campoInicio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        campoFim = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnPeriodo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Dados da Nota Fiscal Produtos");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Número da Nota", "Data de Emissão", "Valor de Impostos", "Cliente", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabela);

        campoBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoBuscaActionPerformed(evt);
            }
        });

        jLabel3.setText("Número Nota:");

        btnDeletar.setText("Deletar");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        campoInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoInicioActionPerformed(evt);
            }
        });

        jLabel4.setText("Inicío período:");

        jLabel5.setText("Fim período:");

        campoFim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoFimActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Busca por período");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnPeriodo.setText("Busca");
        btnPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeriodoActionPerformed(evt);
            }
        });

        jButton1.setText("Calcular");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(109, 109, 109))
                                            .addComponent(jLabel3))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscar))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(btnDeletar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnAtualizar)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(campoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(campoFim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel1)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPeriodo)
                            .addComponent(jButton1))
                        .addGap(6, 6, 6)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPeriodo))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDeletar)
                            .addComponent(btnAtualizar))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(campoFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoBuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoBuscaActionPerformed

    private void campoInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoInicioActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String numeroNota = campoBusca.getText();
        buscarPorNumeroNota(numeroNota);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeriodoActionPerformed
    String dataInicio = campoInicio.getText(); // Data de início do período
    String dataFim = campoFim.getText(); // Data de fim do período

    // Verifica se os campos de data não estão vazios
    if (dataInicio.isEmpty() || dataFim.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, preencha ambos os campos de data.");
    } else {
        // Chama o método para buscar as notas fiscais dentro do intervalo de datas
        buscarPorDataIntervalo(dataInicio, dataFim);
    }
    }//GEN-LAST:event_btnPeriodoActionPerformed

    private void campoFimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoFimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoFimActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
          preencherTabela();        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed

    int row = tabela.getSelectedRow();  // Obtém a linha selecionada
    if (row != -1) {  // Verifica se uma linha foi selecionada
        String numeroNota = tabela.getValueAt(row, 0).toString();  // Obtém o número da nota da linha selecionada
        int confirmacao = JOptionPane.showConfirmDialog(this, 
            "Tem certeza que deseja deletar a nota fiscal número " + numeroNota + "?", 
            "Confirmar Exclusão", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            // Deleta a nota fiscal no banco de dados
            deletarNotaFiscal(numeroNota);
            
            // Recarrega a tabela após a exclusão
            preencherTabela();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecione uma nota fiscal para excluir.");
    }

    }//GEN-LAST:event_btnDeletarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                // Obtendo as datas inseridas nos campos de texto
                String campoInicioText = campoInicio.getText();
                String campoFimText = campoFim.getText();

                // Verificando se os campos não estão vazios
                if (!campoInicioText.isEmpty() && !campoFimText.isEmpty()) {
                    // Chamando o método para calcular a soma dos impostos
                    double totalImpostos = calcularSomaImpostos(campoInicioText, campoFimText);

                    // Exibindo o total dos impostos em uma mensagem
                    JOptionPane.showMessageDialog(this, "Total de Impostos no Período: R$ " + String.format("%.2f", totalImpostos));
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, preencha ambas as datas.");
                }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaHistNFP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaHistNFP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaHistNFP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaHistNFP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaHistNFP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnPeriodo;
    private javax.swing.JTextField campoBusca;
    private javax.swing.JTextField campoFim;
    private javax.swing.JTextField campoInicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
