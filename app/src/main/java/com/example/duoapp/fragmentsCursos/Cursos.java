package com.example.duoapp.fragmentsCursos;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.duoapp.MainActivity;
import com.example.duoapp.R;
import com.example.duoapp.fragmentsCursos.Client.IClientRMI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lipermi.handler.CallHandler;
import lipermi.net.Client;

public class Cursos extends Fragment {

    Spinner spinnerIdiomes;
    ListView listIdiomes;
    List<String> totsElsIdiomes;
    String[] arrayIdiomes;

    public Cursos() {
        // Required empty public constructor
    }

    public static Cursos newInstance(String param1, String param2) {
        Cursos fragment = new Cursos();
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
        View v = inflater.inflate(R.layout.fragment_cursos, container, false);

        totsElsIdiomes = new ArrayList<String>();
        arrayIdiomes = new String[totsElsIdiomes.size()];

        arrayIdiomes = totsElsIdiomes.toArray(arrayIdiomes);

        listIdiomes = v.findViewById(R.id.cursosList);
        //ListAdapter adapter = new ListAdapter(this, listIdiomes, List<totsElsIdiomes>);

        //listIdiomes.setAdapter(adapter);
        //spinnerIdiomes = (Spinner) v.findViewById(R.id.spinnerIdiomes);

        //spinnerIdiomes.setSelection(0);
        return  v;
    }

    class client extends AsyncTask<Void, Void, MainActivity> {

        @Override
        protected MainActivity doInBackground(Void... voids) {
            try{
                CallHandler callHandler = new CallHandler();

                int port = 15015;
                Client client = new Client("ip servidor", port, callHandler);
                IClientRMI inter = (IClientRMI) client.getGlobal(IClientRMI.class);

                totsElsIdiomes = inter.getTotsIdiomes();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}