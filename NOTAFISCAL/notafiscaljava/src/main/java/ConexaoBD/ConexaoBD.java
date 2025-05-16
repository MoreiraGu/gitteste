package ConexaoBD;
        
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author santo
 */
public class ConexaoBD {
     private static final String URL = "jdbc:mysql://localhost:3306/sistemas_notas";
    private static final String USER = "root"; // O nome do usuário que você criou
    private static final String PASSWORD = "fatec"; // A senha do usuário

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados", e);
        }
    }
}
