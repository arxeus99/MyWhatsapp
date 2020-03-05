package com.valentelmadafaka.mywhatsapp.httpRequests;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.AsyncTask;

import com.valentelmadafaka.mywhatsapp.AsyncResponse;
import com.valentelmadafaka.mywhatsapp.model.User;
import com.valentelmadafaka.mywhatsapp.utils.PreferencesHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class Logear extends AsyncTask<String, Void, String[]> {

    public AsyncResponse delegate = null;

    protected void onPreExecute(){}

    @Override
    protected String[] doInBackground(String... strings) {
        try{
            URL url = new URL("http://52.44.95.114/quepassaeh/server/public/login/");

            JSONObject params = new JSONObject();
            params.put("email", strings[0]);
            params.put("password", strings[1]);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(params));

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null){
                    sb.append(line);
                    break;
                }

                in.close();
                String[] toReturn = {sb.toString(), strings[0], strings[1]};
                return toReturn;
            }else{
                return new String[]{"false: "+responseCode};
            }

        } catch (Exception e){
            return new String[]{"Exception: "+e.getMessage()};
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
    protected void onPostExecute(String[] result){
        if(result != null){
            try{
                JSONObject jsonObject = new JSONObject(result[0]);
                if(jsonObject.getBoolean("correcta")){
                    JSONObject toSave = new JSONObject();
                    toSave.put("email", result[1]);
                    toSave.put("password", result[2]);
                    PreferencesHelper.desaArrayList("Info Login", toSave);
                    User user = new User();
                    JSONObject info = jsonObject.getJSONObject("dades");
                    PreferencesHelper.desaArrayList("Info Usuari",info);
                    delegate.processFinish("200");
                }else{
                    delegate.processFinish("no 200");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
