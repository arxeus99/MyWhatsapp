package com.valentelmadafaka.mywhatsapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.valentelmadafaka.mywhatsapp.MyWhatsapp;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Valentelmadafaka
 */

public abstract class PreferencesHelper {

    public static void desaArrayList(String key, Object data){
        SharedPreferences prefs = MyWhatsapp.getAppContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString(key,json);
        editor.apply();
    }

    public static JSONObject recuperaArrayList(String key){
        SharedPreferences prefs = MyWhatsapp.getAppContext().getSharedPreferences("UserInfo",Context.MODE_PRIVATE);
        String json = prefs.getString(key,"");
        JSONObject obj;
        if(json.isEmpty()){
            obj = null;
        }else{
            java.lang.reflect.Type type = new TypeToken<JSONObject>(){}.getType();
            Gson gson = new Gson();
            obj = gson.fromJson(json, type);
        }
        return obj;
    }
}
