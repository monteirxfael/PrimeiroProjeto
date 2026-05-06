package com.example.primeiroprojeto;

import java.io.Serializable;

public class Chamado implements java.io.Serializable {
    private String titulo;
    private String data;
    private String descricao;
    private String local;
    private String tipo; // TI ou Infraestrutura
    private String status; // Aberto, Em Atendimento, Concluído
    private String solucao;

    public Chamado(String titulo, String data, String descricao, String local, String tipo) {
        this.titulo = titulo;
        this.data = data;
        this.descricao = descricao;
        this.local = local;
        this.tipo = tipo;
        this.status = "Aberto"; // Status inicial padrão
        this.solucao = "";
    }

    // Getters e Setters (Essenciais para a edição e filtros)
    public String getTitulo() { return titulo; }
    public String getData() { return data; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public void setSolucao(String solucao) { this.solucao = solucao; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getSolucao() { return solucao; }
}
