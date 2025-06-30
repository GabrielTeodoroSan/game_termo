
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MASTER
 */
public class FornecedoresDao {
    
    public boolean createFornecedor(Fornecedores f){
        String sql = "INSERT INTO fornecedores (nome, email, telefone) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoSqlite.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, f.getName());
            pstmt.setString(2, f.getEmail());
            pstmt.setString(3, f.getTelefone());
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir categoria: " + e.getMessage());
            return false;
        }
    }
    
    public List<Fornecedores> collectFornecedoresFromDatabase() {
        List<Fornecedores> fornecedores = new ArrayList<>();

        String sql = "SELECT nome, email, telefone FROM fornecedores";

        try (Connection conn = ConexaoSqlite.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                fornecedores.add(new Fornecedores(nome, email, telefone));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar categorias: " + e.getMessage());
        }

        return fornecedores;
    }
    
    public int collectFornecedorId(String name) {
        String sql = "SELECT id FROM fornecedores WHERE nome = ?";
        
        try (Connection conn = ConexaoSqlite.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar categoria: " + e.getMessage());
        }

        return 0;
    }
}
