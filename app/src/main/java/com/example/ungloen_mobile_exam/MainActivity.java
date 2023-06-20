package com.example.ungloen_mobile_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ungloen_mobile_exam.service.FirebaseFirestoreService;

public class MainActivity extends AppCompatActivity {

    private final FirebaseFirestoreService firebaseFirestoreService = new FirebaseFirestoreService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView box1 = findViewById(R.id.beregn_maaneds_loen_btn);
        TextView box2 = findViewById(R.id.box2);
        TextView box3 = findViewById(R.id.box3);
        TextView box4 = findViewById(R.id.box4);
        firebaseFirestoreService.gennemsnitMaanedsLoen(box1);
        firebaseFirestoreService.gennemsnitAarligLoen(box2);
        firebaseFirestoreService.gennemsnitKoerselsFradrag(box3);
        firebaseFirestoreService.gennemsnitFeriepenge(box4);
    }

    public void gaaTilVoresTjenester(View view) {
        Intent intent = new Intent(this, VoresTjenester.class);
        startActivity(intent);
    }

    public void gaaTilOpretBruger(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ungloen.vercel.app/opret-bruger"));
        startActivity(intent);
    }
}