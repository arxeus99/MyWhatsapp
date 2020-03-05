package com.valentelmadafaka.mywhatsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class ReceptorXarxa extends BroadcastReceiver {





    @Override
    public void onReceive(Context context, Intent intent) {
        //Actualitza l'estat de la xarxa
        comprovaConnectivitat(context);
    }

    public void comprovaConnectivitat(Context context){
        //Obtenim un gestor de les connexions de xarxa
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE); //Obtenim l’estat de laxarxa
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        //Si està connectat
        if (networkInfo != null && networkInfo.isConnected()) {
            //Xarxa OK
            //Toast.makeText(context,"Xarxa ok", Toast.LENGTH_LONG).show();
            networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            boolean connectat4G = networkInfo.isConnected();
            //Obtenim l’estat de la xarxa Wifi
            networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            boolean connectatWifi = networkInfo.isConnected();
            if (connectat4G) {
                //Xarxa OK
                //Toast.makeText(context,"Connectat per 4G", Toast.LENGTH_LONG).show();
            }
            if(connectatWifi){
                //Xarxa no disponible
               // Toast.makeText(context,"Connectat per Wi-Fi", Toast.LENGTH_LONG).show();
            }
        } else {
            //Xarxa no disponible
            Toast.makeText(context,"NO HAY CONEXION A INTERNET",
                    Toast.LENGTH_LONG).show(); }
    }
}
