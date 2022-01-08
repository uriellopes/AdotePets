package com.example.adotepets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.adotepets.fragments.DetalhePetFragment;
import com.example.adotepets.model.Pet;

public class DetalhePetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pet);

        Intent it = getIntent();

        Pet pet = (Pet) it.getExtras().getSerializable("pet");

        DetalhePetFragment detalhe_pet =DetalhePetFragment.newInstance(pet);

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.detalhe_pet_activity, detalhe_pet, DetalhePetFragment.TAG_PET);
        transaction.commit();
    }
}