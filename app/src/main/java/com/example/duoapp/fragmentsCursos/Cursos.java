package com.example.duoapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.duoapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Cursos extends Fragment {

    Spinner spinnerIdiomes;
    ListView listIdiomes;

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

        //spinnerIdiomes = (Spinner) v.findViewById(R.id.spinnerIdiomes);

        //spinnerIdiomes.setSelection(0);
        return  v;
    }
}