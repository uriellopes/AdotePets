package com.example.adotepets.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adotepets.AdicionarPetshop;
import com.example.adotepets.GerenciarPetshops;
import com.example.adotepets.R;
import com.example.adotepets.model.Petshop;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AdicionarPetshopFragment extends Fragment {

    private EditText editNome, editEndereco;
    private Petshop novoPetshop;
    private Button btnBuscaCEP;
    private Button btnAdicionarPetshop;
    private View layout;

    public AdicionarPetshopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_adicionar_petshop, container, false);

        editNome = layout.findViewById(R.id.edit_nome);
        editEndereco = layout.findViewById(R.id.edit_Endereco);
        btnBuscaCEP = layout.findViewById(R.id.btnBuscaCEP);
        btnAdicionarPetshop = layout.findViewById(R.id.btnAddPetshop);

        btnAdicionarPetshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarPetshop();
            }
        });

        btnBuscaCEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MinhaTarefa tarefa = new MinhaTarefa();
//                String cep = "01001000";
                String cep = editEndereco.getText().toString();
                if( editEndereco.getText() == null || editEndereco.getText().toString().trim().equals("") ) {
                    Toast.makeText(getActivity(), "Digite o endereço do petshop a ser adicionada!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    String urlApi = "https://viacep.com.br/ws/" + cep + "/json/";
                    tarefa.execute(urlApi);
                }
            }
        });

        return layout;
    }

    public interface clickAdicionarPetshop{
        void adicionouPetshop(Petshop petshop);
    }

    public void adicionarPetshop() {
        Activity a = getActivity();

        if ( a instanceof AdicionarPetshopFragment.clickAdicionarPetshop) {

            if( editNome.getText() == null || editNome.getText().toString().trim().equals("") ) {
                Toast.makeText(getActivity(), "Digite o nome do petshop a ser adicionado!", Toast.LENGTH_SHORT).show();
                return;
            }

            if( editEndereco.getText() == null || editEndereco.getText().toString().trim().equals("") ) {
                Toast.makeText(getActivity(), "Digite o endereço do petshop a ser adicionada!", Toast.LENGTH_SHORT).show();
                return;
            }

            novoPetshop = new Petshop(
                    editNome.getText().toString(),
                    editEndereco.getText().toString()
            );

            Intent it = new Intent(a.getApplicationContext(), GerenciarPetshops.class);
            startActivity(it);
        }
    }

    class MinhaTarefa extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String stringUrl = strings[0];
            InputStream inputStream = null;

            InputStreamReader inputStreamReader = null;
            StringBuffer buffer = null;

            try{
                URL url = new URL(stringUrl);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                inputStream = conexao.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);

                BufferedReader reader = new BufferedReader(inputStreamReader);
                buffer = new StringBuffer();
                String linha ="";

                while((linha = reader.readLine()) != null){
                    buffer.append(linha);
                }


            }catch(MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return buffer.toString();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            String logradouro = null;
            String cep = null;
            String complemento = null;
            String bairro = null;
            String localidade = null;
            String uf = null;

            try{
                //3.1 criar objeto JSONObject
                JSONObject jsonObject = new JSONObject(resultado);

                logradouro = jsonObject.getString("logradouro");
                cep = jsonObject.getString("cep");
                complemento = jsonObject.getString("complemento");
                bairro = jsonObject.getString("bairro");
                localidade = jsonObject.getString("localidade");
                uf = jsonObject.getString("uf");

            }catch(JSONException e){
                //caso resultado nao esteja num formato válido
                e.printStackTrace();
            }

            editEndereco.setText(logradouro + " / " +cep+" / "+ localidade+" / "+uf);
        }
    }
}