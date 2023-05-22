package com.example.ungloen_mobile_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ungloen_mobile_exam.service.FirebaseFirestoreService;

public class BeregnMaanedsLoen extends AppCompatActivity {

    private final FirebaseFirestoreService firebaseFirestoreService = new FirebaseFirestoreService();
    private final String[] incomeOptions = {"Løn", "SU", "Dagpenge (A-kasse) eller kontanthjælp", "Efterløn", "Pension"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beregn_maaneds_loen);

        Spinner incomeSpinner = findViewById(R.id.income_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, incomeOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        incomeSpinner.setAdapter(adapter);
        listenForDropDown();
    }

    public void gaaTilbageTilTjenester(View view) {
        finish();
    }

    private void listenForDropDown() {
        Spinner incomeSpinner = findViewById(R.id.income_spinner);
        final RadioGroup payPeriodRadioGroup = findViewById(R.id.every_two_weeks_or_monthly);
        final TextView payPeriodLabel = findViewById(R.id.pay_period_label);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, incomeOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        incomeSpinner.setAdapter(adapter);
        incomeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = incomeOptions[position];
                if (selectedOption.equals("Løn")) {
                    payPeriodRadioGroup.setVisibility(View.VISIBLE);
                    payPeriodLabel.setVisibility(View.VISIBLE);
                } else {
                    payPeriodRadioGroup.setVisibility(View.GONE);
                    payPeriodLabel.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}