/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MASTER
 */
public class Clientes {
    private String nome;
    private String email;
    private String telefone;
    
    public Clientes(String nome, String email, String telefone){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getTelefone() {
        return this.telefone;
    }
}
