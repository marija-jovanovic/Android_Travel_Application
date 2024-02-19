package com.example.patuvanje;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.patuvanje.databinding.ActivityMapaBinding;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapaBinding binding;

    String koordi1;
    String koordi2;
    String koordd1;
    String koordd2;

    String izvorr;
    String destt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        koordi1 = intent.getExtras().getString("koordi1");
        koordi2 = intent.getExtras().getString("koordi2");
        koordd1 = intent.getExtras().getString("koordd1");
        koordd2 = intent.getExtras().getString("koordd2");
        izvorr = intent.getExtras().getString("izvor");
        destt = intent.getExtras().getString("dest");

        binding = ActivityMapaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng izvor = new LatLng(Double.parseDouble(koordi1), Double.parseDouble(koordi2));
        LatLng dest = new LatLng(Double.parseDouble(koordd1), Double.parseDouble(koordd2));
        mMap.addMarker(new MarkerOptions().position(izvor).title(izvorr));
        mMap.addMarker(new MarkerOptions().position(dest).title(destt));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(izvor));
        mMap.addPolyline(new PolylineOptions()
                .add(izvor)
                .add(dest)
        );
       // mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
    }
}