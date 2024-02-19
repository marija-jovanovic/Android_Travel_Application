package com.example.patuvanje;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class KonkretnoPatuvanjeFragment extends Fragment {

    private static final int REQUEST_CODE_DETAILS_ACTIVITY = 1234;
    private SQLiteDatabase db;

    prethodniAdapter pAdapter;

    RecyclerView mRecyclerView;

    String ajdi;

    public KonkretnoPatuvanjeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_konkretno_patuvanje, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        VidiReviewActivity xxx = (VidiReviewActivity) getActivity();
        ajdi = xxx.ajdi;

        db = getActivity().openOrCreateDatabase("mojproekt", MODE_PRIVATE, null);

        mRecyclerView = (RecyclerView)
                getActivity().findViewById(R.id.list);
// оваа карактеристика може да се користи ако се знае дека промените
// во содржината нема да ја сменат layout големината на RecyclerView
        mRecyclerView.setHasFixedSize(true);
// ќе користиме LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
// и default animator (без анимации)
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        List<String> predmeti = new ArrayList<String>();
        Cursor c2 = db.rawQuery("SELECT * FROM vozenja WHERE ID = '" + Integer.parseInt(ajdi) + "'", null);
        if(c2.moveToFirst()) {
            do {
               int id = c2.getInt(0);
              String  kompanija = c2.getString(1);
               String datum = c2.getString(2);
                String vremep = c2.getString(3);
                String vremek = c2.getString(4);
               String izvor = c2.getString(5);
                String dest = c2.getString(6);
                String mesta = c2.getString(7);
                String cena = c2.getString(8);
               String koordi = c2.getString(9);
                String koordd = c2.getString(10);
                predmeti.add("Id: " + String.valueOf(id) + " Company: " + kompanija + " Date: " + datum + " Depart: " +
                        vremep + " Arrival: " + vremek + " Depart_City: " + izvor + " Arrival_City: " + dest + " Seats: " +
                        mesta + " Price: " + cena + " Depart_coordinates: " + koordi + " Arrival_coordinates: " + koordd);
            } while (c2.moveToNext());
        }
        c2.close();


        pAdapter = new prethodniAdapter(predmeti, R.layout.my_rowp, getActivity());
//прикачување на адаптерот на RecyclerView
        mRecyclerView.setAdapter(pAdapter);

        Toast.makeText(getContext(), ajdi, Toast.LENGTH_SHORT).show();

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            // show in same activity
            PrethodniAnketiFragment frag = (PrethodniAnketiFragment) getFragmentManager().findFragmentById(R.id.anketi);
            frag.setCasid(ajdi);

        }

        }


}

