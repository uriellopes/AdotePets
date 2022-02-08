package com.example.adotepets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.adotepets.fragments.AdcionarPetFragment;
import com.example.adotepets.fragments.AdicionarPetshopFragment;
import com.example.adotepets.fragments.InfoDialogFragment;
import com.example.adotepets.model.Pet;
import com.example.adotepets.model.Petshop;

public class AdicionarPetshop extends AppCompatActivity implements AdicionarPetshopFragment.clickAdicionarPetshop{

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_petshop);

        manager = getSupportFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.getItemId() == R.id.menu_info ) {
            InfoDialogFragment dialog = new InfoDialogFragment();
            dialog.show(manager, "INFO");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void adicionouPetshop(Petshop petshop) {
        Intent i = new Intent();
        i.putExtra("novo_petshop", petshop);
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}