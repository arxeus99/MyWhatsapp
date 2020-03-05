package com.valentelmadafaka.mywhatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.valentelmadafaka.mywhatsapp.db.dbQuePasa;
import com.valentelmadafaka.mywhatsapp.httpRequests.EnviarMensaje;
import com.valentelmadafaka.mywhatsapp.httpRequests.RecibirMensajes;
import com.valentelmadafaka.mywhatsapp.model.Mensaje;
import com.valentelmadafaka.mywhatsapp.model.MensajesArray;
import com.valentelmadafaka.mywhatsapp.model.User;
import com.valentelmadafaka.mywhatsapp.utils.PreferencesHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {

    RecibirMensajes recibirMensajes;
    ArrayList<Mensaje> mensajeArrayList = new ArrayList<>();
    MensajesArray mensajesArray;
    EditText mensaje;
    User u;
    dbQuePasa bd = new dbQuePasa(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        u = new User();

        JSONObject userInfo = PreferencesHelper.recuperaArrayList("Info Usuari");
        if(userInfo == null){
            startActivity(new Intent(this, MainActivity.class));
        }
        try {
            u.setNom(userInfo.getString("nom"));
            u.setToken(userInfo.getString("token"));
            u.setCodiUsuari(userInfo.getString("codiusuari"));
            u.setEmail(userInfo.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        mensaje = findViewById(R.id.messageToSend);
        recibirMensajes = new RecibirMensajes();
        recibirMensajes.execute(u.getToken());
        bd.obre();
        Cursor c = bd.obtenMensajes();
        c.moveToFirst();
        while(!c.isAfterLast()){
            Mensaje m = new Mensaje();
            m.setIdMessage(c.getString(0));
            m.setText(c.getString(1));
            m.setDate(c.getString(2));
            m.setIdUser(c.getString(3));
            mensajeArrayList.add(m);
        }

        bd.tanca();

        mensajesArray = new MensajesArray(this, R.layout.mensaje, mensajeArrayList);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(mensajesArray);
        listView.setSelection(mensajesArray.getCount()-1);



    }


    public void enviarVoid(View view) {
        if(!mensaje.getText().toString().isEmpty()){
            EnviarMensaje enviarMensaje = new EnviarMensaje();
            enviarMensaje.execute(mensaje.getText().toString(), u.getCodiUsuari(), u.getToken());
            mensaje.setText("");
        }
    }
}
