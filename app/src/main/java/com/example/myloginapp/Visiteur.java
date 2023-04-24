package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Visiteur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiteur);

        Button Creationutton = findViewById(R.id.creer);
        Button ConsulterButton = findViewById(R.id.consulter);
        Button logoutButton = findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Ajoutez le code pour déconnecter l'utilisateur de votre application ici
                Intent logout = new Intent(Visiteur.this, MainActivity.class);
                startActivity(logout);
                finish();
            }
        });

        Creationutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Ajoutez le code pour déconnecter l'utilisateur de votre application ici
                Intent logout = new Intent(Visiteur.this, VisiteurCreation.class);
                startActivity(logout);
                finish();
            }
        });

        ConsulterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Ajoutez le code pour déconnecter l'utilisateur de votre application ici
                Intent logout = new Intent(Visiteur.this, VisiteurConsultation.class);
                startActivity(logout);
                finish();
            }
        });


    }
}