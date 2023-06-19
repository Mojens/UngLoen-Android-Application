package com.example.ungloen_mobile_exam.model;

public class KoerselsFradragData {

    double kilometerTilArbejde;
    int arbejdsdageMedTransport;

    public KoerselsFradragData(double kilometerTilArbejde, int arbejdsdageMedTransport) {
        this.kilometerTilArbejde = kilometerTilArbejde;
        this.arbejdsdageMedTransport = arbejdsdageMedTransport;
    }

    public double getKilometerTilArbejde() {
        return kilometerTilArbejde;
    }

    public void setKilometerTilArbejde(double kilometerTilArbejde) {
        this.kilometerTilArbejde = kilometerTilArbejde;
    }

    public int getArbejdsdageMedTransport() {
        return arbejdsdageMedTransport;
    }

    public void setArbejdsdageMedTransport(int arbejdsdageMedTransport) {
        this.arbejdsdageMedTransport = arbejdsdageMedTransport;
    }

    @Override
    public String toString() {
        return "KoerselsFradragData{" +
                "kilometerTilArbejde=" + kilometerTilArbejde +
                ", arbejdsdageMedTransport=" + arbejdsdageMedTransport +
                '}';
    }
}
