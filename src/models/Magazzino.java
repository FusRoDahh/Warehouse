package models;

import java.util.ArrayList;

public class Magazzino {
    private ArrayList<Prodotto> magazzino = new ArrayList<Prodotto>();

    public ArrayList<Prodotto> getMagazzino() {
        return magazzino;
    }

    public void aggiungiProdotto(Prodotto prodottoDaAggiungere){
        for(Prodotto prodotto:magazzino) {
            if (
                    prodottoDaAggiungere.getTipoProdotto().equalsIgnoreCase(prodotto.getTipoProdotto())&&
                    prodottoDaAggiungere.getProduttore().equalsIgnoreCase(prodotto.getProduttore())&&
                    prodottoDaAggiungere.getModello().equalsIgnoreCase(prodotto.getModello())&&
                    prodottoDaAggiungere.getDimensioniDisplay() == prodotto.getDimensioniDisplay()&&
                    prodottoDaAggiungere.getDimensioneMemoria() == prodotto.getDimensioneMemoria()&&
                    prodottoDaAggiungere.getPrezzoVendita() == prodotto.getPrezzoVendita()&&
                    prodottoDaAggiungere.getPrezzoAcquisto() == prodotto.getPrezzoAcquisto()
            ){
                prodotto.setQuantita(prodotto.getQuantita()+1);
                return;
            }
        }
        this.magazzino.add(prodottoDaAggiungere);
    }
    public void rimuoviProdotto(String id){
        Prodotto prodottoTemp=cercaProdottoPerId(id);
        if (prodottoTemp.getQuantita() <= 0){
            System.out.println("Prodotto inesistente");
        }else if(prodottoTemp.getQuantita() == 1){
            this.magazzino.remove(prodottoTemp);
            System.out.println("Prodotto eliminato con successo");
        }else {
            cercaProdottoPerId(id).setQuantita(cercaProdottoPerId(id).getQuantita()-1);
            System.out.println("Prodotto eliminato con successo");
        }
    }
    public Prodotto cercaProdottoPerId(String id){
        Prodotto prodottoNull = new Prodotto("inesistente");
        for(Prodotto prodotto:this.magazzino){
            if (prodotto.getId().equalsIgnoreCase(id)) {
                return prodotto;
            }
        }
        return prodottoNull;
    }
    public void cercaProdottoPerModello(String modello){
        int counter=0;
        for(Prodotto prodotto:this.magazzino){
            if (prodotto.getModello().equalsIgnoreCase(modello)) {
                System.out.println(prodotto);
                counter ++;
            }
        }
        if(counter == 0){
            System.out.println("Nessun prodotto trovato");
        }
    }
    public void cercaProdottoPerTipo(String tipoDispositivo){
        int counter=0;
        for(Prodotto prodotto:this.magazzino){
            if (prodotto.getTipoProdotto().equalsIgnoreCase(tipoDispositivo)) {
                System.out.println(prodotto);
                counter ++;
            }
        }
        if(counter == 0){
            System.out.println("NESSSUN PRODOTTO TROVATO");
        }
    }
    public void cercaProdottoPerProduttore(String produttore){
        int counter=0;
        for(Prodotto prodotto:this.magazzino){
            if (prodotto.getProduttore().equalsIgnoreCase(produttore)) {
                System.out.println(prodotto);
                counter ++;
            }
        }
        if(counter == 0){
            System.out.println("Nessun prodotto trovato");
        }
    }
    public void cercaProdottoPerPrezzoVendita(float prezzoVendita){
        int counter=0;
        for(Prodotto prodotto:this.magazzino){
            if (prodotto.getPrezzoVendita() == prezzoVendita) {
                System.out.println(prodotto);
                counter ++;
            }
        }
        if(counter == 0){
            System.out.println("Nessun prodotto trovato");
        }
    }
    public void cercaProdottoPerPrezzoAcquito(float prezzoAcquito){
        int counter=0;
        for(Prodotto prodotto:this.magazzino){
            if (prodotto.getPrezzoAcquisto() == prezzoAcquito) {
                System.out.println(prodotto);
                counter ++;
            }
        }
        if(counter == 0){
            System.out.println("Nessun prodotto trovato");
        }
    }
    public void cercaProdottoPerRangePrezzo(float prezzoInferiore, float prezzoMaggiore){
        int counter=0;
        for(Prodotto prodotto:this.magazzino){
            if (prodotto.getPrezzoVendita() > prezzoInferiore && prodotto.getPrezzoVendita() < prezzoMaggiore) {
                System.out.println(prodotto);
                counter ++;
            }
        }
        if(counter == 0){
            System.out.println("Nessun prodotto trovato");
        }
    }
    public void mediaPrezzoAcquisto(){
        double prezzoAcquistoTotale = 0;
        for (Prodotto prodotto:magazzino){
            prezzoAcquistoTotale += prodotto.getPrezzoAcquisto();
        }
        double mediaPrezzoAcquisto = prezzoAcquistoTotale/magazzino.size();
    }
    public boolean stampaMagazzino(){
        if (magazzino.isEmpty()){
            System.out.println("magazzino vuoto");
            return false;
        }
        for (Prodotto prodotto:this.magazzino){
            System.out.println(prodotto);
        }
        return true;
    }
}
