package com.example.duoapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.duoapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MeusCursos extends Fragment {

    FloatingActionButton btAddIdioma;
    ListView meusCursosList;

    public MeusCursos() {
        // Required empty public constructor
    }

    public static MeusCursos newInstance(String param1, String param2) {
        MeusCursos fragment = new MeusCursos();
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
        View v = inflater.inflate(R.layout.fragment_meus_cursos, container, false);

        btAddIdioma = v.findViewById(R.id.btAddIdioma);
        btAddIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursos segondFragment = new Cursos();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.cursos_fragment_container, segondFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        meusCursosList = v.findViewById(R.id.meusCursosList);
        meusCursosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Categories tercerFragment = new Categories();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.cursos_fragment_container, tercerFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return v;
    }
}