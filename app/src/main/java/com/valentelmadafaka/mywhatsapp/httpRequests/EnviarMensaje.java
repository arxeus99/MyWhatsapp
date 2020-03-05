package com.valentelmadafaka.mywhatsapp.httpRequests;

import android.os.AsyncTask;
import android.widget.Toast;

import com.valentelmadafaka.mywhatsapp.AsyncResponse;
import com.valentelmadafaka.mywhatsapp.MyWhatsapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class EnviarMensaje extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings){
        try{
            URL url = new URL("http://52.44.95.114/quepassaeh/server/public/missatge/");

            JSONObject params = new JSONObject();
            params.put("msg", strings[0]);
            params.put("codiusuari", strings[1]);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", strings[2]);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(params));

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();

            return responseCode+"";
        } catch (MalformedURLException e) {
            return e.getMessage();
        } catch (JSONException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private String getPostDataString(JSONObject params) throws JSONException, UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();

        while(itr.hasNext()){
            String key = itr.next();
            Object value = params.get(key);

            if(first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }

        return result.toString();
    }

    @Override
    protected void onPostExecute(String result){
        if(result == null){
            Toast.makeText(MyWhatsapp.getAppContext(),"asdsad", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(MyWhatsapp.getAppContext(),result, Toast.LENGTH_SHORT).show();
    }
}
