package com.example.adotepets.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "db_adotepets";
    public static String TABELA_PET = "pet";
    public static String TABELA_PETSHOP = "petshop";

    public DBHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_pet = "CREATE TABLE IF NOT EXISTS " + TABELA_PET +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "tipo VARCHAR(50) NOT NULL, " +
                "raca VARCHAR(50) NOT NULL, " +
                "sexo VARCHAR(50) NOT NULL, " +
                "descricao VARCHAR(255) NOT NULL, " +
                "idade INTEGER NOT NULL, " +
                "tamanho INTEGER NOT NULL, " +
                "peso FLOAT NOT NULL, " +
                "vacinado BOOLEAN NOT NULL);";

        String sql_petshop = "CREATE TABLE IF NOT EXISTS " + TABELA_PETSHOP +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "nome VARCHAR(50) NOT NULL, " +
                "endereco VARCHAR(255) NOT NULL);";

        try {
            db.execSQL(sql_pet);
            Log.i("INFO DB", "Sucesso ao criar tabela Pet!");
        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao criar tabela Pet" + e.getMessage());
        }

        try {
            db.execSQL(sql_petshop);
            Log.i("INFO DB", "Sucesso ao criar tabela Petshop!");
        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao criar tabela Petshop " + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql_pet = "DROP TABLE IF EXISTS " + TABELA_PET + ";";
        String sql_petshop = "DROP TABLE IF EXISTS " + TABELA_PETSHOP + ";";

        try {
            db.execSQL(sql_pet);
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao criar tabela Pet!");
        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao criar tabela Pet " + e.getMessage());
        }

        try {
            db.execSQL(sql_petshop);
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao criar tabela Petshop!");
        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao criar tabela Petshop " + e.getMessage());
        }
    }
}
