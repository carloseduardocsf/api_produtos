package com.crud.produtos.dto;


import com.crud.produtos.entity.Produto;

public class ProdutoDto {

    private String nome;
    private Double valor;
    private String descricao;

    public ProdutoDto(Produto entity) {
        this.nome = entity.getNome();
        this.valor = entity.getValor();
        this.descricao = entity.getDescricao();
    }

    public ProdutoDto(String nome, Double valor, String descricao) {
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ProdutoDto{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
