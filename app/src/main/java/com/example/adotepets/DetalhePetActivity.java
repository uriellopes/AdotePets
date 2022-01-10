package com.example.adotepets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.adotepets.fragments.DetalhePetFragment;
import com.example.adotepets.model.Pet;

public class DetalhePetActivity extends AppCompatActivity implements DetalhePetFragment.clickAdotarPet {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pet);

        Intent it = getIntent();

        Pet pet = (Pet) it.getExtras().getSerializable("pet");
        int position = it.getExtras().getInt("pet_position");

        DetalhePetFragment detalhe_pet =DetalhePetFragment.newInstance(pet, position);

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.detalhe_pet_activity, detalhe_pet, DetalhePetFragment.TAG_PET);
        transaction.commit();
    }

    @Override
    public void adotouPet(int position) {
        Intent i = new Intent();
        i.putExtra("pet_position", position);
        setResult(RESULT_OK, i);
        finish();
    }
}