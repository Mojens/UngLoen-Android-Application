package com.example.ungloen_mobile_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class UdregningResultat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udregning_resultat);

        double udbetaling = getIntent().getDoubleExtra("UDBETALING", 0.0);
        double aarligUdbetaling = getIntent().getDoubleExtra("AARLIG_UDBETALING", 0.0);
        Log.i("UdregningResultat", "Udbetaling: " + udbetaling);
        Log.i("UdregningResultat", "Årlig udbetaling: " + aarligUdbetaling);



        TextView resultat = findViewById(R.id.resultat_fra_tjeneste);
        TextView aarligResultat = findViewById(R.id.aarlige_result_fra_tjeneste);

    }

    public void gaaTilbageTilTjeneste() {
        finish();
    }

    public void gaaTilStart(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}