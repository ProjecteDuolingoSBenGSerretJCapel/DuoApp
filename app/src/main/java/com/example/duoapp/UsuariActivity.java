package com.example.duoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.duoapp.fragmentsCursos.MeusCursos;
import com.example.duoapp.fragmentsUsuari.PerfilUsuari;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UsuariActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuari);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Seleccionar cursos
        bottomNavigationView.setSelectedItemId(R.id.usuari);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.cursos:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.usuari:
                        return true;
                    case R.id.lliga:
                        startActivity(new Intent(getApplicationContext(), LligaActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.tenda:
                        startActivity(new Intent(getApplicationContext(), TendaActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        if (findViewById(R.id.cursos_fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            PerfilUsuari firstFragment = new PerfilUsuari();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.cursos_fragment_container, firstFragment).commit();
        }
    }
}