package com.example.adotepets.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adotepets.R;
import com.example.adotepets.model.Pet;

import java.util.List;

public class AdapterPersonalizado extends RecyclerView.Adapter<AdapterPersonalizado.myViewHolder> {

    private List<Pet> lista_pets;
    private itemListClick listener;

    public AdapterPersonalizado(List<Pet> pets) {
        this.lista_pets = pets;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_personalizada, parent, false);
        return new myViewHolder(elemento);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Pet pet = lista_pets.get(position);

        holder.pet_tipo.setText(pet.getTipo());
        holder.pet_raca.setText(pet.getRaca());
        holder.pet_sexo.setText(pet.getSexo());
        holder.pet_idade.setText(pet.getIdade());

    }

    @Override
    public int getItemCount() {
        return lista_pets.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView pet_tipo;
        TextView pet_raca;
        TextView pet_sexo;
        TextView pet_idade;
        CardView card_item_lista;

        public myViewHolder(View itemView) {
            super(itemView);

            pet_tipo = itemView.findViewById(R.id.card_item_lista_tipo);
            pet_raca = itemView.findViewById(R.id.card_item_lista_raca);
            pet_sexo = itemView.findViewById(R.id.card_item_lista_sexo);
            pet_idade = itemView.findViewById(R.id.card_item_lista_idade);
            card_item_lista = itemView.findViewById(R.id.card_item_lista);

            card_item_lista.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.itemListClick(getLayoutPosition());
        }
    }

    public interface itemListClick {
        void itemListClick(int position);
    }

    public void implementItemListClick(itemListClick listener) {
        this.listener = listener;
    }
}
