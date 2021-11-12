package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class UserPanel extends AppCompatActivity {

    Util util = new Util();
    TextView twBienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel);
        TextView twBienvenido = (TextView) findViewById(R.id.twBienvenido);
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);

        String nombre = user.getString("nombre", "");
        twBienvenido.setText("Bienvenid@: " + nombre);
    }

    public void menu(View view){
        util.starActivity(this, Menu.class);
    }

    public void updateData(View view){
        util.starActivity(this, UpdateData.class);
        ((Activity) this).finish();
    }

    public void closeSession(View view){
        util.starActivity(this, Login.class);
        finish();
    }

    public void history(View view){
        util.starActivity(this, History.class);
    }

}