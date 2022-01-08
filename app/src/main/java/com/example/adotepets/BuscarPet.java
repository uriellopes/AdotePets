package com.example.adotepets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.adotepets.fragments.InfoDialogFragment;

public class BuscarPet extends AppCompatActivity {

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_buscar_pet);

        manager = getSupportFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buscar, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_pesquisar);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Procurar...");

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
}