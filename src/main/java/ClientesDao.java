
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MASTER
 */
public class ClientesDao {
    public boolean createCliente(Clientes cliente) {
        String sql = """
            INSERT INTO clientes (nome, email, telefone)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = ConexaoSqlite.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getTelefone());

            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
            return false;
        }
    }
}
