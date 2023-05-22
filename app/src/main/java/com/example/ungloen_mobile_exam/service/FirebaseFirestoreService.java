package com.example.ungloen_mobile_exam.service;

import android.util.Log;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.DecimalFormat;

import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class FirebaseFirestoreService {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public FirebaseFirestoreService() {
    }

    public void gennemsnitMaanedsLoen(TextView textView) {
        db.collection("maanedsLøn")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Double> beloebValues = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Object beloebObject = document.getData().get("Beløb");
                            if (beloebObject instanceof Number) {
                                double beloeb = ((Number) beloebObject).doubleValue();
                                beloebValues.add(beloeb);
                            }
                        }

                        double total = 0;
                        for (double belob : beloebValues) {
                            total += belob;
                        }

                        double average = beloebValues.isEmpty() ? 0 : total / beloebValues.size();

                        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("da-DK"));
                        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
                        String formattedAverage = decimalFormat.format(average);

                        Log.i("Firebase", "Average: " + formattedAverage);
                        String text = formattedAverage + " kr.";
                        textView.setText(text);

                    } else {
                        Log.i("Firebase", "Error getting documents: ", task.getException());
                    }
                });
    }

    public void gennemsnitAarligLoen(TextView textView) {
        db.collection("aarligLøn")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Double> beloebValues = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Object beloebObject = document.getData().get("Beløb");
                            if (beloebObject instanceof Number) {
                                double beloeb = ((Number) beloebObject).doubleValue();
                                beloebValues.add(beloeb);
                            }
                        }

                        double total = 0;
                        for (double belob : beloebValues) {
                            total += belob;
                        }

                        double average = beloebValues.isEmpty() ? 0 : total / beloebValues.size();

                        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("da-DK"));
                        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
                        String formattedAverage = decimalFormat.format(average);

                        Log.i("Firebase", "Average: " + formattedAverage);
                        String text = formattedAverage + " kr.";
                        textView.setText(text);

                    } else {
                        Log.i("Firebase", "Error getting documents: ", task.getException());
                    }
                });
    }

    public void gennemsnitKoerselsFradrag(TextView textView) {
        db.collection("koerselsFradrag")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Double> beloebValues = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Object beloebObject = document.getData().get("Beløb");
                            if (beloebObject instanceof Number) {
                                double beloeb = ((Number) beloebObject).doubleValue();
                                beloebValues.add(beloeb);
                            }
                        }

                        double total = 0;
                        for (double belob : beloebValues) {
                            total += belob;
                        }

                        double average = beloebValues.isEmpty() ? 0 : total / beloebValues.size();

                        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("da-DK"));
                        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
                        String formattedAverage = decimalFormat.format(average);

                        Log.i("Firebase", "Average: " + formattedAverage);
                        String text = formattedAverage + " kr.";
                        textView.setText(text);

                    } else {
                        Log.i("Firebase", "Error getting documents: ", task.getException());
                    }
                });
    }

    public void gennemsnitFeriepenge(TextView textView) {
        db.collection("feriepenge")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Double> beloebValues = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Object beloebObject = document.getData().get("Beløb");
                            if (beloebObject instanceof Number) {
                                double beloeb = ((Number) beloebObject).doubleValue();
                                beloebValues.add(beloeb);
                            }
                        }

                        double total = 0;
                        for (double belob : beloebValues) {
                            total += belob;
                        }

                        double average = beloebValues.isEmpty() ? 0 : total / beloebValues.size();

                        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("da-DK"));
                        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
                        String formattedAverage = decimalFormat.format(average);

                        Log.i("Firebase", "Average: " + formattedAverage);
                        String text = formattedAverage + " kr.";
                        textView.setText(text);

                    } else {
                        Log.i("Firebase", "Error getting documents: ", task.getException());
                    }
                });
    }

    public void tilfoejMaanedsLoen(double beloeb){
        Map<String, Object> data = new HashMap<>();
        data.put("Beløb", beloeb);

        db.collection("maanedsLøn")
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    String documentId = documentReference.getId();
                    Log.i("Firebase", "Document added with ID: " + documentId);
                })
                .addOnFailureListener(e -> {
                    String errorMessage = e.getMessage();
                    Log.i("Firebase", "Error adding document: " + errorMessage);
                });
    }

    public void tilfoejAarligLoen(double beloeb){
        Map<String, Object> data = new HashMap<>();
        data.put("Beløb", beloeb);

        db.collection("aarligLøn")
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    String documentId = documentReference.getId();
                    Log.i("Firebase", "Document added with ID: " + documentId);
                })
                .addOnFailureListener(e -> {
                    String errorMessage = e.getMessage();
                    Log.i("Firebase", "Error adding document: " + errorMessage);
                });
    }

    public void tilfoejKoerselsFradrag(double beloeb){
        Map<String, Object> data = new HashMap<>();
        data.put("Beløb", beloeb);

        db.collection("koerselsFradrag")
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    String documentId = documentReference.getId();
                    Log.i("Firebase", "Document added with ID: " + documentId);
                })
                .addOnFailureListener(e -> {
                    String errorMessage = e.getMessage();
                    Log.i("Firebase", "Error adding document: " + errorMessage);
                });
    }

    public void tilfoejFeriepenge(double beloeb){
        Map<String, Object> data = new HashMap<>();
        data.put("Beløb", beloeb);

        db.collection("feriepenge")
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    String documentId = documentReference.getId();
                    Log.i("Firebase", "Document added with ID: " + documentId);
                })
                .addOnFailureListener(e -> {
                    String errorMessage = e.getMessage();
                    Log.i("Firebase", "Error adding document: " + errorMessage);
                });
    }

}
