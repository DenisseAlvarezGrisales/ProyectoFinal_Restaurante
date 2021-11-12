package com.example.restaurante;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase restaurante) {
        //creamos la tabla usuario
        restaurante.execSQL("CREATE TABLE USERS(cedula INT PRIMARY KEY, " +
                                                "nombre VARCHAR(255), " +
                                                "telefono INT, " +
                                                "direccion VARCHAR(255), " +
                                                "correo VARCHAR(255), " +
                                                "contrasena VARCHAR(255))");

        restaurante.execSQL("CREATE TABLE PRODUCTS(id INT PRIMARY KEY, nombre STRING(255), precio INT)");

        restaurante.execSQL("CREATE TABLE HISTORY(id INT PRIMARY KEY, cedula INT, id_product INT, " +
                                                 "FOREIGN KEY(cedula) REFERENCES USERS(cedula)," +
                                                 "FOREIGN KEY(id_product) REFERENCES PRODUCTS(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}

