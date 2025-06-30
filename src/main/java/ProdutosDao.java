
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
public class ProdutosDao {
    public boolean createProduto(Produtos produto){
        String sql = "INSERT INTO produtos (nome, quantidade, preco, categoria, fornecedor) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoSqlite.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, produto.getNome());
            pstmt.setInt(2, Integer.parseInt(produto.getQuantidade()));     
            pstmt.setFloat(3, Float.parseFloat(produto.getPreco()));        
            pstmt.setInt(4, Integer.parseInt(produto.getCategoria()));       
            pstmt.setInt(5, Integer.parseInt(produto.getFornecedores())); 
            
            int linhasAfetadas = pstmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                System.out.println("Produto inserido com sucesso.");
                return true;
            } else {
                System.out.println("Nenhuma linha foi inserida.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
            return false;
        }
    }
    
    public Produtos getProduto(String nome) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE LOWER(nome) = LOWER(?)";
    
        try (Connection conn = ConexaoSqlite.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome.trim()); // remove espaços antes/depois
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Produtos produto = new Produtos(
                    rs.getString("nome"),
                    rs.getInt("quantidade"),
                    rs.getFloat("preco"),
                    rs.getInt("categoria"),
                    rs.getInt("fornecedor") // <- corrigido aqui
                );
                System.out.println("Produto encontrado: " + produto.getNome());
                return produto;
            } else {
                System.out.println("Produto não encontrado: " + nome);
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
            return null;
        }
    }
    
    public List<Produtos> getProdutos () throws SQLException {
        String sql = "SELECT * FROM produtos";
        List<Produtos> produtos = new ArrayList<>();
        
        try (Connection conn = ConexaoSqlite.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                produtos.add(new Produtos(
                        rs.getString("nome"),
                        rs.getInt("quantidade"),
                        rs.getFloat("preco"),
                        rs.getInt("categoria"),
                        rs.getInt("fornecedor")   
                ));
            }
            return produtos;

        } catch (SQLException e) {
            System.out.println("Erro ao buscar categorias: " + e.getMessage());
            return null;
        }
    }
    
    public boolean deleteProdutoPorNome(String nome) {
    String sql = "DELETE FROM produtos WHERE nome = ?";

    try (Connection conn = ConexaoSqlite.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, nome.trim());
        int affectedRows = pstmt.executeUpdate();

        if (affectedRows > 0) {
            System.out.println("Produto deletado com sucesso.");
            return true;
        } else {
            System.out.println("Nenhum produto encontrado com esse nome.");
            return false;
        }

    } catch (SQLException e) {
        System.out.println("Erro ao deletar produto: " + e.getMessage());
        return false;
    }
    }
    
    public boolean updateProdutoPorNome(String nomeOriginal, Produtos novoProduto) {
    String sql = """
        UPDATE produtos
        SET nome = ?, quantidade = ?, preco = ?, categoria = ?, fornecedor = ?
        WHERE LOWER(nome) = LOWER(?)
        """;

    try (Connection conn = ConexaoSqlite.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, novoProduto.getNome());
        pstmt.setInt(2, Integer.parseInt(novoProduto.getQuantidade()));
        pstmt.setFloat(3, Float.parseFloat(novoProduto.getPreco()));
        pstmt.setInt(4, Integer.parseInt(novoProduto.getCategoria()));
        pstmt.setInt(5, Integer.parseInt(novoProduto.getFornecedores()));
        pstmt.setString(6, nomeOriginal.trim());

        int linhasAfetadas = pstmt.executeUpdate();
        return linhasAfetadas > 0;

    } catch (SQLException e) {
        System.out.println("Erro ao atualizar produto: " + e.getMessage());
        return false;
    }
}
}
