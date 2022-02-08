package com.example.adotepets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GerenciarPetshops extends AppCompatActivity {

    FloatingActionButton btnAdicionarPetshop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_petshops);

        btnAdicionarPetshop = findViewById(R.id.btn_addPetshop);

        btnAdicionarPetshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AdicionarPetshop.class);
                startActivity(it);
            }
        });
    }
}