package com.example.ungloen_mobile_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class UdregningResultat extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udregning_resultat);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("da-DK"));
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);

        double udbetaling = getIntent().getDoubleExtra("UDBETALING", 0.0);
        double aarligUdbetaling = getIntent().getDoubleExtra("AARLIG_UDBETALING", 0.0);
        Log.i("UdregningResultat", "Udbetaling: " + udbetaling);
        Log.i("UdregningResultat", "Årlig udbetaling: " + aarligUdbetaling);

        TextView resultat = findViewById(R.id.resultat_fra_tjeneste);
        TextView aarligResultat = findViewById(R.id.aarlige_result_fra_tjeneste);
        if (udbetaling != 0.0) {
            resultat.setText("Udbetaling: " + decimalFormat.format(udbetaling) + " kr.");
        }
        if (aarligUdbetaling != 0.0) {
            aarligResultat.setText("Årlig udbetaling: " + decimalFormat.format(aarligUdbetaling) + " kr.");
        }

    }

    public void gaaTilbageTilTjeneste() {
        finish();
    }

    public void gaaTilStart(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}