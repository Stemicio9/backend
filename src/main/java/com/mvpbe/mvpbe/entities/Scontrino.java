package com.mvpbe.mvpbe.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Scontrino {

    @Id
    @GeneratedValue
    private long id;

    private String cap;
    private String negozio;
    private Date data;
    private Date ora;
    private String profilo;
    private double numero;
    private Date dataRegistrazione;
    private String codiceCliente;
    private String ragioneSociale;
    private String codice_ss;
    private String codiceArticolo;
    private String descrizione;
    private int quantita;
    private double imponibile;
    private double imposta;
    private double totale;
    private double ultimoCosto;
    private Date adesso;

    public long getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getQuantita() {
        return quantita;
    }

    public String getCap() {
        return cap;
    }

    public double getImposta() {
        return imposta;
    }

    public Date getDataRegistrazione() {
        return dataRegistrazione;
    }

    public Date getOra() {
        return ora;
    }

    public double getImponibile() {
        return imponibile;
    }

    public double getNumero() {
        return numero;
    }

    public String getCodiceArticolo() {
        return codiceArticolo;
    }

    public String getCodiceCliente() {
        return codiceCliente;
    }

    public String getCodice_ss() {
        return codice_ss;
    }

    public String getNegozio() {
        return negozio;
    }

    public String getProfilo() {
        return profilo;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public double getTotale() {
        return totale;
    }

    public Date getAdesso() {
        return adesso;
    }

    public double getUltimoCosto() {
        return ultimoCosto;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public void setOra(Date ora) {
        this.ora = ora;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public void setImposta(double imposta) {
        this.imposta = imposta;
    }

    public void setCodiceArticolo(String codiceArticolo) {
        this.codiceArticolo = codiceArticolo;
    }

    public void setNegozio(String negozio) {
        this.negozio = negozio;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public void setCodiceCliente(String codiceCliente) {
        this.codiceCliente = codiceCliente;
    }

    public void setAdesso(Date adesso) {
        this.adesso = adesso;
    }

    public void setProfilo(String profilo) {
        this.profilo = profilo;
    }

    public void setCodice_ss(String codice_ss) {
        this.codice_ss = codice_ss;
    }

    public void setImponibile(double imponibile) {
        this.imponibile = imponibile;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public void setUltimoCosto(double ultimoCosto) {
        this.ultimoCosto = ultimoCosto;
    }


    public void setDataRegistrazione(Date dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }
}
