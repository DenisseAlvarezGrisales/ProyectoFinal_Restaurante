package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateData extends AppCompatActivity {

    private TextView twCedula;
    private EditText edtNombre, edtCorreo, edtCedula, edtTelefono, edtDireccion, edtContrasena, edtContrasenaConfirma;

    private Util util = new Util();

    Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);

        twCedula = (TextView) findViewById(R.id.twCedula);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtCorreo = (EditText) findViewById(R.id.edtCorreo);
        edtCedula = (EditText) findViewById(R.id.edtCedula);
        edtTelefono = (EditText) findViewById(R.id.edtTelefono);
        edtDireccion = (EditText) findViewById(R.id.edtDireccion);
        edtContrasena = (EditText) findViewById(R.id.edtContrasena);
        edtContrasenaConfirma = (EditText) findViewById(R.id.edtContrasenaConfirma);

        twCedula.setText(user.getString("cedula", "No existe"));

        String[] arg = new String[]{ twCedula.getText().toString() };

        SQLiteDatabase db = util.abrir(this);
        fila = db.rawQuery("SELECT * FROM USERS WHERE cedula = ?", arg);

        if (fila.moveToFirst()){
            edtNombre.setText(fila.getString(1));
            edtTelefono.setText(fila.getString(2));
            edtDireccion.setText(fila.getString(3));
            edtCorreo.setText(fila.getString(4));
            edtContrasena.setText(fila.getString(5));
            edtContrasenaConfirma.setText(fila.getString(5));
        }
        db.close();
    }

    public void update(View view){
        if (!edtNombre.getText().toString().equals("") && !edtCorreo.getText().toString().equals("") ) {

            if (edtContrasena.getText().toString().equals(edtContrasenaConfirma.getText().toString())){
                ContentValues data = new ContentValues();
                data.put("nombre", edtNombre.getText().toString());
                data.put("telefono", edtTelefono.getText().toString());
                data.put("direccion", edtDireccion.getText().toString());
                data.put("correo", edtCorreo.getText().toString());
                data.put("contrasena", edtContrasena.getText().toString());

                SQLiteDatabase db = util.abrir(this);
                db.update("USERS", data, "cedula = " + fila.getString(0), null);
                db.close();

                SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = user.edit();
                editor.putString("nombre", edtNombre.getText().toString()).commit();

                util.starActivity(this, UserPanel.class);
                finish();
            }else{
                util.mensaje("Las contraseñas no coinciden", this);
            }

        }else{
            util.mensaje("Debe ingresar nombre y correo", this);
        }
    }

    public void finalActivity(View view){
        util.starActivity(this, UserPanel.class);
        finish();
    }
}