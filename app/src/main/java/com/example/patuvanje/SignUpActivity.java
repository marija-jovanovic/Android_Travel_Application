package com.example.patuvanje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText signupEmail, signupPassword, getRole;
    private Button signupButton;
    private TextView loginRedirectText;
    private RadioGroup radioGroup;

    private String kopce="";
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = openOrCreateDatabase("mojproekt",MODE_PRIVATE,null);
       // db.execSQL("DROP TABLE IF EXISTS identitet");
        String CREATE_TABLE_IDENTITET = "CREATE TABLE IF NOT EXISTS " + "identitet" + " ("
                + "ID" + " VARCHAR PRIMARY KEY, " + "ROLE" +
                " VARCHAR, " + "PASSWORD" + " VARCHAR" + ")";
        db.execSQL(CREATE_TABLE_IDENTITET);

        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();

                if (user.isEmpty()){
                    signupEmail.setError("Email cannot be empty");
                }
                if(pass.isEmpty()){
                    signupPassword.setError("Password cannot be empty");
                }
                if(kopce.isEmpty()){
                    Toast.makeText(SignUpActivity.this, "You must choose your role.", Toast.LENGTH_SHORT).show();
                }else{

                    Cursor c = db.rawQuery("SELECT * FROM identitet WHERE ID = '" + user + "'", null);
                    if (c.moveToFirst()) {
                        Toast.makeText(SignUpActivity.this, "The email is already in use!", Toast.LENGTH_SHORT).show();
                        c.close();
                    }

                    else {
                        c.close();
                        Toast.makeText(SignUpActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                        ContentValues insertValues = new ContentValues();
                        insertValues.put("ID", user);
                        insertValues.put("ROLE", kopce);
                        insertValues.put("PASSWORD", pass);
                        db.insert("identitet", null, insertValues);
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    }
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        db.close();
    }

    public void radioClick(View view) {
        if(view.getId() == R.id.kompanija)
            kopce = "kompanija";
        else
            kopce = "patnik";
    }
}