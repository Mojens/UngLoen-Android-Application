package com.example.ungloen_mobile_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ungloen_mobile_exam.model.KoerselsFradragData;
import com.example.ungloen_mobile_exam.service.DataUdregner;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.net.InternetDomainName;

import java.text.DecimalFormat;

public class BeregnKoerselsFradrag extends AppCompatActivity implements OnMapReadyCallback {

    private final DataUdregner dataUdregner = new DataUdregner();
    private GoogleMap googleMap;
    private LatLng punkt1;
    private LatLng punkt2;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beregn_koersels_fradrag);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        Button beregnAfstandButton = findViewById(R.id.udregn_distance);
        beregnAfstandButton.setOnClickListener(v -> beregnAfstand());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnMapClickListener(latLng -> {
            if (punkt1 == null) {
                punkt1 = latLng;
                googleMap.addMarker(new MarkerOptions().position(punkt1));
            } else if (punkt2 == null) {
                punkt2 = latLng;
                googleMap.addMarker(new MarkerOptions().position(punkt2));
            } else {
                Toast.makeText(this, "Du kan kun vælge 2 punkter", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fjernPunkter(View view) {
        punkt1 = null;
        punkt2 = null;
        googleMap.clear();
    }

    public void beregnAfstand() {
        if (punkt1 != null && punkt2 != null) {
            double afstand = beregnAfstandMellemPunkter(punkt1, punkt2);
            EditText kilometerTilArbejdeInput = findViewById(R.id.kilometer_til_arbejde);
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            kilometerTilArbejdeInput.setText(decimalFormat.format(afstand/1000));
            Toast.makeText(this, "Afstanden: " + decimalFormat.format(afstand/1000) + " km", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Venligst vælge 2 punkter på kortet", Toast.LENGTH_SHORT).show();
        }
    }

    /* ChatGPT */
    private double beregnAfstandMellemPunkter(LatLng punkt1, LatLng punkt2) {
        float[] results = new float[1];
        android.location.Location.distanceBetween(punkt1.latitude, punkt1.longitude, punkt2.latitude, punkt2.longitude, results);
        return results[0];
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    public void gaaTilbageTilTjenester2(View view) {
        finish();
    }

    public void beregnKoerselsFradrag(View view) {

        EditText kilometerTilArbejdeInput = findViewById(R.id.kilometer_til_arbejde);
        double kilometerTilArbejde = Double.parseDouble(kilometerTilArbejdeInput.getText().toString());

        EditText arbejdsdageInput = findViewById(R.id.arbejdsdage_i_perioden);
        int arbejdsdage = Integer.parseInt(arbejdsdageInput.getText().toString());

        KoerselsFradragData koerselsFradragData = new KoerselsFradragData(kilometerTilArbejde, arbejdsdage);
        double koerselsFradrag = dataUdregner.udregnKoerselsFradrag(koerselsFradragData);


        Intent intent = new Intent(this, UdregningResultat.class);
        intent.putExtra("TYPE_UDREGNING", "KØRESELSFRADRAG");
        intent.putExtra("KØRESELSFRADRAG", koerselsFradrag);

        startActivity(intent);
    }
}