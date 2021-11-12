package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Mexican extends AppCompatActivity {

    private CheckBox cbqm, cbb, cbf;
    Util util = new Util();
    MenUtil menutil = new MenUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mexican);

        cbqm = (CheckBox) findViewById(R.id.CBQM);
        cbb = (CheckBox) findViewById(R.id.CBB);
        cbf = (CheckBox) findViewById(R.id.CBF);

        SharedPreferences hamburger = getSharedPreferences("mexican", MODE_PRIVATE);

        if(hamburger.getInt("Quesadilla Mixta",1) == 0){
            cbqm.setChecked(true);
        }
        if(hamburger.getInt("Burrito",1) == 0){
            cbb.setChecked(true);
        }
        if(hamburger.getInt("Fajita",1) == 0){
            cbf.setChecked(true);
        }
    }

    public void reservar(View view){
        finish();
        Toast.makeText(getApplicationContext(),"Reservado correctamente", Toast.LENGTH_SHORT).show();
    }

    public void MMM (View view){
        menutil.preferencesProduct("mexican", cbqm.isChecked(), "Quesadilla Mixta", this);
    }

    public void BBB (View view){
        menutil.preferencesProduct("mexican", cbb.isChecked(), "Burrito", this);
    }

    public void FFF (View view){
        menutil.preferencesProduct("mexican", cbf.isChecked(), "Fajita", this);
    }
}