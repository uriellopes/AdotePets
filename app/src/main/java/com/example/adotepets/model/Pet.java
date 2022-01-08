package com.example.adotepets.model;

import java.io.Serializable;

public class Pet implements Serializable {

    private String tipo; // cao, gato, hammster, etc
    private String raca; // raca dependendo do tipo ex: tipo cao -> raca -> dobberman
    private String sexo; // Macho, femea
    private String descricao; // Descricao do pet completa
    private int idade; // Idade do pet em meses
    private int tamanho; // Tamanho do pet em centimetros
    private float peso; // Peso do cachorro em g

    public Pet(String tipo, String raca, String sexo, String descricao, int idade, int tamanho, float peso) {
        this.tipo = tipo;
        this.raca = raca;
        this.sexo = sexo;
        this.descricao = descricao;
        this.idade = idade;
        this.tamanho = tamanho;
        this.peso = peso;
    }
}
