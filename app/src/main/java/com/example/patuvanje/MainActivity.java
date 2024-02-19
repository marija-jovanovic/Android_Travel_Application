package com.example.patuvanje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String korisnik;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.pozdrav);
        Intent intent = getIntent();
        korisnik = intent.getExtras().getString("id");
        String p = "Hello, " + korisnik + "!";
        tv.setText(p);



    }


    public void klik1(View view) {
        Intent i = new Intent(MainActivity.this, VozenjeActivity.class);
        i.putExtra("id", korisnik);
        startActivity(i);
    }

    public void klik2(View view) {
        Intent i = new Intent(MainActivity.this, MoiVozenjaActivity.class);
        i.putExtra("id", korisnik);
        startActivity(i);
    }
}