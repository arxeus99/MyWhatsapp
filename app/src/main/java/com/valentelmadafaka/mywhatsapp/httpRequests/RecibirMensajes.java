package com.valentelmadafaka.mywhatsapp.httpRequests;

import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.valentelmadafaka.mywhatsapp.AsyncResponse;
import com.valentelmadafaka.mywhatsapp.MyWhatsapp;
import com.valentelmadafaka.mywhatsapp.R;
import com.valentelmadafaka.mywhatsapp.db.dbQuePasa;
import com.valentelmadafaka.mywhatsapp.model.Mensaje;
import com.valentelmadafaka.mywhatsapp.model.MensajesArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecibirMensajes extends AsyncTask<String, Void, String> {

    dbQuePasa bd = new dbQuePasa(MyWhatsapp.getAppContext());

    @Override
    protected String doInBackground(String... strings) {

        try{
            URL url = new URL("http://52.44.95.114/quepassaeh/server/public/provamissatge/");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Authorization", strings[0]);

            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null){
                    sb.append(line);
                }

                in.close();
                return sb.toString();
            }else{
                return new String("false: "+responseCode);
            }

        } catch (Exception e){
            return  new String("Exception: "+e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){
        try {
            JSONObject data = new JSONObject(result);
            JSONArray mensajes = data.getJSONArray("dades");
            for(int i=0; i<mensajes.length(); i++){
                Mensaje m = new Mensaje();
                m.setIdMessage(mensajes.getJSONObject(i).getString("codimissatge"));
                m.setText(mensajes.getJSONObject(i).getString("msg"));
                m.setDate(mensajes.getJSONObject(i).getString("datahora"));
                m.setNomUser(mensajes.getJSONObject(i).getString("nom"));
                m.setIdUser(mensajes.getJSONObject(i).getString("codiusuari"));
                bd.obre();
                if(bd.insertaMensaje(m.getIdMessage(), m.getText(), m.getDate(), m.getIdUser()) == -1){
                    Toast.makeText(MyWhatsapp.getAppContext(), "Error a l’afegir",
                            Toast.LENGTH_SHORT).show();
                }
                bd.tanca();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
