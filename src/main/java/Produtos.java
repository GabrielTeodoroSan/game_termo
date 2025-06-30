/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MASTER
 */
public class Produtos {
    private final String nome;
    private final int quantidade;
    private final float preco;
    private final int categoria;
    private final int fornecedores;
    
    public Produtos(String nome, int quantidade, float preco, int categoria, int fornecedores){
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.categoria = categoria;
        this.fornecedores = fornecedores;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getQuantidade() {
        return String.valueOf(quantidade);
    }
    
    public String getPreco() {
        return String.valueOf(preco);
    }
    
    public String getCategoria() {
        return String.valueOf(categoria);
    }
    
    public String getFornecedores() {
        return String.valueOf(fornecedores);
    }
}
