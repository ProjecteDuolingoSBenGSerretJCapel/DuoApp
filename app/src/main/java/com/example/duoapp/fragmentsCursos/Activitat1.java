package com.example.duoapp.fragments;

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


public class Activitat1 extends Fragment{

    Button btSortir, btSeguent;
    TextView txtTitul, txtUltimasDiapos;

    int ultimaDiapo , maximDiapo;

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
                    if(ultimaDiapo == maximDiapo-1){
                        btSeguent.setText("Finalitzar");
                    }
                    ultimaDiapo += 1;
                    txtUltimasDiapos.setText(String.valueOf(ultimaDiapo) + "/" + String.valueOf(maximDiapo));
                    txtTitul.setText("Titol del exercici " + String.valueOf(ultimaDiapo));
                }
            }
        });

        return v;

    }

}