/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NotaFiscalDAO;

import Modelo.NotaFiscalProduto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ConexaoBD.ConexaoBD;
/**
 *
 * @author santo
 */

public class NotaFiscalProdutoDAO {

    // Método para salvar a nota fiscal de produto no banco de dados
    public void salvar(NotaFiscalProduto nota) {
        String sql = "INSERT INTO notas_produtos (numero_nota, data_emissao, valor_impostos, cliente, descricao) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection(); // Conecta ao banco
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a query
            
            // Configura os parâmetros da query com os dados da nota
            stmt.setInt(1, nota.getNumeroNota());
            stmt.setDate(2, Date.valueOf(nota.getDataEmissao())); // Converte LocalDate para Date
            stmt.setBigDecimal(3, nota.getValorImpostos());
            stmt.setString(4, nota.getCliente());
            stmt.setString(5, nota.getDescricao());
            
            // Executa a query de inserção
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todas as notas fiscais de produto
    public List<NotaFiscalProduto> listar() {
        List<NotaFiscalProduto> notas = new ArrayList<>();
        String sql = "SELECT * FROM notas_produtos";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                NotaFiscalProduto nota = new NotaFiscalProduto();
                nota.setId(rs.getInt("id"));
                nota.setNumeroNota(rs.getInt("numero_nota"));
                nota.setDataEmissao(rs.getDate("data_emissao").toLocalDate());
                nota.setValorImpostos(rs.getBigDecimal("valor_impostos"));
                nota.setCliente(rs.getString("cliente"));
                nota.setDescricao(rs.getString("descricao"));
                
                notas.add(nota);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return notas;
    }
}            