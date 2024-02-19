package com.example.patuvanje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private TextView signupRedirectText;
    private Button loginButton;

    private String uloga;

    private SQLiteDatabase db;

    private String korisnik;

    private String kopce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = openOrCreateDatabase("mojproekt", MODE_PRIVATE, null);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signUpRedirectText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();
                String p="";

                if (!email.isEmpty() && !pass.isEmpty()) {

                    Cursor c = db.rawQuery("SELECT * FROM identitet WHERE ID = '" + email + "'", null);
                    if (c != null) {
                    if (c.moveToFirst()) {
                        korisnik = (c.getString(0));
                        kopce = c.getString(1);
                        p = c.getString(2);
                        c.close();
                    }
                    else
                        c.close();

                    if (p.equals(pass)) {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        if (kopce.equals("kompanija")) {
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.putExtra("id", korisnik);
                            startActivity(i);
                        }

                        if (kopce.equals("patnik")) {
                             Intent i = new Intent(LoginActivity.this, PatnikActivity.class);
                             i.putExtra("id", korisnik);
                             startActivity(i);
                        }
                    } else
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }
                    else
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }

                else{
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }


            }
        });
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}