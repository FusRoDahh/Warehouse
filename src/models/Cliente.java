package models;


public class Cliente extends Utente {
    private Carrello carrello = new Carrello();

    public Cliente (String nomeUtente, String password) {
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.tipo = "cliente";
    }

    public void  aggiungiAlCarrello(Prodotto prodotto) {
        this.carrello.aggiungiProdottoAlCarrello(prodotto);
    }

    public void rimuoviDalCarrello(Prodotto prodotto) {
        this.carrello.rimuoviProdottoDalCarrello(prodotto);
    }

    public void mostraCarrello() {
        this.carrello.mostraCarrello();
    }

    public void finalizzaAcquisto() {
        this.carrello.finalizzaAcquisto();
    }
    public Prodotto cercaProdotto(String id) {
        return this.carrello.cercaNelCarrello(id);
    }

    public double prezzoFinale() {
        return this.carrello.prezzoTotaleCarrello();
    }

    public boolean isEmpty() {
        return this.carrello.isEmpty();
    }

    public Carrello getCarrello() {
        return carrello;
    }

    @Override
    public String toString() {
        return "Username: " + nomeUtente  +
                ", password: " + password +
                ", tipo: " + tipo
                ;
    }
}
