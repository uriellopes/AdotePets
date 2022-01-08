package com.example.adotepets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.adotepets.fragments.InfoDialogFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;

    Button btnBuscarPet;
    Button btnAdicionarPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        btnBuscarPet = findViewById(R.id.btn_buscar);
        btnAdicionarPet = findViewById(R.id.btn_adicionar);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        InfoDialogFragment dialog = new InfoDialogFragment();
        dialog.show(manager, "INFO");
        return super.onOptionsItemSelected(item);
    }
}