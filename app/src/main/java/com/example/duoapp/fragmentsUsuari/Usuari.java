package com.example.duoapp.fragmentsUsuari;

public class Usuari {

    private String ip;
    private String nom;
    private String contrasena;
    private int monedes;
    private int punts;

    public Usuari(){

    }

    public Usuari(String ip, String nom, String contrasena, int monedes, int punts){
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

    public void setMonedes(int monedes){
        this.monedes = monedes;
    }

    public int getMonedes(){
        return this.monedes;
    }

    public void setPunts(int punts){
        this.punts = punts;
    }

    public int getPunts(){
        return punts;
    }
}
