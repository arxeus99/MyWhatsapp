package com.valentelmadafaka.mywhatsapp;

import android.app.Application;
import android.content.Context;

public class MyWhatsapp extends Application {
    private static Context context;

    public void onCreate(){
        super.onCreate();
        MyWhatsapp.context = getApplicationContext();
    }

    public static Context getAppContext(){
        return MyWhatsapp.context;
    }
}
