package com.valentelmadafaka.mywhatsapp.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.valentelmadafaka.mywhatsapp.R;

import java.util.ArrayList;

public class MensajesArray extends ArrayAdapter<Mensaje> {


    private Context context;
    private ArrayList<Mensaje> mensajeArrayList;

    public MensajesArray(Context context, int resource, ArrayList<Mensaje> mensajes){
        super(context, resource, mensajes);
        this.context = context;
        this.mensajeArrayList = mensajes;

    }

    public View getView(int position, View convertView, ViewGroup parent){
        Mensaje mensaje = mensajeArrayList.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.mensaje, null);
        TextView id = (TextView) view.findViewById(R.id.idUser);
        TextView nom = (TextView) view.findViewById(R.id.nom);
        TextView text = (TextView) view.findViewById(R.id.messageText);
        TextView date = (TextView) view.findViewById(R.id.date);

        id.setText(mensaje.getIdUser());
        nom.setText(mensaje.getNomUser());
        text.setText(mensaje.getText());
        date.setText(mensaje.getDate());

        return view;
    }
}
