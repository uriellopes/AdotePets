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
import android.widget.Toast;

import com.example.adotepets.fragments.InfoDialogFragment;
import com.example.adotepets.model.Pet;
import com.example.adotepets.services.MensagemService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int ADICIONAR_ACTIVITY_REQUEST = 1;
    private static final int BUSCAR_ACTIVITY_REQUEST = 2;
    private static final int ADICIONARPETSHOP_ACTIVITY_REQUEST = 3;
    private FragmentManager manager;

    Button btnBuscarPet;
    Button btnAdicionarPet;
    Button btnGerenciarPetshops;

    List<Pet> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        btnBuscarPet = findViewById(R.id.btn_buscar);
        btnAdicionarPet = findViewById(R.id.btn_adicionar);
        btnGerenciarPetshops = findViewById(R.id.btn_gerenciarPetshops);

        pets = carregarDados();

        btnBuscarPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BUSCAR_ACTIVITY_REQUEST, BuscarPet.class);
            }
        });

        btnAdicionarPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ADICIONAR_ACTIVITY_REQUEST, AdicionarPet.class);
            }
        });

        btnGerenciarPetshops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(3,GerenciarPetshops.class); // provisoriamente i: 3
            }
        });
    }

    @Override
    protected void onPause () {
        super.onPause();
        Intent it = new Intent(getApplicationContext(), MensagemService.class);
        it.putExtra(MensagemService.MSG, selecionaMensagemNotificacao());

        startService(it);
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
                Toast.makeText(getApplicationContext(), "Pet Adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                Pet novoPet = (Pet) data.getExtras().getSerializable("novo_pet");
                pets.add(novoPet);
                startActivity(BUSCAR_ACTIVITY_REQUEST, BuscarPet.class);

            }
        }

        if ( requestCode == BUSCAR_ACTIVITY_REQUEST ) {
            if( resultCode == RESULT_OK && data != null ) {
                int position = data.getExtras().getInt("pet_position");
                pets.remove(position);
                Toast.makeText(getApplicationContext(), "Pet adotado com sucesso!", Toast.LENGTH_SHORT).show();
                startActivity(BUSCAR_ACTIVITY_REQUEST, BuscarPet.class);
            }
        }

        if ( requestCode == ADICIONARPETSHOP_ACTIVITY_REQUEST ) {
            if ( resultCode == RESULT_OK && data != null ) {
                Toast.makeText(getApplicationContext(), "Petshop Adicionado com sucesso!", Toast.LENGTH_SHORT).show();
//                startActivity(BUSCAR_ACTIVITY_REQUEST, AdicionarPetshop.class);
            }
        }
    }

    private List<Pet> carregarDados() {

        List<Pet> pets = new ArrayList<Pet>();

        pets.add(new Pet("Cachorro", "Poodle", "Macho", "Cachorro pelo branco, encontrado em casa abandonada com a pata esquerda ferida", 36, 38, 21.3f, true));
        pets.add(new Pet("Cachorro", "Pinscher", "Femea", "Filhote encontrado dentro de um saco de lixo no rio", 2, 12, 1f, false));
        pets.add(new Pet("Gato", "Siam??s", "Femea", "Gato adulto de olhos azuis cego de um olho", 27, 27, 3.8f, true));
        pets.add(new Pet("Passarinho", "Calopsita ", "Macho", "Recuperado em operacao policial de traficante de animais", 17, 42, 0.91f, false));
        pets.add(new Pet("Macaco", "Prego", "Macho", "Atropelado na avenida e muito machucado", 13, 37, 3.6f, false));
        pets.add(new Pet("Gato", "sphynx", "Macho", "Dono vai se mudar para apertamento onde n??o ?? permitido animais e n??o pode mais ficar com ele ", 28, 34, 3.4f, true));
        pets.add(new Pet("Arara", "Azul ", "Femea", "Captura por ca??adores ilegais e recuperado pelo IBAMA ", 15, 48, 2.37f, false));

        return pets;
    }

    public void startActivity(int i, Class c) {
        Intent it = new Intent(getApplicationContext(), c);
        it.putExtra("lista_pets", (Serializable) pets);
        startActivityForResult(it, i);
    }

    public String selecionaMensagemNotificacao(){
        int num = new Random().nextInt(5);
        String frases[] = {
                "Com meu pet, eu n??o aprendi apenas como ?? ter um animal de estima????o, e sim a ter um amigo de verdade! Adote um pet!",
                "Os pets dividem conosco o privil??gio de existir! Adote um pet!",
                "Conviver com animais de estima????o ?? um dos maiores privil??gios! Adote um pet!",
                "A felicidade do seu animal de estima????o ?? sua tamb??m! Adote um pet!",
                "Animais de estima????o precisam de aten????o e carinho. N??o confunda com bichinho de pel??cia. Adote um pet!"
        };
        return frases[num];
    }
}