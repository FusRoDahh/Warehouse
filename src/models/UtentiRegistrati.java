package models;

import java.util.ArrayList;

public class UtentiRegistrati {
    private final ArrayList<Cliente> ClientiRegistrati = new ArrayList<>();
    private final ArrayList<Magazziniere> MagazzinieriRegistrati = new ArrayList<>();


    public void aggiungiCliente(Cliente utente) {
        this.ClientiRegistrati.add(utente);
    }
    public void aggiungiMagazziniere(Magazziniere utente) {
        this.MagazzinieriRegistrati.add(utente);
    }
    public ArrayList<Cliente> getClientiRegistrati() {
        return this.ClientiRegistrati;
    }
    public ArrayList<Magazziniere> getMagazzinieriRegistrati() {
        return this.MagazzinieriRegistrati;
    }
    public void eliminaCliente (Cliente cliente){
        ClientiRegistrati.remove(cliente);
    }
    public void eliminaMagazziniere (Magazziniere magazziniere){
        MagazzinieriRegistrati.remove(magazziniere);
    }
}
