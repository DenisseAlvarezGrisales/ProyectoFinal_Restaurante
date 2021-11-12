package com.example.restaurante;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AlertDialog;

public class Util {

    public void mensaje(String mensaje, Context context){
        AlertDialog.Builder alerta = new AlertDialog.Builder(context);

        alerta.setMessage(mensaje);
        alerta.setPositiveButton("Aceptar", null);

        AlertDialog dialog = alerta.create();
        dialog.show();
    }

    public SQLiteDatabase abrir(Context context){
        //crear objeto para referir a la base de datos
        DataBase admin = new DataBase(context, "restaurante", null, 1);
        return admin.getWritableDatabase(); //se usa metodo para modificar db
    }

    public void starActivity(Context context, Class clase){
        Intent i = new Intent(context, clase);
        context.startActivity(i);
    }

    public String[] preferences(Context context){
        SharedPreferences user = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        String[] credenciales =  new String [2];
        credenciales[0] = user.getString("cedula", "No esta definido");
        credenciales[1] = user.getString("nombre", "No esta definido");
        return credenciales;
    }

/*    AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage("xxxxxxxx");
        alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
        public void onClick(DialogInterface dialog, int id) {

        }
    });
    AlertDialog dialog = alerta.create();
        dialog.show();*/
}
