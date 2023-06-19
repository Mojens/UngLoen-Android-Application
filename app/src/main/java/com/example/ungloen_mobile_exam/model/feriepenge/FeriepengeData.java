package com.example.ungloen_mobile_exam.model.feriepenge;

public class FeriepengeData {

    double loenIndkomst;
    int maaneder;
    double feriepengeSats = 12.5;

    public FeriepengeData(double loenIndkomst, int maaneder) {
        this.loenIndkomst = loenIndkomst;
        this.maaneder = maaneder;
    }

    public void setLoenIndkomst(double loenIndkomst) {
        this.loenIndkomst = loenIndkomst;
    }

    public int getMaaneder() {
        return maaneder;
    }

    public void setMaaneder(int maaneder) {
        this.maaneder = maaneder;
    }

    public double getLoenIndkomst() {
        return loenIndkomst;
    }

    public double getFeriepengeSats() {
        return feriepengeSats;
    }

    @Override
    public String toString() {
        return "FeriepengeData{" +
                "loenIndkomst=" + loenIndkomst +
                ", maaneder=" + maaneder +
                '}';
    }
}
