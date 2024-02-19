package com.example.patuvanje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class VozenjeActivity extends AppCompatActivity {

    DatePicker picker;

    String datum="";
    String korisnik;

    private SQLiteDatabase db;

    ContentValues insertValues = new ContentValues();
    private TimePicker timePicker1;
    private TimePicker timePicker2;

    EditText izvor;
    EditText dest;
    EditText mesta_max;
    EditText cena;
    EditText koord_izvor;
    EditText koord_dest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vozenje);

        Intent intent = getIntent();
        korisnik = intent.getExtras().getString("id");

        db = openOrCreateDatabase("mojproekt",MODE_PRIVATE,null);
        // db.execSQL("DROP TABLE IF EXISTS vozenja");

        String CREATE_TABLE_VOZENJA = "CREATE TABLE IF NOT EXISTS vozenja" + " ("
                + "ID INTEGER PRIMARY KEY, " + "KOMPANIJA VARCHAR, " + "DATUM VARCHAR, " + "START_TIME VARCHAR, " + "END_TIME VARCHAR, " + "IZVOR VARCHAR, "
                + "DESTINACIJA VARCHAR, " + "MESTA VARCHAR, " + "CENA VARCHAR, " + "KOORDINATI_IZVOR VARCHAR, " + "KOORDINATI_DESTINACIJA VARCHAR" + ")";
        db.execSQL(CREATE_TABLE_VOZENJA);

        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        timePicker1.setIs24HourView(true);
        timePicker2 = (TimePicker) findViewById(R.id.timePicker2);
        timePicker2.setIs24HourView(true);

        insertValues.put("KOMPANIJA", korisnik);

    }

    public void datumm(View view) {
        picker=(DatePicker)findViewById(R.id.datePicker);
        StringBuilder builder=new StringBuilder();;
        builder.append((picker.getMonth() + 1)+"/");//month is 0 based
        builder.append(picker.getDayOfMonth()+"/");
        builder.append(picker.getYear());
        datum = builder.toString();
        insertValues.put("DATUM", datum);
        Toast.makeText(VozenjeActivity.this, "Successfully inserted date.", Toast.LENGTH_SHORT).show();
    }

    public void pocetokk(View view) {

        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        int start_hour = timePicker1.getHour();
        int start_min = timePicker1.getMinute();
        String start_time = String.valueOf(start_hour) + ":" + String.valueOf(start_min);
        insertValues.put("START_TIME", start_time);
        Toast.makeText(VozenjeActivity.this, "Successfully inserted departing time.", Toast.LENGTH_SHORT).show();
    }

    public void krajj(View view) {
        timePicker2 = (TimePicker) findViewById(R.id.timePicker2);
        int end_hour = timePicker2.getHour();
        int end_min = timePicker2.getMinute();
        String end_time = String.valueOf(end_hour) + ":" + String.valueOf(end_min);
        insertValues.put("END_TIME", end_time);
        Toast.makeText(VozenjeActivity.this, "Successfully inserted arriving time.", Toast.LENGTH_SHORT).show();


    }

    public void back(View view) {


        if(insertValues.size() == 10) {

            String naslov = "";
            db.insert("vozenja", null, insertValues);
            Toast.makeText(VozenjeActivity.this, "The ride was successfully made.", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(VozenjeActivity.this, "Missing information, make sure to fill in every field!", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    public void b1(View view) { //izvor
        izvor = findViewById(R.id.izvor);
        insertValues.put("IZVOR",izvor.getText().toString());
        Toast.makeText(VozenjeActivity.this, "Departing city confirmed.", Toast.LENGTH_SHORT).show();
    }

    public void b2(View view) { //dest
        dest = findViewById(R.id.destinacija);
        insertValues.put("DESTINACIJA",dest.getText().toString());
        Toast.makeText(VozenjeActivity.this, "Arriving city confirmed.", Toast.LENGTH_SHORT).show();
    }

    public void b3(View view) { //mesta
        mesta_max = findViewById(R.id.mesta);
        insertValues.put("MESTA",mesta_max.getText().toString());
        Toast.makeText(VozenjeActivity.this, "Seats confirmed.", Toast.LENGTH_SHORT).show();
    }

    public void b4(View view) { //cena
        cena = findViewById(R.id.cena);
        insertValues.put("CENA",cena.getText().toString());
        Toast.makeText(VozenjeActivity.this, "Price confirmed.", Toast.LENGTH_SHORT).show();
    }

    public void b5(View view) { //koordinati izvor
        koord_izvor = findViewById(R.id.koordinati_izvor);
        insertValues.put("KOORDINATI_IZVOR",koord_izvor.getText().toString());
        Toast.makeText(VozenjeActivity.this, "Departing city coordinates confirmed.", Toast.LENGTH_SHORT).show();
    }

    public void b6(View view) { //koordinati dest
        koord_dest = findViewById(R.id.koordinati_destinacija);
        insertValues.put("KOORDINATI_DESTINACIJA",koord_dest.getText().toString());
        Toast.makeText(VozenjeActivity.this, "Arriving city coordinates confirmed.", Toast.LENGTH_SHORT).show();
    }
}