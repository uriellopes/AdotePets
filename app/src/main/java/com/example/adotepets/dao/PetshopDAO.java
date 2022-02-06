package com.example.adotepets.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.adotepets.model.Petshop;
import com.example.adotepets.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class PetshopDAO {

    private final SQLiteDatabase escreve;
    private final SQLiteDatabase le;

    public PetshopDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);

        escreve = dbHelper.getWritableDatabase();
        le = dbHelper.getReadableDatabase();
    }

    public List<Petshop> listar() {

        List<Petshop> petshops = new ArrayList<Petshop>();

        String sql = "SELECT * FROM " + DBHelper.TABELA_PETSHOP+";";

        Cursor cursor = le.rawQuery(sql, null);

        while ( cursor.moveToNext() ) {
            petshops.add(new Petshop(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                    cursor.getString(cursor.getColumnIndexOrThrow("cep")),
                    cursor.getString(cursor.getColumnIndexOrThrow("logradouro")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("numero")),
                    cursor.getString(cursor.getColumnIndexOrThrow("bairro")),
                    cursor.getString(cursor.getColumnIndexOrThrow("uf"))
            ));
        }
        cursor.close();
        return petshops;
    }

    public boolean salvar(Petshop petshop) {
        ContentValues cv = new ContentValues();
        cv.put("nome", petshop.getNome());
        cv.put("cep", petshop.getCep());
        cv.put("logradouro", petshop.getLogradouro());
        cv.put("numero", petshop.getNumero());
        cv.put("bairro", petshop.getBairro());
        cv.put("uf", petshop.getUf());

        try {
            escreve.insert(DBHelper.TABELA_PETSHOP, null, cv);
            Log.i("INFO", "Petshop salvo com sucesso!");
        } catch (Exception e) {
            Log.i("INFO", "Erro ao salvar petshop!" + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deletar(Petshop petshop) {
        try{
            String[] args = {String.valueOf(petshop.getId())};
            escreve.delete(DBHelper.TABELA_PETSHOP, "id=?", args);
            Log.i("INFO", "Petshop apagado com sucesso!");
        } catch (Exception e) {
            Log.i("INFO", "Erro ao apagar Petshop! " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean atualizar(Petshop petshop) {
        ContentValues cv = new ContentValues();
        cv.put("nome", petshop.getNome());
        cv.put("cep", petshop.getCep());
        cv.put("logradouro", petshop.getLogradouro());
        cv.put("numero", petshop.getNumero());
        cv.put("bairro", petshop.getBairro());
        cv.put("uf", petshop.getUf());

        try{
            String[] args = {String.valueOf(petshop.getId())};
            escreve.update(DBHelper.TABELA_PETSHOP, cv, "id=?", args);
            Log.i("INFO", "Petshop atualizado com sucesso!");
        } catch (Exception e) {
            Log.i("INFO", "Erro ao atualizar petshop! " + e.getMessage());
            return false;
        }
        return true;
    }
}
