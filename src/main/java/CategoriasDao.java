
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MASTER
 */
public class CategoriasDao {
    public List<Categoria> collectCategoriasFromDatabase() {
        List<Categoria> categorias = new ArrayList<>();

        String sql = "SELECT nome FROM categorias";

        try (Connection conn = ConexaoSqlite.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                categorias.add(new Categoria(nome));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar categorias: " + e.getMessage());
        }

        return categorias;
    }
    
    public boolean createCategoria(Categoria categoria){
        String sql = "INSERT INTO categorias (nome) VALUES (?)";
        
        try (Connection conn = ConexaoSqlite.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, categoria.getNome());
            pstmt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir categoria: " + e.getMessage());
            return false;
        }
    }

    public Categoria buscarPorNome(String nome) {
        String sql = "SELECT nome FROM categorias WHERE nome = ?";

        try (Connection conn = ConexaoSqlite.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Categoria(rs.getString("nome"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar categoria: " + e.getMessage());
        }

        return null;
    }
    
    public int buscarCategoriaId(String nome) {
        String sql = "SELECT id FROM categorias WHERE LOWER(nome) = LOWER(?)";
    
        try (Connection conn = ConexaoSqlite.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                System.out.println("ID da categoria '" + nome + "': " + id);
                return id;
            } else {
                System.out.println("Categoria não encontrada: " + nome);
                return 0;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar categoria: " + e.getMessage());
            return 0;
        }
    }
}
