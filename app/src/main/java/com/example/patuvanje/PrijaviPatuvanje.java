package com.example.patuvanje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class PrijaviPatuvanje extends AppCompatActivity {

    private String korisnik = BookTripActivity.korisnik;
    private String id;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijavi_patuvanje);

        Intent intent = getIntent();
        String ajdi = intent.getExtras().getString("id");
        String mesta = intent.getExtras().getString("mesta");
        db = openOrCreateDatabase("mojproekt", MODE_PRIVATE, null);
        String CREATE_TABLE_PRIJAVENI = "CREATE TABLE IF NOT EXISTS prijaveni" + " ("
                + "ID INTEGER PRIMARY KEY, " + "VOZENJA_ID VARCHAR, " + "KORISNIK VARCHAR" + ")";
        db.execSQL(CREATE_TABLE_PRIJAVENI);

       // Toast.makeText(this, korisnik, Toast.LENGTH_SHORT).show();
        // Toast.makeText(this, mesta, Toast.LENGTH_SHORT).show();


        Cursor kursor = db.rawQuery("SELECT * FROM " + "prijaveni" + " WHERE " + "VOZENJA_ID" + "='" + ajdi + "'"
                + " AND " + "KORISNIK" + "='" + korisnik + "'", null);
       // Cursor kursor = db.rawQuery("SELECT * FROM prijaveni WHERE ID = '" + ajdi + "'" + " AND KORISNIK = '" + korisnik + "'", null);
        if (kursor.moveToFirst()) {
            Toast.makeText(this, "You have already booked this trip!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
        if(Integer.parseInt(mesta) > 0) {
            Cursor cursor = db.rawQuery("SELECT * FROM vozenja WHERE ID = '" + ajdi + "'", null);
            cursor.moveToFirst();
            do {
                ContentValues data = new ContentValues();

                data.put("MESTA", String.valueOf(Integer.parseInt(mesta) - 1));
                db.update("vozenja", data, "ID=" + ajdi, null);
                ContentValues insertValues = new ContentValues();
                insertValues.put("VOZENJA_ID", ajdi);
                insertValues.put("KORISNIK", korisnik);
                db.insert("prijaveni", null, insertValues);
            } while (cursor.moveToNext());
            cursor.close();

            Toast.makeText(this, "Successfully booked!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sorry, no more spots left!", Toast.LENGTH_SHORT).show();
        }

    }

        kursor.close();





    }
}