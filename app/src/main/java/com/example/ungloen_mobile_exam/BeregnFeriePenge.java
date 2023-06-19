package com.example.ungloen_mobile_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ungloen_mobile_exam.model.KoerselsFradragData;
import com.example.ungloen_mobile_exam.model.feriepenge.FeriePengeResultat;
import com.example.ungloen_mobile_exam.model.feriepenge.FeriepengeData;
import com.example.ungloen_mobile_exam.service.DataUdregner;

public class BeregnFeriePenge extends AppCompatActivity {

    private final DataUdregner dataUdregner = new DataUdregner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beregn_ferie_penge);
    }

    public void gaaTilbageTilTjenester3(View view) {
        finish();
    }

    public void beregnFeriePenge(View view) {

        EditText maanederIArbejdeInput = findViewById(R.id.maaneder_i_arbejde);
        int maanederIArbejde = Integer.parseInt(maanederIArbejdeInput.getText().toString());

        EditText indkomstInput = findViewById(R.id.indkomst_feriepenge);
        double indkomst = Double.parseDouble(indkomstInput.getText().toString());

        FeriepengeData feriepengeData = new FeriepengeData(indkomst, maanederIArbejde);

        FeriePengeResultat resultat = dataUdregner.udregnFeriepenge(feriepengeData);

        Intent intent = new Intent(this, UdregningResultat.class);
        intent.putExtra("TYPE_UDREGNING", "FERIEPENGE");
        intent.putExtra("FERIEDAGEOPTJENT", resultat.getFerieDageOptjent());
        intent.putExtra("FERIEPENGEOPTJENT", resultat.getFeriePengeOptjent());
        intent.putExtra("FERIEPENGEAAR", resultat.getFeriepengeAar());

        startActivity(intent);
    }

}