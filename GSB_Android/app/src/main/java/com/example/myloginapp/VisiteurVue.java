package com.example.myloginapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VisiteurVue extends AppCompatActivity {
    private static final String TAG = VisiteurVue.class.getSimpleName();

    private final String url = "https://projet-gsb.francois-talaban.com/visualisation.php";

    public VisiteurVue() {
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiteur_vue);

// Récupérer l'email de l'intent
        String email = UserData.getInstance().getUserVar();






// Appel de la méthode getData() en passant l'email en tant que paramètre
        getData(email);


    }

    private String nom_utilisateur, prenom_utilisateur, id_utilisateur, id_fiche_frais, statut_validation, created_at_frais, description_frais, total_ff;

    private void getData(String email) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        @SuppressLint("SetTextI18n") StringRequest mStringRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);

                JSONArray jsonArray = jsonResponse.getJSONArray("fiches_frais");

// Supprimer les boutons existants
                @SuppressLint("CutPasteId") LinearLayout buttonLayout = findViewById(R.id.button_layout);
                @SuppressLint("CutPasteId") LinearLayout linearLayout = findViewById(R.id.button_layout);

// 16dp d'espacement en haut et en bas
                linearLayout.setPadding(0, 16, 0, 16);

                buttonLayout.removeAllViews();

                // Créer le TextView pour la création de la fiche de frais
                TextView titleTextView = new TextView(VisiteurVue.this);
                titleTextView.setText("Visualisation des fiche frais");
                titleTextView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                titleTextView.setGravity(Gravity.CENTER);
                titleTextView.setTextColor(getResources().getColor(R.color.white));
                titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
                titleTextView.setTypeface(null, Typeface.BOLD);
                titleTextView.setPadding(50, 50, 50, 20);
                linearLayout.addView(titleTextView);

// Parcourir tous les éléments de la liste et créer un bouton pour chaque élément
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String nom_utilisateur = jsonObject.getString("Nom_utilisateur");
                    String prenom_utilisateur = jsonObject.getString("Prenom_utilisateur");
                    String id_utilisateur = jsonObject.getString("ID_utilisateur");
                    String id_fiche_frais = jsonObject.getString("ID_fiche_frais");
                    String statut_validation = jsonObject.getString("Statut_validation");
                    String created_at_frais = jsonObject.getString("Created_at_frais");
                    String description_frais = jsonObject.getString("Description_frais");
                    String total_ff = jsonObject.getString("total_ff");

// Créer un bouton pour l'élément
                    Button button = new Button(VisiteurVue.this);
                    button.setText("Fiche de frais numéro " + id_fiche_frais);
                    button.setOnClickListener(v -> {
// Créer une vue pour afficher les informations de la fiche de frais
                        LinearLayout layout = new LinearLayout(VisiteurVue.this);
                        layout.setOrientation(LinearLayout.VERTICAL);
                        layout.setPadding(20, 20, 20, 20);

// Ajouter chaque champ à la vue
                        TextView nomTextView = new TextView(VisiteurVue.this);
                        nomTextView.setText("Nom:" + nom_utilisateur);
                        nomTextView.setPadding(10, 0, 0, 50);
                        layout.addView(nomTextView);

                        TextView prenomTextView = new TextView(VisiteurVue.this);
                        prenomTextView.setText("Prénom:" + prenom_utilisateur);
                        prenomTextView.setPadding(10, 0, 0, 50);
                        layout.addView(prenomTextView);

                        TextView idUtilisateurTextView = new TextView(VisiteurVue.this);
                        idUtilisateurTextView.setText("ID utilisateur: " + id_utilisateur);
                        idUtilisateurTextView.setPadding(10, 0, 0, 50);
                        layout.addView(idUtilisateurTextView);

                        TextView idFicheFraisTextView = new TextView(VisiteurVue.this);
                        idFicheFraisTextView.setText("ID fiche frais:" + id_fiche_frais);
                        idFicheFraisTextView.setPadding(10, 0, 0, 50);
                        layout.addView(idFicheFraisTextView);

                        TextView statutValidationTextView = new TextView(VisiteurVue.this);
                        statutValidationTextView.setText("Statut validation: " + statut_validation);
                        statutValidationTextView.setPadding(10, 0, 0, 50);
                        layout.addView(statutValidationTextView);

                        TextView createdAtFraisTextView = new TextView(VisiteurVue.this);
                        createdAtFraisTextView.setText("Created at frais: " + created_at_frais);
                        createdAtFraisTextView.setPadding(10, 0, 0, 50);
                        layout.addView(createdAtFraisTextView);

                        TextView descriptionFraisTextView = new TextView(VisiteurVue.this);
                        descriptionFraisTextView.setText("Description frais: " + description_frais);
                        descriptionFraisTextView.setPadding(10, 0, 0, 50);
                        layout.addView(descriptionFraisTextView);

                        TextView totalFfTextView = new TextView(VisiteurVue.this);
                        totalFfTextView.setText("Total frais: " + total_ff);
                        totalFfTextView.setPadding(10, 0, 0, 50);
                        layout.addView(totalFfTextView);

// Afficher la vue dans une boîte de dialogue
                        AlertDialog.Builder builder = new AlertDialog.Builder(VisiteurVue.this);
                        builder.setTitle("Informations de la fiche de frais");
                        builder.setView(layout);
                        builder.setPositiveButton("OK", null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    });


// Ajouter le bouton au layout
                    buttonLayout.addView(button);
                }

            } catch (JSONException e) {
                Log.e(TAG, "Error while parsing JSON response: " + e.getMessage());
                Toast.makeText(getApplicationContext(), "Error while parsing JSON response", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            Log.i(TAG, "Error :" + error.toString());
            Toast.makeText(getApplicationContext(), "Error while making request", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                return params;
            }
        };

        mRequestQueue.add(mStringRequest);
    }

    @Override
    public void onBackPressed() {
        // Votre code personnalisé pour le comportement de retour
        Intent i = new Intent(VisiteurVue.this, Visiteur.class);
        startActivity(i);
        // Exemple : fermer l'activité actuelle et revenir à l'activité précédente
        finish();
    }

}