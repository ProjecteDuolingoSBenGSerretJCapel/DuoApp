package com.example.duoapp.fragmentsUsuari;

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

import java.util.ArrayList;
import java.util.List;


public class PerfilUsuari extends Fragment {

    private EditText txtIPUsuari;
    private EditText txtNomUsuari;
    private EditText txtContrasenaUsuari;

    private Button btCambiarIP, btCambiarNom, btCambiarContrasena;

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

        txtIPUsuari = v.findViewById(R.id.txtIPUsuari);
        txtNomUsuari = v.findViewById(R.id.txtNomUsuari);
        txtContrasenaUsuari = v.findViewById(R.id.txtContrasenaUsuari);
        txtContrasenaUsuari.setTransformationMethod(PasswordTransformationMethod.getInstance());

        btCambiarIP = v.findViewById(R.id.btCambiarIp);
        btCambiarNom = v.findViewById(R.id.btCambiarNom);
        btCambiarContrasena = v.findViewById(R.id.btCambiarContrasena);

        String ip = "1.1.1.1.1.1";
        String nomUsuari = "samir";
        String contrasenaUsuari = "contrasena";

        Usuari usuari = new Usuari(ip, nomUsuari, contrasenaUsuari);

        txtIPUsuari.setText(usuari.getIp());
        txtNomUsuari.setText(usuari.getNom());
        txtContrasenaUsuari.setText(usuari.getContrasena());

        btCambiarIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtContrasenaUsuari.isEnabled()) {
                    String novaIp = txtContrasenaUsuari.getText().toString();
                    usuari.setContrasena(novaIp);

                    //cambiar tambe al fitxer de configuracio

                    txtContrasenaUsuari.setEnabled(false);
                }

                else {
                    txtContrasenaUsuari.setEnabled(true);
                }
            }
        });

        btCambiarNom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtNomUsuari.isEnabled()) {
                    String nouNom = txtNomUsuari.getText().toString();
                    usuari.setContrasena(nouNom);

                    //cambiar tambe al fitxer de configuracio

                    txtNomUsuari.setEnabled(false);
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

                    //cambiar tambe al fitxer de configuracio

                    txtContrasenaUsuari.setEnabled(false);
                }

                else {
                    txtContrasenaUsuari.setEnabled(true);

                }
            }
        });



        return v;
    }
}