package com.example.ungloen_mobile_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.common.net.InternetDomainName;

public class BeregnKoerselsFradrag extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beregn_koersels_fradrag);
    }

    public void gaaTilbageTilTjenester2(View view) {
        finish();
    }
}