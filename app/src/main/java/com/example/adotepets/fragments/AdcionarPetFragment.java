package com.example.adotepets.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.adotepets.R;
import com.example.adotepets.model.Pet;

public class AdcionarPetFragment extends Fragment {

    private EditText editTipo, editRaca, editIdade, editPeso, editTamanho, editDescricao;
    private RadioGroup editSexo;
    private CheckBox editVacinado;
    private Pet novoPet;
    private Button btnAdicionar;
    private Switch switch_descricao;
    private View layout;

    public AdcionarPetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_adcionar_pet, container, false);

        editTipo = layout.findViewById(R.id.edit_tipo);
        editRaca = layout.findViewById(R.id.edit_raca);
        editIdade = layout.findViewById(R.id.edit_idade);
        editSexo = layout.findViewById(R.id.edit_sexo);
        editPeso = layout.findViewById(R.id.edit_peso);
        editTamanho = layout.findViewById(R.id.edit_tamanho);
        editVacinado = layout.findViewById(R.id.edit_vacinado);
        editDescricao = layout.findViewById(R.id.edit_descricao);
        btnAdicionar = layout.findViewById(R.id.btnAdicionar);
        switch_descricao = layout.findViewById(R.id.switch1);

        editDescricao.setEnabled(false);

        switch_descricao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( isChecked ) {
                    editDescricao.setEnabled(true);
                } else {
                    editDescricao.setEnabled(false);
                }
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarPet();
            }
        });

        return layout;
    }

    public interface clickAdicionarPet{
        void adicionouPet(Pet pet);
    }

    public void adicionarPet() {
        Activity a = getActivity();

        if ( a instanceof clickAdicionarPet ) {

            if( editTipo.getText() == null || editTipo.getText().toString().trim().equals("") ) {
                Toast.makeText(getActivity(), "Digite o tipo do pet a ser adicionado!", Toast.LENGTH_SHORT).show();
                return;
            }

            if( editRaca.getText() == null || editRaca.getText().toString().trim().equals("") ) {
                Toast.makeText(getActivity(), "Digite a raça do pet a ser adicionada!", Toast.LENGTH_SHORT).show();
                return;
            }

            if( editIdade.getText() == null || editIdade.getText().toString().trim().equals("") ) {
                Toast.makeText(getActivity(), "Digite a idade do pet", Toast.LENGTH_SHORT).show();
                return;
            }

            if( editPeso.getText() == null || editPeso.getText().toString().trim().equals("") ) {
                Toast.makeText(getActivity(), "Digite o peso do pet", Toast.LENGTH_SHORT).show();
                return;
            }

            if( editTamanho.getText() == null || editTamanho.getText().toString().trim().equals("") ) {
                Toast.makeText(getActivity(), "Digite o tamanho do pet", Toast.LENGTH_SHORT).show();
                return;
            }

            if( editSexo.getCheckedRadioButtonId() == -1 ) {
                Toast.makeText(getActivity(), "Escolha o sexo do seu pet!", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedId = editSexo.getCheckedRadioButtonId();
            RadioButton btn_radio = layout.findViewById(selectedId);
            String desc;

            if( switch_descricao.isChecked() ) {
                if( editDescricao.getText() == null || editDescricao.getText().toString().trim().equals("") ) {
                    Toast.makeText(getActivity(), "Digite o comentário ou desmarque a opcão para adicionar comentário!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    desc = editDescricao.getText().toString();
                }
            } else {
                desc = "Nenhum comentário adicional...";
            }

            novoPet = new Pet(
                    editTipo.getText().toString(),
                    editRaca.getText().toString(),
                    btn_radio.getText().toString(),
                    desc,
                    Integer.valueOf(editIdade.getText().toString()),
                    Integer.valueOf(editTamanho.getText().toString()),
                    Float.valueOf(editPeso.getText().toString()),
                    editVacinado.isChecked()
            );
        }

        clickAdicionarPet listener = (clickAdicionarPet) a;
        listener.adicionouPet(novoPet);
    }
}