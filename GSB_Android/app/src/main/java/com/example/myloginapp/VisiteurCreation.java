package com.example.myloginapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;

public class VisiteurCreation extends AppCompatActivity {

    EditText description, dateNew, dateFin, nombreNuit, coutNuit, nombreRepas, coutRepas, nombreKm, decriptionExtra, prixEtra;

    Button fichebtn;
    Spinner choixCv;

    String descriVar, dateDVar, dateFVar, nbnVar, cnVar, nbrVar, crVar, nbKm, ccVar, descExtra, prixExtra, user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiteur_creation);

        description = (EditText) findViewById(R.id.description);
        dateNew = (EditText) findViewById(R.id.dateNew);
        dateFin = (EditText) findViewById(R.id.dateFin);
        nombreNuit = (EditText) findViewById(R.id.nombreNuit);
        coutNuit = (EditText) findViewById(R.id.coutNuit);
        nombreRepas = (EditText) findViewById(R.id.nombreRepas);
        coutRepas = (EditText) findViewById(R.id.coutRepas);
        nombreKm = (EditText) findViewById(R.id.nombreKm);
        choixCv = (Spinner) findViewById(R.id.cvSelect);
        fichebtn = (Button) findViewById(R.id.ficheCreaButton);
        decriptionExtra = (EditText) findViewById(R.id.descriptionExtra);
        prixEtra = (EditText) findViewById(R.id.prixExtra);
        user = UserData.getInstance().getUserVar();






        dateNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewa) {
                final Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int day = currentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        viewa.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                                String selectedMonthFormatted = String.valueOf(selectedMonth + 1);
                                if (selectedMonth + 1 < 10) {
                                    selectedMonthFormatted = "0" + selectedMonthFormatted;
                                }
                                String selectedDayFormatted = String.valueOf(selectedDayOfMonth);
                                if (selectedDayOfMonth < 10) {
                                    selectedDayFormatted = "0" + selectedDayFormatted;
                                }
                                dateNew.setText(selectedYear + "-" + selectedMonthFormatted + "-" + selectedDayFormatted);
                            }
                        },
                        year,
                        month,
                        day
                );
                datePickerDialog.show();
            }
        });

        dateFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewb) {
                final Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int day = currentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        viewb.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                                String selectedMonthFormatted = String.valueOf(selectedMonth + 1);
                                if (selectedMonth + 1 < 10) {
                                    selectedMonthFormatted = "0" + selectedMonthFormatted;
                                }
                                String selectedDayFormatted = String.valueOf(selectedDayOfMonth);
                                if (selectedDayOfMonth < 10) {
                                    selectedDayFormatted = "0" + selectedDayFormatted;
                                }
                                dateFin.setText(selectedYear + "-" + selectedMonthFormatted + "-" + selectedDayFormatted);
                            }
                        },
                        year,
                        month,
                        day
                );
                datePickerDialog.show();
            }
        });



        fichebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewd) {

                descriVar = description.getText().toString();
                dateDVar = dateNew.getText().toString();
                dateFVar = dateFin.getText().toString();
                nbnVar = nombreNuit.getText().toString();
                cnVar = coutNuit.getText().toString();
                nbrVar = nombreRepas.getText().toString();
                crVar = coutRepas.getText().toString();
                nbKm = nombreKm.getText().toString();
                ccVar = choixCv.getItemAtPosition(1).toString();
                descExtra = decriptionExtra.getText().toString();
                prixExtra = prixEtra.getText().toString();









                if (ccVar.equals("(Véhicule 4CV Diesel) 0.52 €/Km")) {
                    ccVar = "4cv";
                } else if (ccVar.equals("(Véhicule 5CV Diesel) 0.58 €/Km")) {
                    ccVar = "5cv";
                } else if (ccVar.equals("(Véhicule 6CV Diesel) 0.62 €/Km")) {
                    ccVar = "6cv";
                } else if (ccVar.equals("(Véhicule 6CV Essence) 0.67 €/Km")) {
                    ccVar = "6cve";
                }



                if (descriVar != null) {
                    Log.d("TAG", descriVar);
                } else {
                    Log.d("TAG", "descriVar is null");
                }

                if (user != null) {
                    Log.d("TAG", user);
                } else {
                    Log.d("TAG", "descriVar is null");
                }

                if (dateDVar != null) {
                    Log.d("TAG", dateDVar);
                } else {
                    Log.d("TAG", "dateDVar is null");
                }

                if (dateFVar != null) {
                    Log.d("TAG", dateFVar);
                } else {
                    Log.d("TAG", "dateFVar is null");
                }

                if (nbnVar.equals("")) {
                    nbnVar = "0";
                }
                Log.d("TAG", nbnVar);

                if (cnVar.equals("")) {
                    cnVar = "0";
                }
                Log.d("TAG", cnVar);

                if (nbrVar.equals("")) {
                    nbrVar = "0";
                }
                Log.d("TAG", nbrVar);

                if (crVar.equals("")) {
                    crVar = "0";
                }
                Log.d("TAG", crVar);

                if (nbKm.equals("")) {
                    nbKm = "0";
                }
                Log.d("TAG", nbKm);

                if (ccVar.equals("")) {
                    ccVar = "NA";
                }
                Log.d("TAG", ccVar);

                if (descExtra.equals("")) {
                    descExtra = "NA";
                    prixExtra = "0";
                }
                    Log.d("TAG", descExtra);
                    Log.d("TAG", prixExtra);










                if (descriVar.equals("")) {
                    //identifiant incorrect
                    Toast.makeText(getApplicationContext(), "Saisir une description", Toast.LENGTH_SHORT).show();
                } else if (dateDVar.equals("")) {
                    //mot de passe incorrect
                    Toast.makeText(getApplicationContext(), "Saisir une date de debut", Toast.LENGTH_SHORT).show();
                } else if (dateFVar.equals("")) {
                    //mot de passe incorrect
                    Toast.makeText(getApplicationContext(), "Saisir une date de fin", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VisiteurCreation.this, "En cours connexion", Toast.LENGTH_SHORT).show();

                    Creation creat = new Creation(VisiteurCreation.this);
                    creat.execute();

                }

            }


        });
    }

    @Override
    public void onBackPressed() {
        // Votre code personnalisé pour le comportement de retour
        Intent i = new Intent(VisiteurCreation.this, Visiteur.class);
        startActivity(i);
        // Exemple : fermer l'activité actuelle et revenir à l'activité précédente
        finish();
    }

    class Creation extends AsyncTask<String, Void, Integer> {

        AlertDialog alertDialog;
        Context context;

        ProgressDialog progressDialog;


        Creation(VisiteurCreation ctx) {
            this.context = ctx;


        }


        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "En cours d'execution", Toast.LENGTH_SHORT).show();
        }



        @Override
        protected Integer doInBackground(String... params) {
            String creat_url = "https://projet-gsb.francois-talaban.com/creation.php";
            try {
                URL url = new URL(creat_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                if (user != null) {
                    Log.d("TAG", user);
                } else {
                    Log.d("TAG", "descriVar is null");
                }
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data =
                        "email=" + URLEncoder.encode(user, "UTF-8") + "&" +
                                URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(descriVar, "UTF-8") + "&" +
                                URLEncoder.encode("dateDebut", "UTF-8") + "=" + URLEncoder.encode(dateDVar, "UTF-8") + "&" +
                                URLEncoder.encode("dateFin", "UTF-8") + "=" + URLEncoder.encode(dateFVar, "UTF-8") + "&" +
                                URLEncoder.encode("nbNuit", "UTF-8") + "=" + URLEncoder.encode(nbnVar, "UTF-8") + "&" +
                                URLEncoder.encode("prixTotalNuit", "UTF-8") + "=" + URLEncoder.encode(cnVar, "UTF-8") + "&" +
                                URLEncoder.encode("nbRepas", "UTF-8") + "=" + URLEncoder.encode(nbrVar, "UTF-8") + "&" +
                                URLEncoder.encode("prixTotalRepas", "UTF-8") + "=" + URLEncoder.encode(crVar, "UTF-8") + "&" +
                                URLEncoder.encode("nbKm", "UTF-8") + "=" + URLEncoder.encode(nbKm, "UTF-8") + "&" +
                                URLEncoder.encode("cv-select", "UTF-8") + "=" + URLEncoder.encode(ccVar, "UTF-8") + "&" +
                                URLEncoder.encode("descriptionExtra", "UTF-8") + "=" + URLEncoder.encode(descExtra, "UTF-8") + "&" +
                                URLEncoder.encode("prixExtra", "UTF-8") + "=" + URLEncoder.encode(prixExtra, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }
                    bufferedReader.close();
                    inputStream.close();

                    String jsonString = response.toString();
                    Log.d("TAG", "Response: " + jsonString);

                    JSONObject jsonObject = new JSONObject(jsonString);
                    int status = jsonObject.getInt("status");
                    return status;
                } else {
                    return HttpURLConnection.HTTP_BAD_REQUEST;
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return HttpURLConnection.HTTP_BAD_REQUEST;
            }
        }

        @Override
        protected void onPostExecute(Integer status) {

            if (status == HttpURLConnection.HTTP_OK) {
                Toast.makeText(context, "Fiche frais crée", Toast.LENGTH_SHORT).show();


                if (user.equals("vincent@gmail.com")) {
                    Intent i = new Intent(VisiteurCreation.this, Visiteur.class);
                    startActivity(i);
                }


            } else if (status == HttpURLConnection.HTTP_BAD_REQUEST) {
                Toast.makeText(context, "Une erreur est survenue", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Un champ a mal été renseigné", Toast.LENGTH_LONG).show();
            }
        }



    }
}