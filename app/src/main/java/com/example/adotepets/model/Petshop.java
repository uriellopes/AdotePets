package com.example.adotepets.model;

import java.io.Serializable;

public class Petshop implements Serializable {
    private int id;
    private String nome;
    private String cep;
    private String logradouro;
    private int numero;
    private String bairro;
    private String uf;

    public Petshop (int id, String nome, String cep, String logradouro, int numero, String bairro, String uf) {
        this.id = id;
        this.nome = nome;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.uf = uf;
    }

    public Petshop (String nome, String cep, String logradouro, int numero, String bairro, String uf) {
        this.nome = nome;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.uf = uf;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCep() {
        return this.cep;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getBairro() {
        return this.bairro;
    }

    public String getUf() {
        return this.uf;
    }
}
