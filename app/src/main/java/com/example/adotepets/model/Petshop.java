package com.example.adotepets.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Petshop implements Serializable {
    private int id;
    private String nome; // cao, gato, hammster, etc
    private String endereco;
    private ArrayList<Pet> pets;

    public Petshop(int id, String nome, String endereco, ArrayList<Pet> pets) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.pets = pets;
    }

    public Petshop(String nome, String endereco, ArrayList<Pet> pets) {
        this.nome = nome;
        this.endereco = endereco;
        this.pets = pets;
    }

    public Petshop(int id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Petshop(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }
}
