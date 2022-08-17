package com.mvpbe.mvpbe.dto;

import java.util.Date;

public class TableResponse {

    private double importoGiornaliero;
    private double importoAnnuale;
    private double importoUltimi90;
    private long numeroScontrini;
    private int numSi;
    private double valSi;
    private double prezzoMedio;
    private Date ultimaOra;
    private String negozio;


    public TableResponse(){}

// TableResponse(s.negozio,SUM(s.totale),COUNT(s),AVG(s.totale),MAX(s.ora)


    public TableResponse(String negozio,double importoGiornaliero,long numeroScontrini,double prezzoMedio, Date ultimaOra){
        this.negozio = negozio;
        this.importoGiornaliero = importoGiornaliero;
        this.numeroScontrini = numeroScontrini;
        this.prezzoMedio = prezzoMedio;
        this.ultimaOra = ultimaOra;
    }


    public String getNegozio() {
        return negozio;
    }

    public Date getUltimaOra() {
        return ultimaOra;
    }

    public double getImportoAnnuale() {
        return importoAnnuale;
    }

    public double getImportoGiornaliero() {
        return importoGiornaliero;
    }

    public double getImportoUltimi90() {
        return importoUltimi90;
    }

    public double getPrezzoMedio() {
        return prezzoMedio;
    }

    public double getValSi() {
        return valSi;
    }

    public long getNumeroScontrini() {
        return numeroScontrini;
    }

    public int getNumSi() {
        return numSi;
    }


    public void setNegozio(String negozio) {
        this.negozio = negozio;
    }

    public void setImportoAnnuale(double importoAnnuale) {
        this.importoAnnuale = importoAnnuale;
    }


    public void setImportoGiornaliero(double importoGiornaliero) {
        this.importoGiornaliero = importoGiornaliero;
    }

    public void setImportoUltimi90(double importoUltimi90) {
        this.importoUltimi90 = importoUltimi90;
    }

    public void setNumeroScontrini(long numeroScontrini) {
        this.numeroScontrini = numeroScontrini;
    }

    public void setNumSi(int numSi) {
        this.numSi = numSi;
    }

    public void setPrezzoMedio(double prezzoMedio) {
        this.prezzoMedio = prezzoMedio;
    }

    public void setUltimaOra(Date ultimaOra) {
        this.ultimaOra = ultimaOra;
    }

    public void setValSi(double valSi) {
        this.valSi = valSi;
    }
}
