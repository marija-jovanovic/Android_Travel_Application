package com.example.patuvanje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PatnikOpcii extends AppCompatActivity {

    String ajdi;

    String koordi1;
    String koordi2;
    String koordd1;
    String koordd2;

    String izvor;

    String dest;

    String korisnik;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patnik_opcii);

        Intent intent = getIntent();
        ajdi = intent.getExtras().getString("id");
        korisnik = intent.getExtras().getString("korisnik");
        db = openOrCreateDatabase("mojproekt", MODE_PRIVATE, null);

       // Toast.makeText(this, ajdi, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, korisnik, Toast.LENGTH_SHORT).show();

        Cursor cursor = db.rawQuery("SELECT * FROM vozenja WHERE ID = '" + Integer.parseInt(ajdi) + "'", null);
        if(cursor.moveToFirst()) {
            do {
               String[] koordi = (cursor.getString(9)).split(" ");
               koordi1 = koordi[0];
               koordi2 = koordi[1];
               String[] koordd = (cursor.getString(10)).split(" ");
               koordd1 = koordd[0];
               koordd2 = koordd[1];
               izvor = cursor.getString(5);
               dest = cursor.getString(6);
            } while (cursor.moveToNext());
        }
        cursor.close();


    }

    public void klik1(View view) {
        Intent i = new Intent(PatnikOpcii.this, PatnikReviewActivity.class);
        i.putExtra("korisnik", korisnik);
        i.putExtra("id", ajdi);
        startActivity(i);
    }

    public void klik2(View view) {
        Intent i = new Intent(PatnikOpcii.this, MapaActivity.class);
       // i.putExtra("korisnik", korisnik);
       // i.putExtra("id", ajdi);
        i.putExtra("koordi1", koordi1);
        i.putExtra("koordi2", koordi2);
        i.putExtra("koordd1", koordd1);
        i.putExtra("koordd2", koordd2);
        i.putExtra("izvor", izvor);
        i.putExtra("dest", dest);
        startActivity(i);
    }
}