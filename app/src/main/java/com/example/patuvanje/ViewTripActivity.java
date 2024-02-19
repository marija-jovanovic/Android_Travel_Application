package com.example.patuvanje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewTripActivity extends AppCompatActivity {

   public static String korisnik = null;
    private SQLiteDatabase db;
    RecyclerView mRecyclerView;
    vidiAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);

        Intent intent = getIntent();
        korisnik = intent.getExtras().getString("id");
        Toast.makeText(this, korisnik, Toast.LENGTH_SHORT).show();
        db = openOrCreateDatabase("mojproekt", MODE_PRIVATE, null);

        ArrayList<String> al1 = new ArrayList<String>(); //id na bookiranite vozenja za najaveniot korisnik
        ArrayList<String> lista = new ArrayList<String>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + "prijaveni" + " WHERE " + "KORISNIK" + "='" + korisnik + "'", null);
        if (cursor.moveToFirst()){
            do {
                al1.add(cursor.getString(1));
            } while (cursor.moveToNext());
    }
        cursor.close();

        for(int i = 0; i < al1.size(); i++) {
            Cursor c = db.rawQuery("SELECT * FROM " + "vozenja" + " WHERE " + "ID" + "='" + Integer.parseInt(al1.get(i)) + "'", null);
            c.moveToFirst();
            do {
                //vo recyclerview
                lista.add("Id: " + String.valueOf(c.getInt(0)) + " Company: " + c.getString(1) + " Date: " + c.getString(2) + " Depart: " +
                        c.getString(3) + " Arrival: " + c.getString(4) + " Depart_City: " + c.getString(5) + " Arrival_City: " + c.getString(6) + " Seats: " +
                        c.getString(7) + " Price: " + c.getString(8) + " Depart_coordinates: " + c.getString(9) + " Arrival_coordinates: " + c.getString(10));
            } while (c.moveToNext());
            c.close();
        }

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
        mAdapter = new vidiAdapter(lista, R.layout.my_row, this);
//прикачување на адаптерот на RecyclerView
        mRecyclerView.setAdapter(mAdapter);

    }
}