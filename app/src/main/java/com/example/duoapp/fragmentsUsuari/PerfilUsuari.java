package com.example.duoapp.fragmentsUsuari;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duoapp.R;


public class PerfilUsuari extends Fragment {


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
        return v;
    }
}