package com.example.ungloen_mobile_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VoresTjenester extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vores_tjenester);
    }

    public void gaaTilbage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void gaaTilBeregnMaanedsLoen(View view) {
        Intent intent = new Intent(this, BeregnMaanedsLoen.class);
        startActivity(intent);
    }

    public void gaaTilKoerselsFradrag(View view) {
        Intent intent = new Intent(this, BeregnKoerselsFradrag.class);
        startActivity(intent);
    }

    public void gaaTilFeriePenge(View view) {
        Intent intent = new Intent(this, BeregnFeriePenge.class);
        startActivity(intent);
    }
}