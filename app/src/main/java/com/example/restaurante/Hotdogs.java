package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Hotdogs extends AppCompatActivity {

    private CheckBox cbps, cbpv, cbpc;
    Util util = new Util();
    MenUtil menutil = new MenUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotdogs);

        cbps = (CheckBox) findViewById(R.id.CBPS);
        cbpv = (CheckBox) findViewById(R.id.CBPV);
        cbpc = (CheckBox) findViewById(R.id.CBPC);

        String menuType = "hotdogs";

        SharedPreferences hamburger = getSharedPreferences("hotdogs", MODE_PRIVATE);

        if(hamburger.getInt("Perro sencillo",1) == 0){
            cbps.setChecked(true);
        }
        if(hamburger.getInt("Perro vegetariano",1) == 0){
            cbpv.setChecked(true);
        }
        if(hamburger.getInt("Perro carnico",1) == 0){
            cbpc.setChecked(true);
        }
    }

    public void reservar(View view){
        finish();
        Toast.makeText(getApplicationContext(),"Reservado correctamente", Toast.LENGTH_SHORT).show();
    }

    public void SSS (View view){
        menutil.preferencesProduct("hotdogs", cbps.isChecked(), "Perro sencillo", this);
    }

    public void VVV (View view){
        menutil.preferencesProduct("hotdogs", cbpv.isChecked(), "Perro vegetariano", this);
    }

    public void CCC (View view){
        menutil.preferencesProduct("hotdogs", cbpc.isChecked(), "Perro carnico", this);
    }
}