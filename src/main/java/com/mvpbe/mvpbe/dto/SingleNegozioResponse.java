package com.mvpbe.mvpbe.dto;

import java.util.Date;

public class SingleNegozioResponse {

    // t.negozio AS negozio, SUM(t.totale) AS importoGiornaliero, COUNT(*) AS numeroScontrini, AVG(t.totale) AS prezzoMedio, MAX(t.ora) AS ultimaOra
    private String negozio;
    private double importoGiornaliero;
    private long numeroScontrini;
    private double prezzoMedio;
    private Date ultimaOra;
    private String codiceCategoria;


    public SingleNegozioResponse(){

    }


    public SingleNegozioResponse(String codiceCategoria, String negozio, double importoGiornaliero, long numeroScontrini, double prezzoMedio, Date ultimaOra){
        this.negozio = negozio;
        this.importoGiornaliero = importoGiornaliero;
        this.numeroScontrini = numeroScontrini;
        this.prezzoMedio = prezzoMedio;
        this.ultimaOra = ultimaOra;
        this.codiceCategoria = codiceCategoria;
    }

    public String getNegozio() {
        return negozio;
    }

    public long getNumeroScontrini() {
        return numeroScontrini;
    }

    public double getPrezzoMedio() {
        return prezzoMedio;
    }

    public double getImportoGiornaliero() {
        return importoGiornaliero;
    }

    public Date getUltimaOra() {
        return ultimaOra;
    }

    public String getCodiceCategoria() {
        return codiceCategoria;
    }

    public void setNumeroScontrini(long numeroScontrini) {
        this.numeroScontrini = numeroScontrini;
    }

    public void setNegozio(String negozio) {
        this.negozio = negozio;
    }

    public void setUltimaOra(Date ultimaOra) {
        this.ultimaOra = ultimaOra;
    }

    public void setPrezzoMedio(double prezzoMedio) {
        this.prezzoMedio = prezzoMedio;
    }

    public void setImportoGiornaliero(double importoGiornaliero) {
        this.importoGiornaliero = importoGiornaliero;
    }

    public void setCodiceCategoria(String codiceCategoria) {
        this.codiceCategoria = codiceCategoria;
    }
}
