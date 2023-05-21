package com.example.ungloen_mobile_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class VoresTjenester extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vores_tjenester);
    }

    public void gaaTilbage(View view){
        finish();
    }
}