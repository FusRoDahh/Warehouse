package models;

import java.time.OffsetDateTime;

public class Prodotto {
    private String id;
    private String tipoProdotto;
    private String produttore;
    private String modello;
    private float dimensioniDisplay;
    private float dimensioneMemoria;
    private float prezzoAcquisto;
    private float prezzoVendita;
    private int quantita;
    private String descrizione;

    public Prodotto(String tipoProdotto) {
        this.tipoProdotto = tipoProdotto;
    }

    public Prodotto() {}

    public Prodotto(String tipoProdotto, String produttore, String modello, float dimensioniDisplay,
                    float dimensioneMemoria, float prezzoAcquisto, float prezzoVendita, String descrizione) {
        this.id = generateId();
        this.tipoProdotto = tipoProdotto;
        this.produttore = produttore;
        this.modello = modello;
        this.dimensioniDisplay = dimensioniDisplay;
        this.dimensioneMemoria = dimensioneMemoria;
        this.prezzoAcquisto = prezzoAcquisto;
        this.prezzoVendita = prezzoVendita;
        this.quantita = 1;
        this.descrizione = descrizione;
    }
    public String generateId(){
        long millis = System.currentTimeMillis();
        millis -= 1712000000000L;
        return Long.toString(millis);
    }
    public String getId() {
        return id;
    }

    public String getTipoProdotto() {
        return tipoProdotto;
    }

    public String getProduttore() {
        return produttore;
    }

    public String getModello() {
        return modello;
    }

    public double getPrezzoAcquisto() {
        return prezzoAcquisto;
    }

    public double getPrezzoVendita() {
        return prezzoVendita;
    }

    public float getDimensioniDisplay() {
        return dimensioniDisplay;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public float getDimensioneMemoria() {
        return dimensioneMemoria;
    }

    public int getQuantita() {
        return quantita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public String toString() {
        return
                "id= " + id +
                ", tipoProdotto= " + tipoProdotto  +
                ", produttore= " + produttore +
                ", modello= " + modello  +
                ", dimensioniDisplay= " + dimensioniDisplay +
                ", dimensioneMemoria= " + dimensioneMemoria +
                ", prezzoAcquisto= " + prezzoAcquisto +
                ", prezzoVendita= " + prezzoVendita +
                ", quantità= " + quantita +
                ", descrizione= " + descrizione
                ;
    }
}
