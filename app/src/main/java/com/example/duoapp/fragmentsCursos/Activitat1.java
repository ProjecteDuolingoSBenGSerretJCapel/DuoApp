package com.example.duoapp.fragmentsCursos;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.duoapp.R;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Activitat1 extends Fragment{

    Button btSortir, btSeguent;
    TextView txtTitul, txtUltimasDiapos;

    Button btResposta1, btResposta2, btResposta3;

    int ultimaDiapo , maximDiapo;

    private String[] respostes = {"man","girl","woman"};
    private String respostaCorrecte = "man";

    public Activitat1() {
        // Required empty public constructor
    }

    public static Activitat1 newInstance(String param1, String param2) {
        Activitat1 fragment = new Activitat1();
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
        View v = inflater.inflate(R.layout.fragment_activitat1, container, false);

        btResposta1 = v.findViewById(R.id.btResposta1);
        btResposta2 = v.findViewById(R.id.btResposta2);
        btResposta3 = v.findViewById(R.id.btResposta3);

        List<String> listRespostes = Arrays.asList(respostes);
        Collections.shuffle(listRespostes);
        listRespostes.toArray(respostes);

        btResposta1.setText(respostes[0]);
        btResposta2.setText(respostes[1]);
        btResposta3.setText(respostes[2]);

        //per rebre informacio de un altre fragment
        Bundle bundle = getArguments();
        if(bundle != null){
            ultimaDiapo = bundle.getInt("fetes");
            maximDiapo = bundle.getInt("maxim");
        }

        //
        txtTitul = (TextView) v.findViewById(R.id.txtExercici);

        txtUltimasDiapos = (TextView) v.findViewById(R.id.ultimesDiapos);
        txtUltimasDiapos.setText(String.valueOf(ultimaDiapo) + "/" + String.valueOf(maximDiapo));

        //
        btSortir = (Button) v.findViewById(R.id.btSortirActivitat);
        btSeguent = (Button) v.findViewById(R.id.btSeguentActivitat);
        btSeguent.setEnabled(false);

        btSortir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categories quartFragment = new Categories();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.cursos_fragment_container, quartFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btSeguent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimaDiapo == maximDiapo){
                    Categories quartFragment = new Categories();

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.cursos_fragment_container, quartFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                else{
                    if(btSeguent.getText().equals("Comprobar")){
                        btSeguent.setEnabled(false);

                        if(ultimaDiapo == maximDiapo-1){
                            btSeguent.setText("Finalitzar");
                        }

                        btResposta1.setBackgroundColor(Color.parseColor("#53BF2D"));
                        btResposta2.setBackgroundColor(Color.parseColor("#53BF2D"));
                        btResposta3.setBackgroundColor(Color.parseColor("#53BF2D"));


                        ultimaDiapo += 1;
                        txtUltimasDiapos.setText(String.valueOf(ultimaDiapo) + "/" + String.valueOf(maximDiapo));
                        txtTitul.setText("Titol del exercici " + String.valueOf(ultimaDiapo));

                        List<String> listRespostes = Arrays.asList(respostes);
                        Collections.shuffle(listRespostes);
                        listRespostes.toArray(respostes);

                        btResposta1.setText(respostes[0]);
                        btResposta2.setText(respostes[1]);
                        btResposta3.setText(respostes[2]);
                    }
                    else{
                        btSeguent.setEnabled(false);
                    }
                }

            }
        });

        btResposta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btResposta1.isClickable()){
                    btResposta1.setBackgroundColor(Color.parseColor("#90A4AE"));

                    btResposta2.setBackgroundColor(Color.parseColor("#53BF2D"));
                    btResposta3.setBackgroundColor(Color.parseColor("#53BF2D"));

                    if(btSeguent.getText().equals("Comprobar")){
                        btSeguent.setEnabled(true);
                        if(btSeguent.isClickable()){
                            if(btResposta1.getText().equals(respostaCorrecte)){
                                Snackbar.make(v, "Correcte", Snackbar.LENGTH_LONG).show();
                            }
                            else{
                                Snackbar.make(v, "Incorrecte", Snackbar.LENGTH_LONG).show();
                            }
                        }
                    }

                }
            }
        });

        btResposta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btResposta2.isClickable()){
                    btResposta2.setBackgroundColor(Color.parseColor("#90A4AE"));

                    btResposta1.setBackgroundColor(Color.parseColor("#53BF2D"));
                    btResposta3.setBackgroundColor(Color.parseColor("#53BF2D"));

                    if(btSeguent.getText().equals("Comprobar")){
                        btSeguent.setEnabled(true);
                        if(btSeguent.isClickable()){
                            if(btResposta2.getText().equals(respostaCorrecte)){
                                Snackbar.make(v, "Correcte", Snackbar.LENGTH_LONG).show();
                            }
                            else{
                                Snackbar.make(v, "Incorrecte", Snackbar.LENGTH_LONG).show();
                            }
                        }
                    }

                }
            }
        });

        btResposta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btResposta3.isClickable()){
                    btSeguent.setText("Comprobar");
                    btResposta3.setBackgroundColor(Color.parseColor("#90A4AE"));

                    btResposta2.setBackgroundColor(Color.parseColor("#53BF2D"));
                    btResposta1.setBackgroundColor(Color.parseColor("#53BF2D"));

                    if(btSeguent.getText().equals("Comprobar")){
                        btSeguent.setEnabled(true);
                        if(btSeguent.isClickable()){
                            if(btResposta3.getText().equals(respostaCorrecte)){
                                Snackbar.make(v, "Correcte", Snackbar.LENGTH_LONG).show();
                            }
                            else{
                                Snackbar.make(v, "Incorrecte", Snackbar.LENGTH_LONG).show();
                            }
                        }
                    }

                }
            }
        });

        return v;

    }

}