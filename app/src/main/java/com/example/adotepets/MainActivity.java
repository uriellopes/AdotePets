package com.example.adotepets;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnBuscarPet;
    Button btnAdicionarPet;
    Button btnSobre;
    AlertDialog.Builder adBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBuscarPet = findViewById(R.id.btn_buscar);
        btnAdicionarPet = findViewById(R.id.btn_adicionar);
        btnSobre = findViewById(R.id.btn_sobre);
        adBuilder = new AlertDialog.Builder(this);

        btnBuscarPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), BuscarPet.class);
                startActivity(it);
            }
        });

        btnAdicionarPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AdicionarPet.class);
                startActivity(it);
            }
        });

        btnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adBuilder
                    .setMessage(R.string.sobre_integrantes)
                    .setTitle(R.string.sobre_titulo)
                    .setPositiveButton("VER SITE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent navIt = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/uriellopes/AdotePets"));
                            startActivity(navIt);
                        }
                    })
                    .setNegativeButton("SAIR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                AlertDialog al = adBuilder.create();
                al.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}