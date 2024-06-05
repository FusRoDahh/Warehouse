package models;

import java.util.ArrayList;

public class Admin extends Utente{

    private final ArrayList<String> keys = new ArrayList<>();
    public Admin() {
    }

    public Admin(String username, String password) {
        this.nomeUtente = username;
        this.password = password;
        this.tipo="admin";
    }

    public void addKey (String newKey){
        for (String key : keys){
            if (key.equals(newKey)){
                System.out.println("Chiave esistente");
                return;
            }
        }
        keys.add(newKey);
        System.out.println("Chiave Aggiunta!");
    }
    public void removeKey (String key){
        keys.remove(key);
    }

    public ArrayList<String> getKeys() {
        return keys;
    }

    public void visualizzaKeys(){
        for (String key:keys){
            System.out.println(key);
        }
    }

}
