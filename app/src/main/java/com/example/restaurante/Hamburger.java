package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Hamburger extends AppCompatActivity {

    private CheckBox cbhs,cbhd, cbht;
    Util util = new Util();
    MenUtil menutil = new MenUtil();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamburger);

        cbhs = (CheckBox) findViewById(R.id.CBHS);
        cbhd = (CheckBox) findViewById(R.id.CBHD);
        cbht = (CheckBox) findViewById(R.id.CBHT);

        SharedPreferences hamburger = getSharedPreferences("hamburger", MODE_PRIVATE);

        if(hamburger.getInt("Hamburguesa sencilla",1) == 0){
            cbhs.setChecked(true);
        }
        if(hamburger.getInt("Hamburguesa doble",1) == 0){
            cbhd.setChecked(true);
        }
        if(hamburger.getInt("Hamburguesa triple",1) == 0){
            cbht.setChecked(true);
        }
    }

    public void reservar(View view){
/*        cheked(cbhs.isChecked(), "hamburger", "Hamburguesa sencilla");
        cheked(cbhd.isChecked(), "hamburger", "Hamburguesa doble");
        cheked(cbht.isChecked(), "hamburger", "Hamburguesa triple");*/
        finish();
        Toast.makeText(getApplicationContext(),"Reservado correctamente", Toast.LENGTH_SHORT).show();
    }

/*    private void cheked(Boolean box, String menuType, String foodType){
        if(box){
            menutil.preferencesProduct(menuType, true, foodType, this);
        }else{
            menutil.preferencesProduct(menuType, false, foodType, this);
        }
    }*/

    public void SSS (View view){
        menutil.preferencesProduct("hamburger", cbhs.isChecked(), "Hamburguesa sencilla", this);
    }

    public void DDD (View view){
        menutil.preferencesProduct("hamburger", cbhd.isChecked(), "Hamburguesa doble", this);
    }

    public void TTT (View view){
        menutil.preferencesProduct("hamburger", cbht.isChecked(), "Hamburguesa triple", this);
    }
}
