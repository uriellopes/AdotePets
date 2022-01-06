package com.example.adotepets.model;

import android.widget.ImageView;

import java.io.Serializable;

public class Pet implements Serializable {

    private String tipo; // cao, gato, hammster, etc
    private String raca; // raca dependendo do tipo ex: tipo cao -> raca -> dobberman
    private float idade;
    private ImageView foto;

    private enum sexo {
        MACHO, FEMEA, INDEFINIDO
    };
    private enum situacao {
        DOADO, RESGATADO, EM_RECUPERACAO
    }; // doado pelo antigo dono, resgatado, em recuperacao, etc


    public Pet() {
    }

    public Pet(String tipo, String raca, float idade, ImageView foto) {
        this.tipo = tipo;
        this.raca = raca;
        this.idade = idade;
        this.foto = foto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public float getIdade() {
        return idade;
    }

    public void setIdade(float idade) {
        this.idade = idade;
    }

    public ImageView getFoto() {
        return foto;
    }

    public void setFoto(ImageView foto) {
        this.foto = foto;
    }
}
