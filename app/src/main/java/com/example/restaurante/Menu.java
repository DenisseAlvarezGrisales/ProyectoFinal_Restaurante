package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaCas;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    Util util = new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void hamburguesas (View view){
        util.starActivity(this, Hamburger.class);
    }

    public void perro (View view){
        util.starActivity(this, Hotdogs.class);
    }

    public void mexicana (View view){
        util.starActivity(this, Mexican.class);
    }

    public void gaseosas (View view){
        util.starActivity(this, Soda.class);
    }

    public void shoppingCart (View view){
        util.starActivity(this, ShoppingCart.class);
    }
}