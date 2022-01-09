package com.example.adotepets;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.adotepets.fragments.InfoDialogFragment;
import com.example.adotepets.model.Pet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADICIONAR_ACTIVITY_REQUEST = 1;
    private static final int BUSCAR_ACTIVITY_REQUEST = 2;
    private FragmentManager manager;

    Button btnBuscarPet;
    Button btnAdicionarPet;
    Button btnTestePet;

    List<Pet> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        btnBuscarPet = findViewById(R.id.btn_buscar);
        btnAdicionarPet = findViewById(R.id.btn_adicionar);
        btnTestePet = findViewById(R.id.btn_teste);

        pets = carregarDados();

        btnBuscarPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), BuscarPet.class);
                it.putExtra("lista_pets", (Serializable) pets);
                startActivityForResult(it, BUSCAR_ACTIVITY_REQUEST);
            }
        });

        btnAdicionarPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AdicionarPet.class);
                startActivityForResult(it, ADICIONAR_ACTIVITY_REQUEST);
            }
        });

        btnTestePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), TesteImagem.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode == ADICIONAR_ACTIVITY_REQUEST ) {
            if ( resultCode == RESULT_OK && data != null ) {

                // MANIPULAR DATA RETURNADO PELA ACTIVITY ADICIONAR PET

            }
        }

        if ( requestCode == BUSCAR_ACTIVITY_REQUEST ) {
            if( resultCode == RESULT_OK && data != null ) {

                // MANIPULAR DATA RETURNADO PELA ACTIVITY BUSCAR PET

                String s = data.getStringExtra("keyName");
                btnBuscarPet.setText(s);

            }
        }
    }

    private List<Pet> carregarDados() {

        List<Pet> pets = new ArrayList<Pet>();

        pets.add(new Pet("Cachorro", "Poodle", "Macho", "Cachorro pelo branco, encontrado em casa abandonada com a pata esquerda ferida", 36, 38, 21.3f));
        pets.add(new Pet("Cachorro", "Pinscher", "Femea", "Filhote encontrado dentro de um saco de lixo no rio", 2, 12, 1f));
        pets.add(new Pet("Gato", "Siamês", "Femea", "Gato adulto de olhos azuis cego de um olho", 27, 27, 3.8f));
        pets.add(new Pet("Passarinho", "Calopsita ", "Macho", "Recuperado em operacao policial de traficante de animais", 17, 42, 0.91f));

        return pets;
    }
}