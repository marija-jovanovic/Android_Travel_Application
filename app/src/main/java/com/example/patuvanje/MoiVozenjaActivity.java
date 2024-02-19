package com.example.patuvanje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class MoiVozenjaActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    myAdapter mAdapter;
    private SQLiteDatabase db;
    String korisnik;

    //public static String ajdi = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moi_vozenja);

        Intent intent = getIntent();
        korisnik = intent.getExtras().getString("id");

        db = openOrCreateDatabase("mojproekt",MODE_PRIVATE,null);
        ArrayList<String> lista = new ArrayList<String>();
        int id;
        String kompanija, datum, vremep, vremek, izvor, dest, mesta, cena, koordi, koordd;

        Cursor cursor = db.rawQuery("SELECT * FROM vozenja WHERE KOMPANIJA = '" + korisnik + "'", null);
       if(cursor.moveToFirst()) {
            do {
                id = cursor.getInt(0);
                kompanija = cursor.getString(1);
                datum = cursor.getString(2);
                vremep = cursor.getString(3);
                vremek = cursor.getString(4);
                izvor = cursor.getString(5);
                dest = cursor.getString(6);
                mesta = cursor.getString(7);
                cena = cursor.getString(8);
                koordi = cursor.getString(9);
                koordd = cursor.getString(10);
                lista.add("Id: " + String.valueOf(id) + " Company: " + kompanija + " Date: " + datum + " Depart: " +
                        vremep + " Arrival: " + vremek + " Depart_City: " + izvor + " Arrival_City: " + dest + " Seats: " +
                        mesta + " Price: " + cena + " Depart_coordinates: " + koordi + " Arrival_coordinates: " + koordd);
            } while (cursor.moveToNext());
        }
        cursor.close();

        //сетирање на RecyclerView контејнерот
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
// оваа карактеристика може да се користи ако се знае дека промените
// во содржината нема да ја сменат layout големината на RecyclerView
        mRecyclerView.setHasFixedSize(true);
// ќе користиме LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
// и default animator (без анимации)
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
// сетирање на кориснички дефиниран адаптер myAdapter (посебна класа)
        mAdapter = new myAdapter(lista, R.layout.my_row, this);
//прикачување на адаптерот на RecyclerView
        mRecyclerView.setAdapter(mAdapter);
    }
}