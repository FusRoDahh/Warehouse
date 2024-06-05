package models;

public class Magazziniere extends Utente {
    public Magazziniere (String nomeUtente, String password) {
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.tipo = "magazziniere";
    }

    @Override
    public String toString() {
        return "Username: " + nomeUtente  +
                ", password: " + password +
                ", tipo: " + tipo
                ;
    }
}
