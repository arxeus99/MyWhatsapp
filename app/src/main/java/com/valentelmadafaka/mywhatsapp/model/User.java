package com.valentelmadafaka.mywhatsapp.model;

import java.io.Serializable;

public class User implements Serializable {

    private String codiUsuari;
    private String nom;
    private String email;
    private String token;


    public String getCodiUsuari() {
        return codiUsuari;
    }

    public void setCodiUsuari(String codiUsuari) {
        this.codiUsuari = codiUsuari;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "codiUsuari='" + codiUsuari + '\'' +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
