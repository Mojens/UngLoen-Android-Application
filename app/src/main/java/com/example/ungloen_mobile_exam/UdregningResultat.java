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

        String typeUdregning = getIntent().getStringExtra("TYPE_UDREGNING");
        TextView resultat = findViewById(R.id.resultat_fra_tjeneste);
        TextView aarligResultat = findViewById(R.id.aarlige_result_fra_tjeneste);

        if (typeUdregning.equals("MÅNEDSLØN")) {
            double udbetaling = getIntent().getDoubleExtra("UDBETALING", 0.0);
            double aarligUdbetaling = getIntent().getDoubleExtra("AARLIG_UDBETALING", 0.0);

            if (udbetaling != 0.0) {
                resultat.setText("Udbetaling: " + decimalFormat.format(udbetaling) + " kr.");
            }
            if (aarligUdbetaling != 0.0) {
                aarligResultat.setText("Årlig udbetaling: " + decimalFormat.format(aarligUdbetaling) + " kr.");
            }
        }
        if (typeUdregning.equals("KØRESELSFRADRAG")) {
            aarligResultat.setVisibility(View.GONE);
            double koerselsFradrag = getIntent().getDoubleExtra("KØRESELSFRADRAG", 0.0);
            resultat.setText("Kørselsfradrag: " + decimalFormat.format(koerselsFradrag) + " kr.");
        }
        if (typeUdregning.equals("FERIEPENGE")) {
            double ferieDageOptjent = getIntent().getDoubleExtra("FERIEDAGEOPTJENT", 0.0);
            double feriePengeOptjent = getIntent().getDoubleExtra("FERIEPENGEOPTJENT", 0.0);
            double feriePengeAar = getIntent().getDoubleExtra("FERIEPENGEAAR", 0.0);
            int maanederIArbejde = getIntent().getIntExtra("FERIEMÅNEDER", 0);
            resultat.setText("Feriedage optjent i "+maanederIArbejde+" måneder: " + decimalFormat.format(ferieDageOptjent) + " dage. \n\n"
                    + "Feriepenge optjent: \n" + decimalFormat.format(feriePengeOptjent) + " kr.");
            aarligResultat.setText("Feriedage optjent pr. år:\n 24,96 dage.\n\n" + "Feriepenge optjent pr. år: " + decimalFormat.format(feriePengeAar) + " kr.");
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