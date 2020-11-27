package com.example.duoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TendaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenda);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Seleccionar cursos
        bottomNavigationView.setSelectedItemId(R.id.tenda);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.cursos:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.usuari:
                        startActivity(new Intent(getApplicationContext(), UsuariActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.lliga:
                        startActivity(new Intent(getApplicationContext(), LligaActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.tenda:
                        return true;
                }
                return false;
            }
        });
    }
}