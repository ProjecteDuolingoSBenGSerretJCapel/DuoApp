package com.example.duoapp.fragmentsUsuari;

import android.content.Intent;
import android.graphics.Color;
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


public class PerfilUsuari extends Fragment {

    private EditText txtIPUsuari;
    private EditText txtNomUsuari;
    private EditText txtContrasenaUsuari;

    private TextView txtPuntsUsuari;
    private TextView txtMonedesUsuari;

    private Button btCambiarIP, btCambiarNom, btCambiarContrasena;

    private String ip, nom, contrasena, monedes, punts;

    private Document doc;
    private String path;
    private File file;

    private int puntsObtinguts;
    private int mondesObtingudes;
    private boolean cambi;

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

        Intent intent = new Intent();

        intent.getIntExtra("monedesObtingudes", mondesObtingudes);
        intent.getIntExtra("puntsObtinguts", puntsObtinguts);
        intent.getBooleanExtra("cambi", cambi);

        /*
        Bundle bundle = getArguments();
        if(bundle != null){
            mondesObtingudes = bundle.getInt("fetes");
            puntsObtinguts = bundle.getInt("maxim");
        }
        System.out.println(bundle);
        */


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
                    monedes = icmanagerConfigure.getValueTag(element, "Monedes");
                    punts = icmanagerConfigure.getValueTag(element, "Punts");
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
        String monedesUsuari= monedes;
        String puntsUsuari = punts;

        Usuari usuari = new Usuari(ipUsuari, nomUsuari, contrasenaUsuari, monedesUsuari,puntsUsuari);

        txtIPUsuari.setText(usuari.getIp());
        txtNomUsuari.setText(usuari.getNom());
        txtContrasenaUsuari.setText(usuari.getContrasena());

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

        System.out.println(cambi);
        System.out.println(mondesObtingudes);
        System.out.println(puntsObtinguts);

        if(cambi){
            icmanagerConfigure.modificarXML(doc, "Monedes",monedesUsuari);
            try {
                try {
                    txtMonedesUsuari.setText(String.valueOf(mondesObtingudes));
                    txtPuntsUsuari.setText(String.valueOf(puntsObtinguts));
                    icmanagerConfigure.guardarXML(doc, path,file, ipUsuari, nomUsuari, contrasenaUsuari, String.valueOf(mondesObtingudes),String.valueOf(puntsObtinguts));
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }

        return v;
    }

}