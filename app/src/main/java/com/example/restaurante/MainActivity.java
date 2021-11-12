package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.logging.XMLFormatter;

public class MainActivity extends AppCompatActivity {

    ViewFlipper vfImagenes;
    Util util = new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //vector de imagenes
        int images[]={R.drawable.ham, R.drawable.b, R.drawable.n};
        vfImagenes=(ViewFlipper) findViewById(R.id.vfImagenes);

        //recorrer el vector para enviar al metodo creado
        for(int i = 0; i< images.length; i++){
            flipperImages(images[i]);
        }

        //creacion de objeto, se usa el metodo abrir de la clase util
        SQLiteDatabase db = util.abrir(this);

        //vector donde se almacenan los nombres de los productos
        String[] names = new String[]{ "Hamburguesa sencilla",
                                        "Hamburguesa doble",
                                        "Hamburguesa triple",
                                        "Perro sencillo",
                                        "Perro vegetariano",
                                        "Perro carnico",
                                        "Quesadilla Mixta",
                                        "Burrito",
                                        "Fajita",
                                        "Personal",
                                        "Litro / Medio",
                                        "Mega"};
        //vector precios de productos
        int[] prices = new int[]{ 7500,
                                12000,
                                15000,
                                5500,
                                9000,
                                15000,
                                12000,
                                13000,
                                15000,
                                3000,
                                6000,
                                9000};

        //recorrer los vectores para pasar el precio y nombre al metodo
        for (int i = 0; i < names.length; i++) {
            crearContent(i + 1, names[i], prices[i], db);
        }
        db.close();

    }

    //metodo para guardar los productos en la tabla
    private void crearContent(int id, String nombre, int precio, SQLiteDatabase db){
        ContentValues registro = new ContentValues();
        registro.put("id", String.valueOf(id));
        registro.put("nombre", nombre);
        registro.put("precio", String.valueOf(precio));
        insertar(db, registro);
    }

    private void insertar(SQLiteDatabase db, ContentValues registro){
        db.insert("PRODUCTS", null, registro);
    }

    //metodo para mover las imagenes
    public void flipperImages(int image){

        ImageView imageView= new ImageView(this);
        imageView.setBackgroundResource(image);

        vfImagenes.addView(imageView);
        vfImagenes.setFlipInterval(4000);
        vfImagenes.setAutoStart(true);

        vfImagenes.setInAnimation(this, android.R.anim.slide_in_left);
        vfImagenes.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    //dirige a la ventana de login
    public void login(View view){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    //dirige a la ventana de registro de usuario
    public void registro(View view){
        Intent r = new Intent(this, Registro.class);
        startActivity(r);
    }
}