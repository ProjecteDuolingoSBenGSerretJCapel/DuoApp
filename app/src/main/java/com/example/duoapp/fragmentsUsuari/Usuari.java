package com.example.duoapp.fragmentsUsuari;

public class Usuari {

    private String ip;
    private String nom;
    private String contrasena;

    public Usuari(){

    }

    public Usuari(String ip, String nom, String contrasena){
        this.ip = ip;
        this.nom = nom;
        this.contrasena = contrasena;
    }

    public void setIp(String ip){
        this.ip = ip;
    }

    public String getIp(){
        return this.ip;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public String getNom(){
        return this.nom;
    }

    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }

    public String getContrasena(){
        return this.contrasena;
    }
}
