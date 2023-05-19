package com.example.myloginapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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



public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button loginbtn;
    String userVar, passVar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginbtn = (Button) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userVar = username.getText().toString();
                passVar = password.getText().toString();


                if (userVar.equals("")) {
                    //identifiant incorrect
                    Toast.makeText(getApplicationContext(), "Saisir un identifant", Toast.LENGTH_SHORT).show();
                } else if (passVar.equals("")) {
                    //mot de passe incorrect
                    Toast.makeText(getApplicationContext(), "Saisir un mot de passe", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Vérification de la connexion", Toast.LENGTH_SHORT).show();

                    Login lg = new Login(MainActivity.this);
                    lg.execute();



                   /* Intent i = new Intent (MainActivity.this,MainActivity2.class);
                    startActivity(i);*/
                }
            }


        });
    }

    class Login extends AsyncTask<String, Void, Integer> {

        AlertDialog alertDialog;
        Context context;
        ProgressDialog progressDialog;

        Login(MainActivity ctx) {
            this.context = ctx;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(context, "", "En cours de connexion");
        }

        @Override
        protected Integer doInBackground(String... params) {
            String login_url = "https://projet-gsb.francois-talaban.com/login.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(userVar, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(passVar, "UTF-8");
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

                    JSONObject jsonObject = new JSONObject(response.toString());
                    return jsonObject.getInt("status");
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
            progressDialog.dismiss();

            if (status == HttpURLConnection.HTTP_OK) {
                username.setText("");
                password.setText("");
                Toast.makeText(context, "Connexion réussie", Toast.LENGTH_SHORT).show();


                if(userVar.equals("vincent@gmail.com"))
                {
                    UserData.getInstance().setUserVar(userVar);

                    if (userVar != null) {
                        Log.d("TAG", userVar);
                    } else {
                        Log.d("TAG", "userVar is null");
                    }


                    Intent i = new Intent(MainActivity.this, Visiteur.class);
                    startActivity(i);
                }



            } else if (status == HttpURLConnection.HTTP_BAD_REQUEST) {
                Toast.makeText(context, "Identifiant ou mot de passe incorrect", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Problème rencontré lors de la connexion au serveur. Veuillez réessayer.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
