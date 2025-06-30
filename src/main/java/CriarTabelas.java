
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MASTER
 */
public class CriarTabelas {
    public static void criarTabelaProdutos() throws SQLException {
        String sql = """
                     CREATE TABLE IF NOT EXISTS produtos(
                        id INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT,
                        nome TEXT NOT NULL UNIQUE,
                        quantidade INTEGER NOT NULL,
                        preco REAL NOT NULL,
                        categoria INTEGER NOT NULL,
                        fornecedor INTEGER NOT NULL,
                        FOREIGN KEY (categoria) REFERENCES categorias(id),
                        FOREIGN KEY (fornecedor) REFERENCES fornecedores(id)
                     )
                     """;
        
        try (Connection conn = ConexaoSqlite.connect(); Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            System.out.println("Tabela produtos criada ou já existe!");
        } catch (Exception e){
            System.out.println("Erro ao criar a tabela produtos: " + e.getMessage());
        }
    }
    
    public static void criarTabelaCategorias() throws SQLException {
        String sql = """
                     CREATE TABLE IF NOT EXISTS categorias(
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nome TEXT NOT NULL
                     )
                     """;
        
        try (Connection conn = ConexaoSqlite.connect(); Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            System.out.println("Tabela categorias criada ou já existe!");
        } catch (Exception e){
            System.out.println("Erro ao criar a tabela produtos: " + e.getMessage());
        }
    }
    
    public static void criarTabelaFornecedores() throws SQLException {
        String sql = """
                     CREATE TABLE IF NOT EXISTS fornecedores(
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nome TEXT NOT NULL,
                        email TEXT NOT NULL,
                        telefone TEXT NOT NULL
                     )
                     """;
        
        try (Connection conn = ConexaoSqlite.connect(); Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            System.out.println("Tabela fornecedores criada ou já existe!");
        } catch (Exception e){
            System.out.println("Erro ao criar a tabela produtos: " + e.getMessage());
        }
    }
    
    public static void criarTabelaClientes() throws SQLException {
        String sql = """
                     CREATE TABLE IF NOT EXISTS clientes(
                        id INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT,
                        nome TEXT NOT NULL UNIQUE,
                        email TEXT NOT NULL,
                        telefone TEXT NOT NULL
                     )
                     """;
        
        try (Connection conn = ConexaoSqlite.connect(); Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            System.out.println("Tabela clientes criada ou já existe!");
        } catch (Exception e){
            System.out.println("Erro ao criar a tabela produtos: " + e.getMessage());
        }
    }
    
    public static void criarTabelaCompras() {
        String sql = """
                     CREATE TABLE IF NOT EXISTS compras(
                        id INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT,
                        codigo TEXT UNIQUE NOT NULL,
                        valor REAL NOT NULL,
                        produto INTEGER NOT NULL,
                        cliente INTEGER NOT NULL,
                        FOREIGN KEY (produto) REFERENCES produtos(id),
                        FOREIGN KEY (cliente) REFERENCES clientes(id)
                     )
                     """;
        
        try (Connection conn = ConexaoSqlite.connect(); Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            System.out.println("Tabela compras criada ou já existe!");
        } catch (Exception e){
            System.out.println("Erro ao criar a tabela produtos: " + e.getMessage());
        }
    }
    
    public static void criarTodasTabelas() {
        try {
            criarTabelaCategorias();
            criarTabelaFornecedores();
            criarTabelaClientes();
            criarTabelaProdutos();
            criarTabelaCompras();
        } catch (SQLException e){
            System.out.println("Erro ao criar as tabelas: " + e.getMessage());
        }
    }
}