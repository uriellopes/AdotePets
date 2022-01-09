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

    public String getTipo() {
        return this.tipo;
    };

    public String getRaca() {
        return this.raca;
    }

    public String getDescricao() { return this.descricao; }

    public String getSexo() {
        return this.sexo;
    }

    public String getIdade() {
        if( this.idade > 12 ) {
            int anos = this.idade/12;
            int meses = this.idade%12;
            if ( meses > 0 ) {
                return "" + anos + " anos e " + meses + " meses";
            } else {
                return "" + anos + " anos";
            }
        } else {
            return "" + this.idade + " meses";
        }
    }

    public String getTamanho() {
        if( this.tamanho > 100 ) {
            int metro = this.tamanho/100;
            int cm = this.tamanho%100;
            if( cm > 0 ) {
                return "" + metro + "." + cm + "m";
            } else {
                return "" + metro + "m";
            }
        } else {
            return "" + this.tamanho + "cm";
        }
    }

    public String getPeso() {
        if( this.peso < 1 ) {
            int g = (int) (this.peso * 1000);
            return "" + g + "g";
        } else {
            return "" + this.peso + "kg";
        }
    }
}
