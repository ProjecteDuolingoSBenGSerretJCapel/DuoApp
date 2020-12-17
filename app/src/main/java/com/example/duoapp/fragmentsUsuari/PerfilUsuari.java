package com.example.duoapp.fragmentsUsuari;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duoapp.R;
import com.example.duoapp.Recursos.SXmlConfigureDAO;
import com.example.duoapp.Recursos.SXmlConfigureImpl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


public class PerfilUsuari extends Fragment {

    private static Cipher rsa;

    private static boolean cambi;
    private static boolean totCorrecte;

    private EditText txtIPUsuari;
    private EditText txtNomUsuari;
    private EditText txtContrasenaUsuari;

    private TextView txtPuntsUsuari;
    private TextView txtMonedesUsuari;

    private Button btCambiarIP, btCambiarNom, btCambiarContrasena;

    private String ip, nom, contrasena;

    private Document doc;
    private String path;

    private int monedes, punts;

    public PerfilUsuari() {
        // Required empty public constructor
    }

    public static PerfilUsuari newInstance(String param1, String param2) {
        PerfilUsuari fragment = new PerfilUsuari();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil_usuari, container, false);

        SXmlConfigureDAO icmanagerConfigure = new SXmlConfigureImpl();
        path ="/data/user/0/com.example.duoapp/usuariXML.xml";

        File file = new File(path);
        try{
            doc = icmanagerConfigure.getMyGetDocument(path);
            doc.getDocumentElement().normalize();

            NodeList nodeList = icmanagerConfigure.returnNodeListByDocument(doc, "Configure");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node nodo = nodeList.item(temp);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = icmanagerConfigure.setElementByNode(nodo);

                    ip = icmanagerConfigure.getValueTag(element, "Ip");
                    nom = icmanagerConfigure.getValueTag(element, "Nom");
                    contrasena = icmanagerConfigure.getValueTag(element, "Contrasena");
                    monedes = Integer.valueOf(icmanagerConfigure.getValueTag(element, "Monedes"));
                    punts = Integer.valueOf(icmanagerConfigure.getValueTag(element, "Punts"));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }



        txtIPUsuari = v.findViewById(R.id.txtIPUsuari);
        txtNomUsuari = v.findViewById(R.id.txtNomUsuari);
        txtContrasenaUsuari = v.findViewById(R.id.txtContrasenaUsuari);
        txtContrasenaUsuari.setTransformationMethod(PasswordTransformationMethod.getInstance());

        btCambiarIP = v.findViewById(R.id.btCambiarIp);
        btCambiarNom = v.findViewById(R.id.btCambiarNom);
        btCambiarContrasena = v.findViewById(R.id.btCambiarContrasena);

        txtPuntsUsuari = v.findViewById(R.id.txtPuntsUsuari);
        txtMonedesUsuari = v.findViewById(R.id.txtMonedesUsuari);

        String ipUsuari = ip;
        String nomUsuari = nom;
        String contrasenaUsuari = contrasena;
        int monedesUsuari= monedes;
        int puntsUsuari = punts;

        Usuari usuari = new Usuari(ipUsuari, nomUsuari, contrasenaUsuari, monedesUsuari,puntsUsuari);

        txtIPUsuari.setText(usuari.getIp());
        txtNomUsuari.setText(usuari.getNom());
        txtContrasenaUsuari.setText(usuari.getContrasena());

        //encriptem la contrasena
        /*
        escriureFitcherContrasena(usuari.getContrasena());
        String cntrasenaFitcher = obtindreContrasena();
        try {
            encriptar(cntrasenaFitcher);
            System.out.println("hola si");
        } catch (Exception e) {
            System.out.println("hola no");
            e.printStackTrace();
        }
        */

        txtPuntsUsuari.setText(String.valueOf(usuari.getPunts()));
        txtMonedesUsuari.setText(String.valueOf(usuari.getMonedes()));

        btCambiarIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtIPUsuari.isEnabled()) {
                    String novaIp = txtIPUsuari.getText().toString();
                    usuari.setIp(novaIp);

                    txtIPUsuari.setEnabled(false);

                    icmanagerConfigure.modificarXML(doc, "Ip",novaIp);
                    try {
                        try {
                            icmanagerConfigure.guardarXML(doc, path,file, novaIp, nomUsuari, contrasenaUsuari, monedes,punts);
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        }
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    }
                }

                else {
                    txtIPUsuari.setEnabled(true);
                }
            }
        });

        btCambiarNom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtNomUsuari.isEnabled()) {
                    String nouNom = txtNomUsuari.getText().toString();
                    usuari.setNom(nouNom);

                    txtNomUsuari.setEnabled(false);

                    icmanagerConfigure.modificarXML(doc, "Nom",nouNom);
                    try {
                        try {
                            icmanagerConfigure.guardarXML(doc, path,file, ipUsuari, nouNom, contrasenaUsuari, monedes,punts);
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        }
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    }
                }

                else {
                    txtNomUsuari.setEnabled(true);
                }
            }
        });

        btCambiarContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtContrasenaUsuari.isEnabled()) {
                    String novaContrasena = txtContrasenaUsuari.getText().toString();
                    usuari.setContrasena(novaContrasena);

                    txtContrasenaUsuari.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    txtContrasenaUsuari.setEnabled(false);

                    icmanagerConfigure.modificarXML(doc, "Contrasena",novaContrasena);
                    try {
                        try {
                            icmanagerConfigure.guardarXML(doc, path,file, ipUsuari, nomUsuari, novaContrasena, monedes,punts);
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        }
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    }
                }

                else {
                    txtContrasenaUsuari.setEnabled(true);

                }
            }
        });

        System.out.println("hola!!!!");
        System.out.println(getCambi());
        System.out.println(getTotCorrecte());

        if(totCorrecte){
            monedes += 250;
            punts += 200;

            usuari.setPunts(punts);
            usuari.setMonedes(monedes);
        }
        else{
            monedes += 100;
            punts += 200;

            usuari.setPunts(punts);
            usuari.setMonedes(monedes);
        }
        if(cambi) {
            icmanagerConfigure.modificarXML(doc, "Monedes",String.valueOf(usuari.getMonedes()));
            icmanagerConfigure.modificarXML(doc, "Punts",String.valueOf(usuari.getPunts()));
            System.out.println("hola!!!!11111111111");
            System.out.println(usuari.getPunts());
            System.out.println(usuari.getMonedes());
            try {
                try {
                    txtMonedesUsuari.setText(String.valueOf(usuari.getMonedes()));
                    txtPuntsUsuari.setText(String.valueOf(usuari.getPunts()));
                    icmanagerConfigure.guardarXML(doc, path, file, ipUsuari, nomUsuari, contrasenaUsuari,usuari.getMonedes(),usuari.getPunts());
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }

        return v;
    }

    public static void setTotCorrecte(boolean totCorrecteBol){
        PerfilUsuari.totCorrecte = totCorrecteBol;
    }

    public static boolean getTotCorrecte(){
        return PerfilUsuari.totCorrecte;
    }

    public static void setCambi(boolean cambiMonedesPunts) {
        PerfilUsuari.cambi = cambiMonedesPunts;
    }

    public static boolean getCambi(){
        return PerfilUsuari.cambi;
    }

    public String obtindreContrasena(){
        String text = "";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File ("/data/user/0/com.example.duoapp/contrasenaFithcerUsuari.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null)
                text = linea;
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

        return text;
    }
    public void escriureFitcherContrasena(String contrasena){
        /** FORMA 1 DE ESCRITURA **/
        FileWriter fichero = null;
        try {

            fichero = new FileWriter("/data/user/0/com.example.duoapp/contrasenaFithcerUsuari.txt");

            //escribim el fitcer la contrasena
            fichero.write(contrasena);

            fichero.close();

        } catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        }
    }
    public void encriptar(String txtAEncriptar) throws Exception {
        // Generar el par de claves
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Se salva y recupera de fichero la clave publica
        saveKey(publicKey, "publickey.dat");
        publicKey = loadPublicKey("publickey.dat");

        // Se salva y recupera de fichero la clave privada
        saveKey(privateKey, "privatekey.dat");
        privateKey = loadPrivateKey("privatekey.dat");

        // Obtener la clase para encriptar/desencriptar
        rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // Texto a encriptar
        String text = null;

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File ("/data/user/0/com.example.duoapp/contrasenaFithcerUsuari.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null)
                text = linea;
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

        // Se encripta
        rsa.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encriptado = rsa.doFinal(text.getBytes());

        /** FORMA 1 DE ESCRITURA **/
        FileWriter fichero = null;
        try {

            fichero = new FileWriter("/data/user/0/com.example.duoapp/contrasenaFithcerUsuariEnc.txt");

            // Escribimos el encriptado para verlo, con caracteres visibles
            for (byte b : encriptado) {
                fichero.write(Integer.toHexString(0xFF & b));
            }

            fichero.close();

        } catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        }


        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File ("/home/super/Escriptori/fitcherContrasenaUsuariEnc.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null)
                text = linea;
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }


        // Se desencripta
        rsa.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytesDesencriptados = rsa.doFinal(encriptado);
        String textoDesencripado = new String(bytesDesencriptados);

        // Se escribe el texto desencriptado
        System.out.println(textoDesencripado);

    }

    private static PublicKey loadPublicKey(String fileName) throws Exception {
        FileInputStream fis = new FileInputStream(fileName);
        int numBtyes = fis.available();
        byte[] bytes = new byte[numBtyes];
        fis.read(bytes);
        fis.close();

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        KeySpec keySpec = new X509EncodedKeySpec(bytes);
        PublicKey keyFromBytes = keyFactory.generatePublic(keySpec);
        return keyFromBytes;
    }

    private static PrivateKey loadPrivateKey(String fileName) throws Exception {
        FileInputStream fis = new FileInputStream(fileName);
        int numBtyes = fis.available();
        byte[] bytes = new byte[numBtyes];
        fis.read(bytes);
        fis.close();

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        PrivateKey keyFromBytes = keyFactory.generatePrivate(keySpec);
        return keyFromBytes;
    }

    private static void saveKey(Key key, String fileName) throws Exception {
        byte[] publicKeyBytes = key.getEncoded();
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(publicKeyBytes);
        fos.close();
    }



}