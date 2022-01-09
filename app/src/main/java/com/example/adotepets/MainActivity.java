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

public class MainActivity extends AppCompatActivity {

    private static final int ADICIONAR_ACTIVITY_REQUEST = 1;
    private static final int BUSCAR_ACTIVITY_REQUEST = 2;
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

            }
        }
    }
}