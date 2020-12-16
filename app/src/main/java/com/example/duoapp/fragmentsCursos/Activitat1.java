package com.example.duoapp.fragmentsCursos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duoapp.MainActivity;
import com.example.duoapp.R;
import com.example.duoapp.UsuariActivity;
import com.example.duoapp.fragmentsUsuari.PerfilUsuari;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Activitat1 extends Fragment{

    private Button btSortir, btSeguent;
    private TextView txtTitul, txtUltimasDiapos;

    private Button btResposta1, btResposta2, btResposta3;

    private int ultimaDiapo , maximDiapo;

    private String resposta;
    private String r;

    private String[] respostes = {"man","girl","woman"};
    private String respostaCorrecte = "man";

    private boolean totCorrecte = true;

    private int monedesObtingudes, puntsObtinguts;

    private EditText txtTraduir;
    private TextView txtTitulTraduir;

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

        Bundle bundle1 = new Bundle();

        //
        txtTitul = (TextView) v.findViewById(R.id.txtExercici);

        txtUltimasDiapos = (TextView) v.findViewById(R.id.ultimesDiapos);
        txtUltimasDiapos.setText(String.valueOf(ultimaDiapo) + "/" + String.valueOf(maximDiapo));

        //
        btSortir = (Button) v.findViewById(R.id.btSortirActivitat);
        btSeguent = (Button) v.findViewById(R.id.btSeguentActivitat);
        btSeguent.setEnabled(false);

        //
        txtTraduir = (EditText) v.findViewById(R.id.txtTraduir);
        txtTitulTraduir = (TextView) v.findViewById(R.id.txtTitulTraduir);

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
                    if(resposta.equals(respostaCorrecte)){
                        btSeguent.setEnabled(false);
                        Snackbar snackbar = Snackbar
                                .make(container, "Correcte", Snackbar.LENGTH_LONG)
                                .setAction("SEGUENT", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(ultimaDiapo == maximDiapo){
                                            txtTitul.setText("PUNTS OBTINGUTS, "+ monedesObtingudes +" Punts, MONEDES OBTINGUDES "+ puntsObtinguts +" Monedes");
                                            btResposta3.setText("OK");

                                            btResposta2.setVisibility(View.INVISIBLE);
                                            btResposta1.setVisibility(View.INVISIBLE);
                                            btSortir.setVisibility(View.INVISIBLE);
                                            btSeguent.setVisibility(View.INVISIBLE);

                                            txtUltimasDiapos.setVisibility(View.INVISIBLE);

                                            btResposta3.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent i = new Intent(getActivity(), UsuariActivity.class);
                                                    i.putExtra("puntsObtinguts", puntsObtinguts);
                                                    i.putExtra("monedesObtingudes", monedesObtingudes);
                                                    i.putExtra("cambi", true);

                                                    Categories quartFragment = new Categories();

                                                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                    transaction.replace(R.id.cursos_fragment_container, quartFragment);
                                                    transaction.addToBackStack(null);
                                                    transaction.commit();
                                                }
                                            });

                                        }
                                        else{
                                            int puntsRandom = (int) (Math.random()*50+50);
                                            int monedesRandom = (int) (Math.random()*50+50);

                                            puntsObtinguts += puntsRandom;
                                            monedesObtingudes += monedesRandom;

                                            if(ultimaDiapo == 5){
                                                if(ultimaDiapo == maximDiapo-1){
                                                    btSeguent.setText("Finalitzar");
                                                }
                                                ultimaDiapo += 1;
                                                txtUltimasDiapos.setText(String.valueOf(ultimaDiapo) + "/" + String.valueOf(maximDiapo));
                                                txtTitul.setText("Titol del exercici " + String.valueOf(ultimaDiapo));

                                                btResposta1.setBackgroundColor(Color.parseColor("#53BF2D"));
                                                btResposta2.setBackgroundColor(Color.parseColor("#53BF2D"));
                                                btResposta3.setBackgroundColor(Color.parseColor("#53BF2D"));

                                                List<String> listRespostes = Arrays.asList(respostes);
                                                Collections.shuffle(listRespostes);
                                                listRespostes.toArray(respostes);

                                                btResposta1.setText(respostes[0]);
                                                btResposta2.setText(respostes[1]);
                                                btResposta3.setText(respostes[2]);
                                            }
                                            else if(ultimaDiapo == 1){
                                                if(ultimaDiapo == maximDiapo-1){
                                                    btSeguent.setText("Finalitzar");
                                                }
                                                ultimaDiapo += 1;
                                                txtUltimasDiapos.setText(String.valueOf(ultimaDiapo) + "/" + String.valueOf(maximDiapo));
                                                txtTitul.setText("Hello, how are you?");

                                                txtTraduir.setVisibility(View.VISIBLE);
                                                txtTitulTraduir.setVisibility(View.VISIBLE);

                                                btSeguent.setEnabled(true);

                                                respostaCorrecte = "holacomoestas";


                                                btSeguent.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        //r = "hola como estas";
                                                        r = txtTraduir.getText().toString();
                                                        r = r.replace(" ", "").replace(",", "").replace(";", "").replace(":", "").replace(",", "").replace("!", "")
                                                                .replace("'", "").replace("·", "").replace("$", "").replace("%", "").replace("&", "").replace("/", "")
                                                                .replace("(", "").replace(")", "").replace("=", "").replace("?", "").replace("¿", "").replace("¡", "");

                                                        if(respostaCorrecte.equalsIgnoreCase(r)){
                                                            System.out.println("Si");
                                                            System.out.println(r);
                                                        }
                                                        else{
                                                            System.out.println("No");
                                                            System.out.println(r);
                                                        }
                                                    }
                                                });
                                                btResposta1.setVisibility(View.INVISIBLE);
                                                btResposta2.setVisibility(View.INVISIBLE);
                                                btResposta3.setVisibility(View.INVISIBLE);
                                            }
                                        }

                                    }
                                });

                        snackbar.show();
                    }
                    else{
                        totCorrecte = false;

                        btSeguent.setEnabled(false);
                        Snackbar snackbar = Snackbar
                                .make(container, "Incorrecte", Snackbar.LENGTH_LONG)
                                .setAction("SEGUENT", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(ultimaDiapo == maximDiapo){
                                            monedesObtingudes += 150;

                                            txtTitul.setText("PUNTS OBTINGUTS, "+ monedesObtingudes +" Punts, MONEDES OBTINGUDES "+ puntsObtinguts +" Monedes");
                                            btResposta3.setText("OK");

                                            btResposta2.setVisibility(View.INVISIBLE);
                                            btResposta1.setVisibility(View.INVISIBLE);
                                            btSortir.setVisibility(View.INVISIBLE);
                                            btSeguent.setVisibility(View.INVISIBLE);

                                            txtUltimasDiapos.setVisibility(View.INVISIBLE);

                                            btResposta3.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent i = new Intent(getActivity(), UsuariActivity.class);
                                                    i.putExtra("puntsObtinguts", 100);
                                                    i.putExtra("monedesObtingudes", 100);
                                                    i.putExtra("cambi", true);

                                                    Categories quartFragment = new Categories();

                                                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                    transaction.replace(R.id.cursos_fragment_container, quartFragment);
                                                    transaction.addToBackStack(null);
                                                    transaction.commit();
                                                }
                                            });

                                        }
                                        else{
                                            int puntsRandom = (int) (Math.random()*50+50);
                                            int monedesRandom = (int) (Math.random()*50+50);

                                            puntsObtinguts += puntsRandom;
                                            monedesObtingudes += monedesRandom;

                                            if(ultimaDiapo == 5){
                                                if(ultimaDiapo == maximDiapo-1){
                                                    btSeguent.setText("Finalitzar");
                                                }
                                                ultimaDiapo += 1;
                                                txtUltimasDiapos.setText(String.valueOf(ultimaDiapo) + "/" + String.valueOf(maximDiapo));
                                                txtTitul.setText("Titol del exercici " + String.valueOf(ultimaDiapo));

                                                btResposta1.setBackgroundColor(Color.parseColor("#53BF2D"));
                                                btResposta2.setBackgroundColor(Color.parseColor("#53BF2D"));
                                                btResposta3.setBackgroundColor(Color.parseColor("#53BF2D"));

                                                List<String> listRespostes = Arrays.asList(respostes);
                                                Collections.shuffle(listRespostes);
                                                listRespostes.toArray(respostes);

                                                btResposta1.setText(respostes[0]);
                                                btResposta2.setText(respostes[1]);
                                                btResposta3.setText(respostes[2]);
                                            }
                                            else if(ultimaDiapo == 1){
                                                if(ultimaDiapo == maximDiapo-1){
                                                    btSeguent.setText("Finalitzar");
                                                }
                                                ultimaDiapo += 1;
                                                txtTitul.setText("Hello, how are you?");

                                                txtTraduir.setVisibility(View.VISIBLE);
                                                txtTitulTraduir.setVisibility(View.VISIBLE);

                                                btSeguent.setEnabled(true);

                                                respostaCorrecte = "holacomoestas";

                                                btSeguent.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        //r = "hola como estas";
                                                        r = txtTraduir.getText().toString();
                                                        r = r.replace(" ", "").replace(",", "").replace(";", "").replace(":", "").replace(",", "").replace("!", "")
                                                                .replace("'", "").replace("·", "").replace("$", "").replace("%", "").replace("&", "").replace("/", "")
                                                                .replace("(", "").replace(")", "").replace("=", "").replace("?", "").replace("¿", "").replace("¡", "");


                                                        if(respostaCorrecte.equalsIgnoreCase(r)){
                                                            System.out.println("Si");
                                                            System.out.println(r);
                                                        }
                                                        else{
                                                            System.out.println("No");
                                                            System.out.println(r);
                                                        }
                                                    }
                                                });


                                                btResposta1.setVisibility(View.INVISIBLE);
                                                btResposta2.setVisibility(View.INVISIBLE);
                                                btResposta3.setVisibility(View.INVISIBLE);
                                            }

                                        }

                                    }
                                });

                        snackbar.show();
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

                    if(btResposta1.isClickable()){
                        resposta = btResposta1.getText().toString();
                        btSeguent.setEnabled(true);
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

                    if(btResposta2.isClickable()){
                        resposta = btResposta2.getText().toString();
                        btSeguent.setEnabled(true);
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

                    if(btResposta3.isClickable()){
                        resposta = btResposta3.getText().toString();
                        btSeguent.setEnabled(true);
                    }

                }
            }
        });

        return v;

    }


}