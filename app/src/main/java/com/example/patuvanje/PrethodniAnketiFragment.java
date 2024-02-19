package com.example.patuvanje;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class PrethodniAnketiFragment extends Fragment {

    private SQLiteDatabase db;

    anketenAdapter aAdapter;

    RecyclerView mRecyclerView;

    String korisnik;

    public PrethodniAnketiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_prethodni_anketi, container, false);
        View view = inflater.inflate(R.layout.fragment_prethodni_anketi, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       // db = getActivity().openOrCreateDatabase("mojproekt", MODE_PRIVATE, null);

       //tuka bese za recyclerview

    }

    /*
     * Го сетира активно избраниот град според дадениот resource ID
     */

    public void setCasid(String s) {

        mRecyclerView = (RecyclerView)
                getActivity().findViewById(R.id.list1);
// оваа карактеристика може да се користи ако се знае дека промените
// во содржината нема да ја сменат layout големината на RecyclerView
        mRecyclerView.setHasFixedSize(true);
// ќе користиме LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
// и default animator (без анимации)
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // извлечете го ID-то на градот што треба да се покаже од intent-от на активност

       // Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
        db = getActivity().openOrCreateDatabase("mojproekt", MODE_PRIVATE, null);
        List<String> anketi = new ArrayList<String>();
        Cursor c2 = db.rawQuery("SELECT * FROM review WHERE VOZENJA_ID = '" + s + "'", null);
        if(c2.moveToFirst()) {
            do {
                anketi.add(c2.getString(2) + " " + c2.getString(3) + " " + c2.getString(4));
            } while (c2.moveToNext());
        }
        c2.close();

        // сетирање на кориснички дефиниран адаптер myAdapter (посебна класа)
        aAdapter = new anketenAdapter(anketi, R.layout.my_rowa, getActivity());
//прикачување на адаптерот на RecyclerView
        mRecyclerView.setAdapter(aAdapter);

       // Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
