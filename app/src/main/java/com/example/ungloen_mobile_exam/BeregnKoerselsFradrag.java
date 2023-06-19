package com.example.ungloen_mobile_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ungloen_mobile_exam.model.KoerselsFradragData;
import com.example.ungloen_mobile_exam.service.DataUdregner;
import com.google.common.net.InternetDomainName;

public class BeregnKoerselsFradrag extends AppCompatActivity {

    private final DataUdregner dataUdregner = new DataUdregner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beregn_koersels_fradrag);
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