package com.example.adotepets.fragments;

import android.app.Activity;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaPersonalizadaFragment extends Fragment {

    public static final String TAG_LISTA_PERSONALIZADA = "tagListaPersonalizada";

    RecyclerView pets_lista;
    AdapterPersonalizado adapter_personalizado;
    List<Pet> pets = new ArrayList<Pet>();

    public ListaPersonalizadaFragment() {
        // Required empty public constructor
    }

    public static ListaPersonalizadaFragment newInstance(List<Pet> pets) {
        ListaPersonalizadaFragment fragment = new ListaPersonalizadaFragment();
        Bundle args = new Bundle();
        args.putSerializable("lista_pets", (Serializable) pets);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pets = (List<Pet>) getArguments().getSerializable("lista_pets");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_lista_personalizada, container, false);

        pets_lista = layout.findViewById(R.id.listaPersonalizada);

        adapter_personalizado = new AdapterPersonalizado(pets);

        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(getActivity());

        pets_lista.setLayoutManager(layout_manager);
        pets_lista.setHasFixedSize(true);
        pets_lista.setAdapter(adapter_personalizado);

        adapter_personalizado.implementItemListClick(new AdapterPersonalizado.itemListClick() {
            @Override
            public void itemListClick(int position) {
                Activity activity = getActivity();

                if(activity instanceof clickOnListItem ) {
                    clickOnListItem listener = (clickOnListItem) activity;
                    listener.clickOnListItem(pets.get(position));
                }
            }
        });

        return layout;
    }

    public interface clickOnListItem{
        public void clickOnListItem(Pet pet);
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
        adapter_personalizado.implementItemListClick(new AdapterPersonalizado.itemListClick() {
            @Override
            public void itemListClick(int position) {
                Activity activity = getActivity();

                if(activity instanceof clickOnListItem ) {
                    clickOnListItem listener = (clickOnListItem) activity;
                    listener.clickOnListItem(filtro.get(position));
                }
            }
        });
    }

    public void clear(){
        adapter_personalizado = new AdapterPersonalizado(pets);
        pets_lista.setAdapter(adapter_personalizado);
        adapter_personalizado.implementItemListClick(new AdapterPersonalizado.itemListClick() {
            @Override
            public void itemListClick(int position) {
                Activity activity = getActivity();

                if(activity instanceof clickOnListItem ) {
                    clickOnListItem listener = (clickOnListItem) activity;
                    listener.clickOnListItem(pets.get(position));
                }
            }
        });

    }
}