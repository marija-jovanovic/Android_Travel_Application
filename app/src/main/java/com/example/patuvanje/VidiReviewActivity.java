package com.example.patuvanje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class VidiReviewActivity extends AppCompatActivity {

    public static String ajdi = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidi_review);

        Intent intent = getIntent();
        ajdi = intent.getExtras().getString("ajdi");


    }

}

