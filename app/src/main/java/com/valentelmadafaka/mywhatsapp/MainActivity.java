package com.valentelmadafaka.mywhatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.valentelmadafaka.mywhatsapp.httpRequests.Logear;
import com.valentelmadafaka.mywhatsapp.httpRequests.RecibirMensajes;
import com.valentelmadafaka.mywhatsapp.model.User;
import com.valentelmadafaka.mywhatsapp.utils.PreferencesHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements com.valentelmadafaka.mywhatsapp.AsyncResponse {

    Logear logear;

    private ReceptorXarxa receptor;
    EditText email, password;
    TextView respuesta;
    CheckBox recordar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receptor = new ReceptorXarxa();
        this.registerReceiver(receptor, filter);

        email = findViewById(R.id.userEditText);
        password = findViewById(R.id.passwordEditText);
        respuesta = findViewById(R.id.respuesta);
        recordar = findViewById(R.id.recordarCheck);



//        if(PreferencesHelper.recuperaArrayList("Info Login") != null){
//            JSONObject infoUser = PreferencesHelper.recuperaArrayList("Info Login");
//            logear = new Logear();
//            logear.delegate = this;
//            try {
//                logear.execute(infoUser.getString("email"), infoUser.getString("password"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }

    }

    public void loginVoid(View view) {
        logear = new Logear();
        logear.delegate = this;
        logear.execute(email.getText().toString(), password.getText().toString());
        //logear.execute("valentinhernandiaz@paucasesnovescifp.cat", "s2p");
        //respuesta.setText(Auxiliar.interacionPost(email.getText().toString(), password.getText().toString(), true));
    }

    @Override
    public void processFinish(String output){
        if(output.equals("200")){
            //startActivity(new Intent(this, ChatActivity.class));
            finish();
        }else{
            respuesta.setText("Usuario y/o contraseña incorrecta");
        }
    }
}
