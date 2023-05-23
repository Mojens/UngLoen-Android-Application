package com.example.ungloen_mobile_exam.model;

public class MaanedsLoenData {

    double traekProcent;
    double maanedligtFradrag;
    double maanedligeIndkomst;
    String indkomstType;
    String biEllerHoved;
    String udbetalingsPeriode;

    public MaanedsLoenData(double traekProcent, double maanedligtFradrag, String indkomstType, String biEllerHoved, String udbetalingsPeriode, double maanedligeIndkomst) {
        this.traekProcent = traekProcent;
        this.maanedligtFradrag = maanedligtFradrag;
        this.indkomstType = indkomstType;
        this.biEllerHoved = biEllerHoved;
        this.udbetalingsPeriode = udbetalingsPeriode;
        this.maanedligeIndkomst = maanedligeIndkomst;
    }

    public double getTraekProcent() {
        return traekProcent;
    }

    public void setTraekProcent(double traekProcent) {
        this.traekProcent = traekProcent;
    }

    public double getMaanedligtFradrag() {
        return maanedligtFradrag;
    }

    public void setMaanedligtFradrag(double maanedligtFradrag) {
        this.maanedligtFradrag = maanedligtFradrag;
    }

    public String getIndkomstType() {
        return indkomstType;
    }

    public void setIndkomstType(String indkomstType) {
        this.indkomstType = indkomstType;
    }

    public String getBiEllerHoved() {
        return biEllerHoved;
    }

    public void setBiEllerHoved(String biEllerHoved) {
        this.biEllerHoved = biEllerHoved;
    }

    public String getUdbetalingsPeriode() {
        return udbetalingsPeriode;
    }

    public void setUdbetalingsPeriode(String udbetalingsPeriode) {
        this.udbetalingsPeriode = udbetalingsPeriode;
    }

    public double getMaanedligeIndkomst() {
        return maanedligeIndkomst;
    }

    public void setMaanedligeIndkomst(double maanedligeIndkomst) {
        this.maanedligeIndkomst = maanedligeIndkomst;
    }

    @Override
    public String toString() {
        return "MaanedsLoenData{" +
                "traekProcent=" + traekProcent +
                ", maanedligtFradrag=" + maanedligtFradrag +
                ", maanedligeIndkomst=" + maanedligeIndkomst +
                ", indkomstType='" + indkomstType + '\'' +
                ", biEllerHoved='" + biEllerHoved + '\'' +
                ", udbetalingsPeriode='" + udbetalingsPeriode + '\'' +
                '}';
    }
}
