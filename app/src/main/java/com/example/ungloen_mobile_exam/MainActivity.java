package com.example.ungloen_mobile_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView opretBruger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        opretBruger = findViewById(R.id.opret_bruger_link);
    }

    public void gaaTilVoresTjenester(View view){
        Intent intent = new Intent(this, VoresTjenester.class);
        startActivity(intent);
    }

    public void gaaTilOpretBruger(View view){
        String link = "https://ungloen.dk/opret-bruger";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }
}