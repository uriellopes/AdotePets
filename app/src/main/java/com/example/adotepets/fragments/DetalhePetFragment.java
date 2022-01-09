package com.example.adotepets.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adotepets.R;
import com.example.adotepets.model.Pet;

public class DetalhePetFragment extends Fragment {

    public static final String TAG_PET = "tagPet";

    Pet pet;

    TextView detalhe_tipo, detalhe_raca, detalhe_descricao, detalhe_idade, detalhe_sexo, detalhe_tamanho, detalhe_peso;

    public DetalhePetFragment() {
        // Required empty public constructor
    }

    public static DetalhePetFragment newInstance(Pet pet) {
        DetalhePetFragment fragment = new DetalhePetFragment();
        Bundle args = new Bundle();
        args.putSerializable("pet", pet);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pet = (Pet) getArguments().getSerializable("pet");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_detalhe_pet, container, false);

        detalhe_tipo = layout.findViewById(R.id.detalhe_tipo);
        detalhe_raca = layout.findViewById(R.id.detalhe_raca);
        detalhe_descricao = layout.findViewById(R.id.detalhe_descricao);
        detalhe_sexo = layout.findViewById(R.id.detalhe_sexo);
        detalhe_tamanho = layout.findViewById(R.id.detalhe_tamanho);
        detalhe_peso = layout.findViewById(R.id.detalhe_peso);
        detalhe_idade = layout.findViewById(R.id.detalhe_idade);

        if( pet != null ) {
            detalhe_tipo.setText(pet.getTipo());
            detalhe_raca.setText(pet.getRaca());
            detalhe_descricao.setText(pet.getDescricao());
            detalhe_sexo.setText(pet.getSexo());
            detalhe_idade.setText(pet.getIdade());
            detalhe_tamanho.setText(pet.getTamanho());
            detalhe_peso.setText(pet.getPeso());
        }

        return layout;
    }
}