package com.example.adotepets.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adotepets.R;
import com.example.adotepets.adapters.AdapterPersonalizado;
import com.example.adotepets.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class ListaPersonalizadaFragment extends Fragment {

    RecyclerView pets_lista;
    AdapterPersonalizado adapter_personalizado;
    List<Pet> pets = new ArrayList<Pet>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_lista_personalizada, container, false);

        pets_lista = layout.findViewById(R.id.listaPersonalizada);
        pets = carregarDados();

        adapter_personalizado = new AdapterPersonalizado(pets);

        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(getActivity());

        pets_lista.setLayoutManager(layout_manager);
        pets_lista.setHasFixedSize(true);
        pets_lista.setAdapter(adapter_personalizado);

        return layout;
    }

        private List<Pet> carregarDados() {

        List<Pet> pets = new ArrayList<Pet>();

        pets.add(new Pet("Cachorro", "Poodle", "Macho", "Cachorro pelo branco, encontrado em casa abandonada com a pata esquerda ferida", 36, 38, 21.3f));
        pets.add(new Pet("Cachorro", "Pinscher", "Femea", "Filhote encontrado dentro de um saco de lixo no rio", 2, 12, 1f));
        pets.add(new Pet("Gato", "Siamês", "Femea", "Gato adulto de olhos azuis cego de um olho", 27, 27, 3.8f));
        pets.add(new Pet("Passarinho", "Calopsita ", "Macho", "Recuperado em operacao policial de traficante de animais", 17, 42, 0.91f));

        return pets;
    }

    public void buscar(String s){

        if(s == null || s.trim().equals("")){
            clear();
            return;
        }

        List<Pet> filtro = new ArrayList<Pet>(pets);

        for(int i = filtro.size()-1; i >= 0; i--){
            Pet pet = filtro.get(i);

            if(!pet.getTipo().toUpperCase().contains(s.toUpperCase()) && !pet.getRaca().toUpperCase().contains(s.toUpperCase())){
                filtro.remove(pet);
            }
        }

        adapter_personalizado = new AdapterPersonalizado(filtro);
        pets_lista.setAdapter(adapter_personalizado);
    }

    public void clear(){
        adapter_personalizado = new AdapterPersonalizado(pets);
        pets_lista.setAdapter(adapter_personalizado);

    }
}