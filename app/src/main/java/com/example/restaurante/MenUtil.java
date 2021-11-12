package com.example.restaurante;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.CheckBox;

public class MenUtil {

    public void preferencesProduct(String menuType, Boolean box, String foodType, Context context){
        SharedPreferences preferences = context.getSharedPreferences(menuType, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(box){
            editor.putInt(foodType,0);
            editor.commit();
        }
        else{
            editor.putInt(foodType,1);
            editor.commit();
        }
    }
}
