
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MASTER
 */
public class ConexaoSqlite {
    
    public static Connection connect(){
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:banco.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e){
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
        return conn;
    }
}
