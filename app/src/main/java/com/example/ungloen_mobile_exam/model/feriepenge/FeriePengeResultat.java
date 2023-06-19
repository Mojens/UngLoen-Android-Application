package com.example.ungloen_mobile_exam.model.feriepenge;

public class FeriePengeResultat {

    double feriepengeAar;
    double ferieDageOptjent;
    double feriePengeOptjent;

    public FeriePengeResultat() {
    }

    public FeriePengeResultat(double feriepengeAar, double ferieDageOptjent, double feriePengeOptjent) {
        this.feriepengeAar = feriepengeAar;
        this.ferieDageOptjent = ferieDageOptjent;
        this.feriePengeOptjent = feriePengeOptjent;
    }

    public double getFeriepengeAar() {
        return feriepengeAar;
    }

    public void setFeriepengeAar(double feriepengeAar) {
        this.feriepengeAar = feriepengeAar;
    }

    public double getFerieDageOptjent() {
        return ferieDageOptjent;
    }

    public void setFerieDageOptjent(double ferieDageOptjent) {
        this.ferieDageOptjent = ferieDageOptjent;
    }

    public double getFeriePengeOptjent() {
        return feriePengeOptjent;
    }

    public void setFeriePengeOptjent(double feriePengeOptjent) {
        this.feriePengeOptjent = feriePengeOptjent;
    }

    @Override
    public String toString() {
        return "FeriePengeResultat{" +
                "feriepengeAar=" + feriepengeAar +
                ", ferieDageOptjent=" + ferieDageOptjent +
                ", feriePengeOptjent=" + feriePengeOptjent +
                '}';
    }
}
