package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Login extends AppCompatActivity {

    private EditText edtCorreoL, edtContrasenaL;
    Util util = new Util();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtCorreoL=(EditText) findViewById(R.id.edtCorreoL);
        edtContrasenaL=(EditText) findViewById(R.id.edtContrasenaL);
    }

    public void irprincipal(View view){
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }

    //validar que el usuario exista e iniciar sesion
    public void inicio_sesion(View view){
        //ver si existe el usuario
        String correoL = edtCorreoL.getText().toString();
        String contrasenaL = edtContrasenaL.getText().toString();

        if(!correoL.isEmpty() && !contrasenaL.isEmpty()){

            SQLiteDatabase db = util.abrir(this);
            //para moverse en el registro, hacer la busqueda con variable de tipo cursor
            String[] args = new String[] {correoL,contrasenaL};
            Cursor registro = db.rawQuery("select * from USERS where correo=? and contrasena=?",args);//consulta

            //buscar usuario
            if (registro.moveToFirst()){
                db.close();

                SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("cedula", String.valueOf(registro.getInt(0)));
                editor.putString("nombre", registro.getString(1));
                editor.commit();

                util.starActivity(this, UserPanel.class);
            }else{
                //creacion de mensaje emergente
                util.mensaje("Usuario o contraseña incorrectos", this);
                db.close();
            }
        }else {
            util.mensaje("Debe ingresar todos los datos", this);
        }
    }
}
