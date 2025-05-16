/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import factory.ConnectionFactory;
import gui.TabelaGUI;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import modelo.Cliente;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author santo
 */
public class ClienteDAO {
    private Connection connection;
    
    public ClienteDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Cliente cliente){
        String sql = "INSERT INTO cliente(cli_nome, cli_cpf, cli_email, cli_tel, cli_end, cli_data_nasc) Values(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            // String id_aux=Integer.toString(cliente.getId());
             stmt.setString(1, cliente.getNome());
             stmt.setString(2, cliente.getCpf());
             stmt.setString(3, cliente.getEmail());
             stmt.setString(4, cliente.getTel());
             stmt.setString(5, cliente.getEnd());
             if (cliente.getData_nasc() != null){
              stmt.setDate(6, java.sql.Date.valueOf(cliente.getData_nasc()));
        } else {
                stmt.setNull(6, java.sql.Types.DATE);
        }
             stmt.execute();
             stmt.close();
        }
        catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    public List<Cliente> getLista() {
    List<Cliente> lista = new ArrayList<>();
    String sql = "SELECT * FROM cliente";

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Cliente c = new Cliente();
            c.setCodigo(rs.getInt("cli_id"));
            c.setNome(rs.getString("cli_nome"));
            c.setCpf(rs.getString("cli_cpf"));
            c.setEmail(rs.getString("cli_email"));
            c.setTel(rs.getString("cli_tel"));
            c.setEnd(rs.getString("cli_end"));
            c.setData_nasc(rs.getDate("cli_data_nasc").toLocalDate());

            lista.add(c);
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao listar clientes: " + e.getMessage(), e);
    }

    return lista;
}
    public List<Cliente> buscarPorNomeOuCpf(String busca) {
    List<Cliente> clientes = new ArrayList<>();
    String sql = "SELECT * FROM cliente WHERE cli_nome LIKE ? OR cli_cpf LIKE ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, "%" + busca + "%");
        stmt.setString(2, "%" + busca + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setCodigo(rs.getInt("cli_id"));
            cliente.setNome(rs.getString("cli_nome"));
            cliente.setCpf(rs.getString("cli_cpf"));
            cliente.setEmail(rs.getString("cli_email"));
            cliente.setTel(rs.getString("cli_tel"));
            cliente.setEnd(rs.getString("cli_end"));
            cliente.setData_nasc(rs.getDate("cli_data_nasc").toLocalDate());
            clientes.add(cliente);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return clientes;
}
    
     public Cliente buscarPorCpf(String cpf) {
        Cliente cliente = null;
        String sql = "SELECT * FROM cliente WHERE cli_cpf = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTel(rs.getString("tel"));
                cliente.setEnd(rs.getString("end"));
                cliente.setData_nasc(rs.getDate("data_nasc").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cliente;
    }
    
     // Método para alterar os dados do cliente
   public void alterarCliente(String cpf, String nome, String email, String tel, String end) {
    String sql = "UPDATE cliente SET cli_nome = ?, cli_email = ?, cli_tel = ?, cli_end = ? WHERE cli_cpf = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        // Definir os parâmetros para o UPDATE
        stmt.setString(1, nome);
        stmt.setString(2, email);
        stmt.setString(3, tel);
        stmt.setString(4, end);
        stmt.setString(5, cpf);  // Alterar com base no CPF

        // Executa a atualização
        stmt.executeUpdate();
        
        // Exibe a confirmação de sucesso
        JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e.getMessage());
        e.printStackTrace();
    }
}
   
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET cli_nome = ?, cli_email = ?, cli_tel = ?, cli_end = ?, cli_data_nasc = ? WHERE cli_cpf = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTel());
            stmt.setString(4, cliente.getEnd());
            stmt.setDate(5, Date.valueOf(cliente.getData_nasc()));  // Conversão para java.sql.Date
            stmt.setString(6, cliente.getCpf());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obter a conexão com o banco de dados
    private Connection getConnection() throws SQLException {
        // Retorne a sua conexão com o banco de dados aqui.
        return DriverManager.getConnection("jdbc:mysql://localhost/vendas", "root", "fatec");
    }

    
   public void deletarCliente(int codigoCliente) {
    String sql = "DELETE FROM cliente WHERE cli_id = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        // Verifique se o código está correto
        System.out.println("Tentando excluir cliente com ID: " + codigoCliente);

        stmt.setInt(1, codigoCliente);
        
        // Log da execução do DELETE
        int rowsAffected = stmt.executeUpdate();
        System.out.println("Linhas afetadas: " + rowsAffected);

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado para exclusão.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao deletar cliente: " + e.getMessage());
        e.printStackTrace();
    }
}

public void deletarSelecionado(JTable tabelaGUI) {
    // Pega o índice da linha selecionada
    int linhaSelecionada = tabelaGUI.getSelectedRow();
    if (linhaSelecionada != -1) {
    int codigoCliente = (int) tabelaGUI.getValueAt(linhaSelecionada, 0); // Supondo que o ID esteja na primeira coluna
    System.out.println("Código do cliente: " + codigoCliente);  // Verifique no console se o valor está correto
} else {
    JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada.");
}
    // Verifica se alguma linha foi selecionada
    if (linhaSelecionada != -1) {
        // Confirmação para deletar o cliente
        int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este cliente?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            // Pega o código do cliente para deletar do banco de dados
            int codigoCliente = (int) tabelaGUI.getValueAt(linhaSelecionada, 0); // Supondo que o código do cliente esteja na primeira coluna

            // Log do código do cliente que será excluído
            System.out.println("Excluindo cliente com código: " + codigoCliente);

            // Deleta o cliente do banco de dados
            ClienteDAO dao = new ClienteDAO();
            dao.deletarCliente(codigoCliente);

            // Remove a linha da interface
            DefaultTableModel modelo = (DefaultTableModel) tabelaGUI.getModel();
            modelo.removeRow(linhaSelecionada);

            // Atualiza a tabela para refletir a exclusão
            TabelaGUI tabelaGui = (TabelaGUI) tabelaGUI.getTopLevelAncestor();  // Obtém a instância da janela principal (caso a TabelaGUI seja um componente dentro de uma janela)
            if (tabelaGui != null) {
                tabelaGui.carregarTabela();  // Atualiza a tabela
            }
        }
    } else {
        // Caso nenhuma linha seja selecionada
        JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada.");
    }
}

}

