package com.example.duoapp.fragmentsCursos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duoapp.R;
import com.google.android.material.button.MaterialButton;

public class Categories extends Fragment {

    MaterialButton btActivitat1;
    TextView cantDiapos, txtTitul;

    public Categories() {
        // Required empty public constructor
    }

    public static Categories newInstance(String param1, String param2) {
        Categories fragment = new Categories();
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
        View v = inflater.inflate(R.layout.fragment_categories, container, false);

        cantDiapos = (TextView) v.findViewById(R.id.cantDiapos);

        int numUltimaFragment = obtenirUltimFragmentsActivitat(cantDiapos);
        int cantFragmentsActivitat = obtenirCantFragmentsActivitat(cantDiapos);

        btActivitat1 = v.findViewById(R.id.activitat1);
        btActivitat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activitat1 quartFragment = new Activitat1();

                //per pasar informacio de fragment a fragment
                Bundle bundle = new Bundle();
                bundle.putInt("fetes", numUltimaFragment);
                bundle.putInt("maxim", cantFragmentsActivitat);

                quartFragment.setArguments(bundle);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.cursos_fragment_container, quartFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return v;
    }

    public static int obtenirUltimFragmentsActivitat(TextView textView){
        char num = textView.getText().charAt(0);
        return Integer.parseInt(Character.toString(num));
    }

    public static int obtenirCantFragmentsActivitat(TextView textView){
        char num = textView.getText().charAt(2);
        return Integer.parseInt(Character.toString(num));
    }

}