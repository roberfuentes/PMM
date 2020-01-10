package com.example.firstdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    /*int DB_VERSION = 3;
    String DB_NAME = "DBClientes";
    String DB_TABLE_PERSONA = "Persona";
    String id = "id";
    String name = "name";
    String phone = "phone";*/



    String DB_CREATE_TABLE = "CREATE TABLE Clientes (id INTEGER, name TEXT , phone TEXT)";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Clientes");

        db.execSQL(DB_CREATE_TABLE);
    }
}
