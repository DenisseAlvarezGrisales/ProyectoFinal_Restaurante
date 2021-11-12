package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class History extends AppCompatActivity {

    Util util = new Util();
    private TextView tvHistorialn,tvHistorialp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tvHistorialn = (TextView) findViewById(R.id.tvHistorialn);
        tvHistorialp = (TextView) findViewById(R.id.tvHistorialp);
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);

        CargarHistorial();
    }

    public void CargarHistorial(){
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        String cedula = user.getString("cedula", "");

        SQLiteDatabase db = util.abrir(this);
        //para moverse en el registro, hacer la busqueda con variable de tipo cursor
        Cursor ced=db.rawQuery("SELECT cedula from USERS where cedula="+cedula,null);

        Cursor registro = db.rawQuery("SELECT PRODUCTS.nombre,PRODUCTS.precio FROM HISTORY INNER JOIN PRODUCTS ON HISTORY.id_product = PRODUCTS.id INNER JOIN USERS ON HISTORY.cedula ="+cedula,null);

        if(registro.moveToFirst()){
            do{
                tvHistorialn.setText(tvHistorialn.getText() + registro.getString(0) + ", \n");
                tvHistorialp.setText(tvHistorialp.getText() + registro.getString(1) + ", \n");
            }while(registro.moveToNext());
            db.close();
        }
    }
}