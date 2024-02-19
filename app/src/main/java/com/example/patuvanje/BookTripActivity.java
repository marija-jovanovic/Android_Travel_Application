package com.example.patuvanje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BookTripActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    patnikAdapter mAdapter;
    public static String korisnik;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_trip);

        Intent intent = getIntent();
        korisnik = intent.getExtras().getString("id");

        db = openOrCreateDatabase("mojproekt",MODE_PRIVATE,null);
        ArrayList<String> lista = new ArrayList<String>();
        int id;
        String kompanija, datum, vremep, vremek, izvor, dest, mesta, cena, koordi, koordd;

        String date = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        Calendar now = Calendar.getInstance();

        // Get the current hour and minute as parameters
       String cas = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);

        Cursor cursor = db.rawQuery("SELECT * FROM vozenja", null);
        cursor.moveToFirst();
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
            koordd  =  cursor.getString(10);

            String[] arrOfStr1 =  date.split("/");
            String[] arrOfStr2 = datum.split("/");
            String[] cas1 = cas.split(":");
            String[] cas2 = vremep.split(":");

            if((Integer.parseInt(arrOfStr1[0]) <= Integer.parseInt(arrOfStr2[0])) &&
                    (Integer.parseInt(arrOfStr1[1]) <= Integer.parseInt(arrOfStr2[1])) &&
                    (Integer.parseInt(arrOfStr1[2]) <= Integer.parseInt(arrOfStr2[2]))){
                //ako sega sme ist ili pomal mesec od patuvanjeto
                if((Integer.parseInt(arrOfStr1[0]) == Integer.parseInt(arrOfStr2[0])) &&
                        (Integer.parseInt(arrOfStr1[1]) == Integer.parseInt(arrOfStr2[1])) &&
                        (Integer.parseInt(arrOfStr1[2]) == Integer.parseInt(arrOfStr2[2])) &&
                        (Integer.parseInt(cas1[0]) == (Integer.parseInt(cas2[0]))) &&
                        (Integer.parseInt(cas1[1]) > (Integer.parseInt(cas2[1])))){
                    //nisto ne treba da prikaze
                }

                else if((Integer.parseInt(arrOfStr1[0]) == Integer.parseInt(arrOfStr2[0])) &&
                        (Integer.parseInt(arrOfStr1[1]) == Integer.parseInt(arrOfStr2[1])) &&
                        (Integer.parseInt(arrOfStr1[2]) == Integer.parseInt(arrOfStr2[2])) &&
                        (Integer.parseInt(cas1[0]) > (Integer.parseInt(cas2[0])))){
                    //nisto ne treba da prikaze
                }


                else
            lista.add("Id: " + String.valueOf(id) + " Company: " + kompanija + " Date: " + datum + " Depart: " +
                    vremep + " Arrival: " + vremek + " Depart_City: " + izvor + " Arrival_City: " + dest + " Seats: " +
                    mesta + " Price: " + cena + " Depart_coordinates: " + koordi + " Arrival_coordinates: " + koordd); }
        } while (cursor.moveToNext());
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
        mAdapter = new patnikAdapter(lista, R.layout.my_row, this);
//прикачување на адаптерот на RecyclerView
        mRecyclerView.setAdapter(mAdapter);


    }
}