package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Soda extends AppCompatActivity {

    private CheckBox cbgp, cblm, cbgm;
    Util util = new Util();
    MenUtil menutil = new MenUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soda);

        cbgp = (CheckBox) findViewById(R.id.CBGP);
        cblm = (CheckBox) findViewById(R.id.CBGLM);
        cbgm = (CheckBox) findViewById(R.id.CBGM);

        String menuType = "hotdogs";

        SharedPreferences hamburger = getSharedPreferences("soda", MODE_PRIVATE);

        if(hamburger.getInt("Personal",1) == 0){
            cbgp.setChecked(true);
        }
        if(hamburger.getInt("Litro / Medio",1) == 0){
            cblm.setChecked(true);
        }
        if(hamburger.getInt("Mega",1) == 0){
            cbgm.setChecked(true);
        }
    }

    public void reservar(View view){
        finish();
        Toast.makeText(getApplicationContext(),"Reservado correctamente", Toast.LENGTH_SHORT).show();
    }

    public void PPP (View view){
        menutil.preferencesProduct("soda", cbgp.isChecked(), "Personal", this);
    }

    public void LMM (View view){
        menutil.preferencesProduct("soda", cblm.isChecked(), "Litro / Medio", this);
    }

    public void MMM (View view){
        menutil.preferencesProduct("soda", cbgm.isChecked(), "Mega", this);
    }
}