package com.example.restaurante;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {

    private Util util = new Util();

    private EditText edtNombre, edtCorreo, edtCedula, edtTelefono, edtDireccion, edtContrasena, edtContrasenaConfirma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtCorreo = (EditText) findViewById(R.id.edtCorreo);
        edtCedula = (EditText) findViewById(R.id.edtCedula);
        edtTelefono = (EditText) findViewById(R.id.edtTelefono);
        edtDireccion = (EditText) findViewById(R.id.edtDireccion);
        edtContrasena = (EditText) findViewById(R.id.edtContrasena);
        edtContrasenaConfirma = (EditText) findViewById(R.id.edtContrasenaConfirma);
    }

    //metodo para ir al activity principal
    public void irprincipal(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    //metodo para crear un nuevo usuario
    public void registrar(View view) {

        //tomar lo que el usuario digita
        String cedula = edtCedula.getText().toString();
        String nombre = edtNombre.getText().toString();
        String telefono = edtTelefono.getText().toString();
        String correo = edtCorreo.getText().toString();
        String direccion = edtDireccion.getText().toString();
        String contrasena = edtContrasena.getText().toString();
        String contrasena_confirma = edtContrasenaConfirma.getText().toString();


        //validacion
        if (!nombre.isEmpty() && !telefono.isEmpty() && !correo.isEmpty() && !direccion.isEmpty() && !contrasena.isEmpty() && !contrasena_confirma.isEmpty()) {
            if (contrasena.equals(contrasena_confirma)) {

                SQLiteDatabase db = util.abrir(this);
                String[] args = new String[] {cedula, correo};
                Cursor fila = db.rawQuery("select correo from USERS where cedula = ? and correo = ?", args);


                if (fila.moveToFirst()) {
                    //Toast.makeText(this,"Usuario ya existe",Toast.LENGTH_LONG).show();
                    util.mensaje("Usuario ya existe", this);
                    db.close();
                }else{
                    //crear variable registro tipo contentvalue
                    ContentValues registro = new ContentValues();

                    registro.put("cedula", cedula);
                    registro.put("nombre", nombre);
                    registro.put("telefono", telefono);
                    registro.put("direccion", direccion);
                    registro.put("correo", correo);
                    registro.put("contrasena", contrasena);

                    //insertar registro
                    db.insert("USERS", null, registro);
                    db.close();

                    util.mensaje("Usuario creado exitosamente!", this);

                    Intent i=new Intent(this, Login.class);
                    startActivity(i);
                }
            }else {
                util.mensaje("Contraseñas no coinciden", this);
            }
        } else {
            util.mensaje("Debe ingresar todos los datos", this);
        }
    }
}
