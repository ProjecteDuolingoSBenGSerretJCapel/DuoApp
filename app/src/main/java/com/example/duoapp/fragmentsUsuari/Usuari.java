package com.example.duoapp.fragmentsUsuari;

public class Usuari {

    private String ip;
    private String nom;
    private String contrasena;
    private String monedes;
    private String punts;

    public Usuari(){

    }

    public Usuari(String ip, String nom, String contrasena, String monedes, String punts){
        this.ip = ip;
        this.nom = nom;
        this.contrasena = contrasena;
        this.monedes = monedes;
        this.punts = punts;
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

    public void setMonedes(String monedes){
        this.monedes = monedes;
    }

    public String getMonedes(){
        return this.monedes;
    }

    public void setPunts(String punts){
        this.punts = punts;
    }

    public String getPunts(){
        return punts;
    }
}
