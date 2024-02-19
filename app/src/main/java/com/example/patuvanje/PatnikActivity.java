package com.example.patuvanje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PatnikActivity extends AppCompatActivity {

    String korisnik;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patnik);

        TextView tv = findViewById(R.id.pozdrav);
        Intent intent = getIntent();
        korisnik = intent.getExtras().getString("id");
        String p = "Hello, " + korisnik + "!";
        tv.setText(p);

    }


    public void klik1(View view) {
        Intent i = new Intent(PatnikActivity.this, BookTripActivity.class);
        i.putExtra("id", korisnik);
        startActivity(i);
    }

    public void klik2(View view) {
        Intent i = new Intent(PatnikActivity.this, ViewTripActivity.class);
        i.putExtra("id", korisnik);
        startActivity(i);
    }
}