package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends AppCompatActivity {

    TextView product, price, twTotal;
    String nombre = "";
    String precio = "";
    Util util = new Util();
    List<String> lista = new ArrayList();
    float suma = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        product = findViewById(R.id.txc);
        price = findViewById(R.id.txc2);
        twTotal = findViewById(R.id.twTotal);

        comprobar("hamburger", "Hamburguesa sencilla", "Hamburguesa doble", "Hamburguesa triple");
        comprobar("hotdogs", "Perro sencillo", "Perro vegetariano", "Perro carnico");
        comprobar("mexican", "Quesadilla Mixta", "Burrito", "Fajita");
        comprobar("soda", "Personal", "Litro / Medio", "Mega");

        twTotal.setText(suma + "$");
    }

    private void comprobar(String menuType, String one, String two, String three){
        SharedPreferences preferences = getSharedPreferences(menuType, MODE_PRIVATE);
        if(preferences.getInt(one,1) == 0){
            busqueda(one);
        }
        if(preferences.getInt(two,1) == 0){
            busqueda(two);
        }
        if(preferences.getInt(three,1) == 0){
            busqueda(three);
        }
    }

    private void busqueda(String nombre){
        String[] name = new String[]{ nombre };
        SQLiteDatabase db = util.abrir(this);
        Cursor cursor = db.rawQuery("SELECT nombre, precio FROM PRODUCTS WHERE nombre = ?", name);
        if (cursor.moveToFirst()) {
            espaciado();
            product.setText(product.getText() + cursor.getString(0));
            price.setText(price.getText() + cursor.getString(1));
            lista.add(cursor.getString(0));
            suma = suma + cursor.getInt(1);
        }
        db.close();
    }

    private void espaciado(){
        if (!product.getText().toString().equals("")) {
            product.setText(product.getText() + ", \n");
        }
        if (!price.getText().toString().equals("")) {
            price.setText(price.getText() + ", \n");
        }
    }

    public void comprar(View view){
        SQLiteDatabase db = util.abrir(this);
        for (String valor: lista) {
            Cursor id = db.rawQuery("SELECT id FROM PRODUCTS WHERE nombre = '" + valor.trim() + "'", null);
            id.moveToFirst();

            String[] aux = util.preferences(this);
            ContentValues registro = new ContentValues();
            registro.put("cedula", aux[0]);
            registro.put("id_product", String.valueOf(id.getInt(0)));

            db.insert("HISTORY", null, registro);
        }
        deletePreferences("hamburger");
        deletePreferences("mexican");
        deletePreferences("hotdogs");
        deletePreferences("soda");
        finish();
        Toast.makeText(getApplicationContext(),"Compra realizada correctamente", Toast.LENGTH_SHORT).show();
    }

    private void deletePreferences(String menuType){
        SharedPreferences.Editor editor = getSharedPreferences(menuType, MODE_PRIVATE).edit();
        editor.clear().apply();
    }
}