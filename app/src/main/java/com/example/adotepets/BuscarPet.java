package com.example.adotepets;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.adotepets.fragments.InfoDialogFragment;
import com.example.adotepets.fragments.ListaPersonalizadaFragment;
import com.example.adotepets.model.Pet;

import java.util.List;

public class BuscarPet extends AppCompatActivity implements SearchView.OnQueryTextListener, ListaPersonalizadaFragment.clickOnListItem {

    private static final int ADOTAR_ACTIVITY_REQUEST = 1;

    private ListaPersonalizadaFragment lista_personalizada;
    private FragmentManager fragment_manager;
    List<Pet> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_pet);

        fragment_manager = getSupportFragmentManager();

        Intent it = getIntent();

        pets = (List<Pet>) it.getExtras().getSerializable("lista_pets");

        lista_personalizada = ListaPersonalizadaFragment.newInstance(pets);

        fragment_manager = getSupportFragmentManager();

        FragmentTransaction transaction = fragment_manager.beginTransaction();

        transaction.replace(R.id.buscar_activity, lista_personalizada, ListaPersonalizadaFragment.TAG_LISTA_PERSONALIZADA);
        transaction.commit();
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
        switch (item.getItemId()) {
            case R.id.menu_info:
                InfoDialogFragment dialog = new InfoDialogFragment();
                dialog.show(fragment_manager, "INFO");
                break;
            default:
                break;
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

    public void clickOnPet(Pet pet, int position) {
        Intent it = new Intent(getApplicationContext(), DetalhePetActivity.class);
        it.putExtra("pet",pet);
        it.putExtra("pet_position", position);
        startActivityForResult(it, ADOTAR_ACTIVITY_REQUEST);
    }

    @Override
    public void clickOnListItem(Pet pet, int position) {
        clickOnPet(pet , position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode == ADOTAR_ACTIVITY_REQUEST ) {
            if ( resultCode == RESULT_OK && data != null ) {
                int position = data.getExtras().getInt("pet_position");
                Intent i = new Intent();
                i.putExtra("pet_position", position);
                setResult(RESULT_OK, i);
                finish();
            }
        }
    }
}