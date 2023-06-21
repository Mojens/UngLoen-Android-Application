package com.example.ungloen_mobile_exam.service;

import android.util.Log;

import com.example.ungloen_mobile_exam.model.feriepenge.FeriePengeResultat;
import com.example.ungloen_mobile_exam.model.feriepenge.FeriepengeData;
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

        double amBidragSats = 0.08;

        if (indkomstType.equals("Løn")) {
            double amBidrag = maanedligeIndkomst * amBidragSats;

            if (biEllerHoved.equals("Hovedkort") && udbetalingsPeriode.equals("Pr. Måned")) {
                double indkomstEfterAMBidragOgFradrag = maanedligeIndkomst - amBidrag - maanedsLoenData.getMaanedligtFradrag();
                double skatAtBetale = indkomstEfterAMBidragOgFradrag * (traekProcent / 100);

                maanedligeUdbetaling = indkomstEfterAMBidragOgFradrag - skatAtBetale + maanedsLoenData.getMaanedligtFradrag();
            } else if (biEllerHoved.equals("Hovedkort") && udbetalingsPeriode.equals("Hver anden uge")) {
                double indkomstEfterAMBidragOgFradrag = maanedligeIndkomst - amBidrag - (maanedsLoenData.getMaanedligtFradrag() * 0.46);
                double skatAtBetale = indkomstEfterAMBidragOgFradrag * (traekProcent / 100);

                maanedligeUdbetaling = indkomstEfterAMBidragOgFradrag - skatAtBetale + (maanedsLoenData.getMaanedligtFradrag() * 0.46);
            } else if (biEllerHoved.equals("Bikort") && udbetalingsPeriode.equals("Pr. Måned")) {
                double indkomstEfterAMBidragOgFradrag = maanedligeIndkomst - amBidrag;
                double skatAtBetale = indkomstEfterAMBidragOgFradrag * (traekProcent / 100);

                maanedligeUdbetaling = indkomstEfterAMBidragOgFradrag - skatAtBetale;
            } else if (biEllerHoved.equals("Bikort") && udbetalingsPeriode.equals("Hver anden uge")) {
                double indkomstEfterAMBidragOgFradrag = maanedligeIndkomst - amBidrag;
                double skatAtBetale = indkomstEfterAMBidragOgFradrag * (traekProcent / 100);

                maanedligeUdbetaling = indkomstEfterAMBidragOgFradrag - skatAtBetale;
            }
        } else {
            if (biEllerHoved.equals("Hovedkort")) {
                double indkomstsEfterFradrag = maanedligeIndkomst - maanedsLoenData.getMaanedligtFradrag();
                double skatAtBetale = indkomstsEfterFradrag * (traekProcent / 100);

                maanedligeUdbetaling = indkomstsEfterFradrag - skatAtBetale + maanedsLoenData.getMaanedligtFradrag();
            } else if (biEllerHoved.equals("Bikort")) {
                double skatAtBetale = maanedligeIndkomst * (traekProcent / 100);

                maanedligeUdbetaling = maanedligeIndkomst - skatAtBetale;
            }
        }
        firebaseFirestoreService.tilfoejMaanedsLoen(maanedligeUdbetaling);
        double aarligLoen = maanedligeUdbetaling * 12;
        firebaseFirestoreService.tilfoejAarligLoen(aarligLoen);
        return maanedligeUdbetaling;
    }

    public double udregnKoerselsFradrag(KoerselsFradragData koerselsFradragData) {
        double koerselsFradrag = 0.0;
        double kilometerTilArbejde = koerselsFradragData.getKilometerTilArbejde();
        int arbejdsdageMedTransport = koerselsFradragData.getArbejdsdageMedTransport();

        if (kilometerTilArbejde > 24) {
            koerselsFradrag = (kilometerTilArbejde - 24) * 1.93 * arbejdsdageMedTransport;
        }
        if (koerselsFradrag > 0.0) {
            firebaseFirestoreService.tilfoejKoerselsFradrag(koerselsFradrag);

        }
        return koerselsFradrag;
    }

    public FeriePengeResultat udregnFeriepenge(FeriepengeData feriepengeData){
        FeriePengeResultat feriePengeResultat = new FeriePengeResultat();
        double loenIndkomst = feriepengeData.getLoenIndkomst();
        int maaneder = feriepengeData.getMaaneder();
        double ferieDageOptjent = maaneder * 2.08;
        double feriepengeAfIndkomst = (loenIndkomst * feriepengeData.getFeriepengeSats())/100;
        double feriepengeOptjent = feriepengeAfIndkomst * maaneder;
        double feriepengeAar = feriepengeAfIndkomst * 12;
        firebaseFirestoreService.tilfoejFeriepenge(feriepengeAar);
        feriePengeResultat.setFeriePengeOptjent(feriepengeOptjent);
        feriePengeResultat.setFeriepengeAar(feriepengeAar);
        feriePengeResultat.setFerieDageOptjent(ferieDageOptjent);
        return feriePengeResultat;
    }
}
