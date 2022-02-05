package com.example.adotepets.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.adotepets.model.Pet;
import com.example.adotepets.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    private final SQLiteDatabase escreve;
    private final SQLiteDatabase le;

    public PetDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);

        escreve = dbHelper.getWritableDatabase();
        le = dbHelper.getReadableDatabase();
    }

    public List<Pet> listar() {

        List<Pet> notas = new ArrayList<Pet>();

        String sql = "SELECT * FROM " + DBHelper.TABELA_PET+";";

        Cursor cursor = le.rawQuery(sql, null);

        while ( cursor.moveToNext() ) {
            notas.add(new Pet(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("tipo")),
                    cursor.getString(cursor.getColumnIndexOrThrow("raca")),
                    cursor.getString(cursor.getColumnIndexOrThrow("sexo")),
                    cursor.getString(cursor.getColumnIndexOrThrow("descricao")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("idade")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("tamanho")),
                    cursor.getFloat(cursor.getColumnIndexOrThrow("peso")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("vacinado")) > 0
            ));
        }
        cursor.close();
        return notas;
    }

    public boolean salvar(Pet pet) {
        ContentValues cv = new ContentValues();
        cv.put("tipo", pet.getTipo());
        cv.put("raca", pet.getRaca());
        cv.put("sexo", pet.getRaca());
        cv.put("descricao", pet.getDescricao());
        cv.put("idade", pet.getIdadeDB());
        cv.put("tamanho", pet.getTamanhoDB());
        cv.put("peso", pet.getPesoDB());
        cv.put("vacinado", pet.getVacinadoDB());

        try {
            escreve.insert(DBHelper.TABELA_PET, null, cv);
            Log.i("INFO", "Pet salvo com sucesso!");
        } catch (Exception e) {
            Log.i("INFO", "Erro ao salvar pet!" + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deletar(Pet pet) {
        try{
            String[] args = {String.valueOf(pet.getId())};
            escreve.delete(DBHelper.TABELA_PET, "id=?", args);
            Log.i("INFO", "Pet apagado com sucesso!");
        } catch (Exception e) {
            Log.i("INFO", "Erro ao apagar Pet! " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean atualizar(Pet pet) {
        ContentValues cv = new ContentValues();
        cv.put("tipo", pet.getTipo());
        cv.put("raca", pet.getRaca());
        cv.put("sexo", pet.getRaca());
        cv.put("descricao", pet.getDescricao());
        cv.put("idade", pet.getIdadeDB());
        cv.put("tamanho", pet.getTamanhoDB());
        cv.put("peso", pet.getPesoDB());
        cv.put("vacinado", pet.getVacinadoDB());

        try{
            String[] args = {String.valueOf(pet.getId())};
            escreve.update(DBHelper.TABELA_PET, cv, "id=?", args);
            Log.i("INFO", "Pet atualizada com sucesso!");
        } catch (Exception e) {
            Log.i("INFO", "Erro ao atualizar pet! " + e.getMessage());
            return false;
        }
        return true;
    }
}
