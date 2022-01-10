package com.example.adotepets.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.adotepets.R;
import com.example.adotepets.model.Pet;

public class DetalhePetFragment extends Fragment {

    public static final String TAG_PET = "tagPet";

    Pet pet;
    int position;
    Button btnAdotar;

    TextView detalhe_tipo, detalhe_raca, detalhe_descricao, detalhe_idade, detalhe_sexo, detalhe_tamanho, detalhe_peso, detalhe_vacinado;

    public DetalhePetFragment() {
        // Required empty public constructor
    }

    public static DetalhePetFragment newInstance(Pet pet, int position) {
        DetalhePetFragment fragment = new DetalhePetFragment();
        Bundle args = new Bundle();
        args.putSerializable("pet", pet);
        args.putSerializable("pet_position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pet = (Pet) getArguments().getSerializable("pet");
        position = getArguments().getInt("pet_position");
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
        detalhe_vacinado = layout.findViewById(R.id.detalhe_vacinado);

        btnAdotar = layout.findViewById(R.id.btnAdotar);
        btnAdotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adotarPet();
            }
        });

        if( pet != null ) {
            detalhe_tipo.setText(pet.getTipo());
            detalhe_raca.setText(pet.getRaca());
            detalhe_descricao.setText(pet.getDescricao());
            detalhe_sexo.setText(pet.getSexo());
            detalhe_idade.setText(pet.getIdade());
            detalhe_tamanho.setText(pet.getTamanho());
            detalhe_peso.setText(pet.getPeso());
            detalhe_vacinado.setText(pet.getVacinado());
        }

        return layout;
    }

    public interface clickAdotarPet {
        void adotouPet(int position);
    }

    public void adotarPet() {
        Activity a = getActivity();

        if( a instanceof clickAdotarPet ) {
            clickAdotarPet l = (clickAdotarPet) a;
            l.adotouPet(position);
        }
    }
}