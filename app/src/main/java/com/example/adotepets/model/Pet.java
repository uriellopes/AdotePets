package com.example.adotepets.model;

import java.io.Serializable;

public class Pet implements Serializable {

    private int id; // Valor do id do pet salvo no banco
    private String tipo; // cao, gato, hammster, etc
    private String raca; // raca dependendo do tipo ex: tipo cao -> raca -> dobberman
    private String sexo; // Macho, femea
    private String descricao; // Descricao do pet completa
    private int idade; // Idade do pet em meses
    private int tamanho; // Tamanho do pet em centimetros
    private float peso; // Peso do cachorro em kg
    private boolean vacinado; // True ou False

    public Pet(String tipo, String raca, String sexo, String descricao, int idade, int tamanho, float peso, boolean vacinado) {
        this.tipo = tipo;
        this.raca = raca;
        this.sexo = sexo;
        this.descricao = descricao;
        this.idade = idade;
        this.tamanho = tamanho;
        this.peso = peso;
        this.vacinado = vacinado;
    }

    public Pet(int id, String tipo, String raca, String sexo, String descricao, int idade, int tamanho, float peso, boolean vacinado) {
        this.id = id;
        this.tipo = tipo;
        this.raca = raca;
        this.sexo = sexo;
        this.descricao = descricao;
        this.idade = idade;
        this.tamanho = tamanho;
        this.peso = peso;
        this.vacinado = vacinado;
    }

    public int getId() {
        return this.id;
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

    public Integer getIdadeDB() {
        return this.idade;
    }

    public Integer getTamanhoDB() {
        return this.tamanho;
    }

    public Float getPesoDB() {
        return this.peso;
    }

    public Boolean getVacinadoDB() {
        return this.vacinado;
    }

    public String getIdade() {
        if( this.idade >= 12 ) {
            int anos = this.idade/12;
            int meses = this.idade%12;

            if( anos == 1 ) {
                if ( meses == 1 ) {
                    return "" + anos + " ano e" + meses + " mes";
                } else if ( meses == 0) {
                    return "" + anos + " ano";
                } else {
                    return "" + anos + " ano e " + meses + " meses";
                }
            } else {
                if ( meses == 1 ) {
                    return "" + anos + " anos e" + meses + " mes";
                } else if ( meses == 0) {
                    return "" + anos + " anos";
                } else {
                    return "" + anos + " anos e " + meses + " meses";
                }
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

    public String getVacinado() {
        if( vacinado ) {
            return "Sim";
        } else {
            return "Não";
        }
    }
}
