package com.example.ungloen_mobile_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ungloen_mobile_exam.R;
import com.example.ungloen_mobile_exam.model.MaanedsLoenData;
import com.example.ungloen_mobile_exam.service.DataUdregner;
import com.example.ungloen_mobile_exam.service.FirebaseFirestoreService;

public class BeregnMaanedsLoen extends AppCompatActivity {

    private final DataUdregner dataUdregner = new DataUdregner();
    private final String[] indkomstMuligheder = {"Løn", "SU", "Dagpenge (A-kasse) eller kontanthjælp", "Efterløn", "Pension"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beregn_maaneds_loen);

        Spinner incomeSpinner = findViewById(R.id.income_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, indkomstMuligheder);
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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, indkomstMuligheder);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        incomeSpinner.setAdapter(adapter);
        incomeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = indkomstMuligheder[position];
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

    public void beregnMaanedsLoen(View view) {
        EditText traekProcentInput = findViewById(R.id.traek_procent_input);
        double traekProcent = Double.parseDouble(traekProcentInput.getText().toString());

        EditText maanedligtFradragInput = findViewById(R.id.maanedlig_fraadrag_input);
        double maanedligtFradrag = Double.parseDouble(maanedligtFradragInput.getText().toString());

        RadioGroup biEllerHovedRadioGroup = findViewById(R.id.head_or_bi_card);
        int valgtRadio = biEllerHovedRadioGroup.getCheckedRadioButtonId();
        RadioButton valgtRadioButton = findViewById(valgtRadio);
        String biEllerHoved = valgtRadioButton.getText().toString();

        EditText maanedligeIndkomstInput = findViewById(R.id.monthly_income);
        double maanedligeIndkomst = Double.parseDouble(maanedligeIndkomstInput.getText().toString());

        Spinner incomeSpinner = findViewById(R.id.income_spinner);
        String indkomstType = incomeSpinner.getSelectedItem().toString();

        String udbetalingsPeriode = "";
        if (indkomstType.equals("Løn")) {
            RadioGroup payPeriodRadioGroup = findViewById(R.id.every_two_weeks_or_monthly);
            int valgtPayPeriodRadio = payPeriodRadioGroup.getCheckedRadioButtonId();
            RadioButton valgtPayPeriodRadioButton = findViewById(valgtPayPeriodRadio);
            udbetalingsPeriode = valgtPayPeriodRadioButton.getText().toString();
        }

        MaanedsLoenData maanedsLoenData = new MaanedsLoenData(traekProcent, maanedligtFradrag, indkomstType, biEllerHoved, udbetalingsPeriode, maanedligeIndkomst);
        double udbetaling = dataUdregner.udregnMaanedsLoen(maanedsLoenData);
        double aarligUdbetaling = udbetaling * 12;

        Intent intent = new Intent(this, UdregningResultat.class);

        intent.putExtra("TYPE_UDREGNING", "MÅNEDSLØN");
        intent.putExtra("UDBETALING", udbetaling);
        intent.putExtra("AARLIG_UDBETALING", aarligUdbetaling);

        startActivity(intent);


    }
}