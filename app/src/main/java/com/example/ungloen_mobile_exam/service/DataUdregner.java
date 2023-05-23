package com.example.ungloen_mobile_exam.service;

import android.util.Log;

import com.example.ungloen_mobile_exam.model.KoerselsFradragData;
import com.example.ungloen_mobile_exam.model.MaanedsLoenData;

public class DataUdregner {

    private final FirebaseFirestoreService firebaseFirestoreService = new FirebaseFirestoreService();

    public double udregnMaanedsLoen(MaanedsLoenData maanedsLoenData) {
        double maanedligeUdbetaling = 0.0;
        Log.i("DataUdregner", "udregnMaanedsLoen: " + maanedsLoenData);
        String udbetalingsPeriode = maanedsLoenData.getUdbetalingsPeriode();
        String indkomstType = maanedsLoenData.getIndkomstType();
        double maanedligeIndkomst = maanedsLoenData.getMaanedligeIndkomst();
        String biEllerHoved = maanedsLoenData.getBiEllerHoved();
        double traekProcent = maanedsLoenData.getTraekProcent();

        double laborContributionRate = 0.08;

        if (indkomstType.equals("Løn")) {
            double laborContribution = maanedligeIndkomst * laborContributionRate;

            if (biEllerHoved.equals("Hovedkort") && udbetalingsPeriode.equals("Pr. Måned")) {
                double incomeAfterLaborContributionAndDeduction = maanedligeIndkomst - laborContribution - maanedsLoenData.getMaanedligtFradrag();
                double taxToPay = incomeAfterLaborContributionAndDeduction * (traekProcent / 100);

                maanedligeUdbetaling = incomeAfterLaborContributionAndDeduction - taxToPay + maanedsLoenData.getMaanedligtFradrag();
            } else if (biEllerHoved.equals("Hovedkort") && udbetalingsPeriode.equals("Hver anden uge")) {
                double incomeAfterLaborContributionAndDeduction = maanedligeIndkomst - laborContribution - (maanedsLoenData.getMaanedligtFradrag() * 0.46);
                double taxToPay = incomeAfterLaborContributionAndDeduction * (traekProcent / 100);

                maanedligeUdbetaling = incomeAfterLaborContributionAndDeduction - taxToPay + (maanedsLoenData.getMaanedligtFradrag() * 0.46);
            } else if (biEllerHoved.equals("Bikort") && udbetalingsPeriode.equals("Pr. Måned")) {
                double incomeAfterLaborContributionAndDeduction = maanedligeIndkomst - laborContribution;
                double taxToPay = incomeAfterLaborContributionAndDeduction * (traekProcent / 100);

                maanedligeUdbetaling = incomeAfterLaborContributionAndDeduction - taxToPay;
            } else if (biEllerHoved.equals("Bikort") && udbetalingsPeriode.equals("Hver anden uge")) {
                double incomeAfterLaborContributionAndDeduction = maanedligeIndkomst - laborContribution;
                double taxToPay = incomeAfterLaborContributionAndDeduction * (traekProcent / 100);

                maanedligeUdbetaling = incomeAfterLaborContributionAndDeduction - taxToPay;
            }
        } else {
            if (biEllerHoved.equals("Hovedkort")) {
                double incomeAfterDeduction = maanedligeIndkomst - maanedsLoenData.getMaanedligtFradrag();
                double taxToPay = incomeAfterDeduction * (traekProcent / 100);

                maanedligeUdbetaling = incomeAfterDeduction - taxToPay + maanedsLoenData.getMaanedligtFradrag();
            } else if (biEllerHoved.equals("Bikort")) {
                double taxToPay = maanedligeIndkomst * (traekProcent / 100);

                maanedligeUdbetaling = maanedligeIndkomst - taxToPay;
            }
        }
        firebaseFirestoreService.tilfoejMaanedsLoen(maanedligeUdbetaling);
        double aarligLoen = maanedligeUdbetaling * 12;
        firebaseFirestoreService.tilfoejAarligLoen(aarligLoen);
        return maanedligeUdbetaling;
    }

    public double udregnKoerselsFradrag(KoerselsFradragData koerselsFradragData){
        return 0.0;
    }
}
