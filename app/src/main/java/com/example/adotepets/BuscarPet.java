package com.example.adotepets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.adotepets.fragments.InfoDialogFragment;
import com.example.adotepets.fragments.ListaPersonalizadaFragment;
import com.example.adotepets.model.Pet;

public class BuscarPet extends AppCompatActivity implements SearchView.OnQueryTextListener, ListaPersonalizadaFragment.clickOnListItem {

    private ListaPersonalizadaFragment lista_personalizada;
    private FragmentManager fragment_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_pet);

        fragment_manager = getSupportFragmentManager();
        lista_personalizada = (ListaPersonalizadaFragment) fragment_manager.findFragmentById(R.id.listaPersonalizadaFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_buscar, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_pesquisar);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Procurar...");
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.getItemId() == R.id.menu_info ) {
            InfoDialogFragment dialog = new InfoDialogFragment();
            dialog.show(fragment_manager, "INFO");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        lista_personalizada.buscar(newText);
        return false;
    }

    public void clickOnPet(Pet pet) {
        Intent it = new Intent(this, DetalhePetActivity.class);
        it.putExtra("pet",pet);
        startActivity(it);
    }

    @Override
    public void clickOnListItem(Pet pet) {
        clickOnPet(pet);
    }
}