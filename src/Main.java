import com.google.gson.Gson;
import models.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) throws IOException {
        String PATH_MAGAZZINO = ".\\save_files\\magazzino.json";
        String PATH_UTENTI = ".\\save_files\\utenti.json";
        String PATH_ADMIN = ".\\save_files\\admin.json";

        Admin admin = loadAdmin(PATH_ADMIN);

        UtentiRegistrati utenti = loadUser(PATH_UTENTI);

        Magazzino magazzino = new Magazzino();
        magazzino = loadMagazzino(PATH_MAGAZZINO, magazzino);

        System.out.println("""
                         _____         _     _____                         _       _____                             _   _            \s
                        |_   _|       | |   /  __ \\                       | |     /  __ \\                           | | (_)           \s
                          | | ___  ___| |__ | /  \\/ __ _ _ __  _ __   ___ | | ___ | /  \\/ ___  _ __  _ __   ___  ___| |_ _  ___  _ __ \s
                          | |/ _ \\/ __| '_ \\| |    / _` | '_ \\| '_ \\ / _ \\| |/ _ \\| |    / _ \\| '_ \\| '_ \\ / _ \\/ __| __| |/ _ \\| '_ \\\s
                          | |  __/ (__| | | | \\__/\\ (_| | | | | | | | (_) | | (_) | \\__/\\ (_) | | | | | | |  __/ (__| |_| | (_) | | | |
                          \\_/\\___|\\___|_| |_|\\____/\\__,_|_| |_|_| |_|\\___/|_|\\___/ \\____/\\___/|_| |_|_| |_|\\___|\\___|\\__|_|\\___/|_| |_|
                        """
                );


        System.out.println("[1] LOGIN" + "\n" + "[2] REGISTRA NUOVO CLIENTE" + "\n" + "[3] REGISTRA NUOVO MAGAZZINIERE" + "\n");
        Utente currentUser = menuIniziale(admin, utenti, PATH_UTENTI, PATH_ADMIN);

        System.out.println("Login Effettuato con " + currentUser.getTipo()+ " " + currentUser.getNomeUtente());

        //GESTIONE MENU

        if (currentUser.getTipo().equals("cliente")) {
            menuCliente((Cliente)currentUser, magazzino, PATH_MAGAZZINO);
        } else if (currentUser.getTipo().equals("magazziniere")) {
            menuMagazziniere(magazzino, PATH_MAGAZZINO);
        } else if (currentUser.getTipo().equals("admin")){
            menuAdmin((Admin)currentUser,magazzino,utenti,PATH_ADMIN, PATH_UTENTI, PATH_MAGAZZINO);
        }


    }

    public static void menuCliente(Cliente cliente, Magazzino magazzino, String path) {
        int choice = 0;
        String id;

        while (true) {
            System.out.println("""         
                     _____         _     _____                         _       _____                             _   _            \s
                    |_   _|       | |   /  __ \\                       | |     /  __ \\                           | | (_)           \s
                      | | ___  ___| |__ | /  \\/ __ _ _ __  _ __   ___ | | ___ | /  \\/ ___  _ __  _ __   ___  ___| |_ _  ___  _ __ \s
                      | |/ _ \\/ __| '_ \\| |    / _` | '_ \\| '_ \\ / _ \\| |/ _ \\| |    / _ \\| '_ \\| '_ \\ / _ \\/ __| __| |/ _ \\| '_ \\\s
                      | |  __/ (__| | | | \\__/\\ (_| | | | | | | | (_) | | (_) | \\__/\\ (_) | | | | | | |  __/ (__| |_| | (_) | | | |
                      \\_/\\___|\\___|_| |_|\\____/\\__,_|_| |_|_| |_|\\___/|_|\\___/ \\____/\\___/|_| |_|_| |_|\\___|\\___|\\__|_|\\___/|_| |_|
                      
                    [1] AGGIUNGI PRODOTTO AL CARRELLO
                    [2] RUMUOVI PRODOTTO DAL CARRELLO
                    [3] MOSTRA PRODOTTI PRESENTI IN CARRELLO
                    [4] FINALIZZA SPESA
                    [5] ESCI DAL PROGRAMMA

                    Inserire un numero corrispondente all'azione ->
                    """
            );

            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        if (magazzino.stampaMagazzino()) {
                            System.out.print("Che prodotto vuoi aggiungere al carrello? (inserire id prodotto da aggiungere) -> ");
                            id = scanner.next();
                            if (Objects.equals(magazzino.cercaProdottoPerId(id).getTipoProdotto(), "inesistente")) {
                                System.out.print("NESSUN PRODOTTO CORRISPONDENTE! PREMERE INVIO PER TORNARE INDIETRO!");
                                scanner.next();
                                break;
                            }
                            Prodotto prodottoDaAggiungere = magazzino.cercaProdottoPerId(id);
                            cliente.aggiungiAlCarrello(prodottoDaAggiungere);
                            magazzino.rimuoviProdotto(id);
                            break;
                        }
                        scanner.next();
                        break;

                    case 2:
                        if (cliente.isEmpty()) {
                            System.out.print("Carrello vuoto. PREMERE INVIO PER TORNARE INDIETRO! ");
                            scanner.next();
                            break;
                        }

                        cliente.mostraCarrello();
                        System.out.print("Che prodotto vuoi rimuovere dal carrello? (inserire id prodotto da aggiungere) -> ");
                        id = scanner.next();
                        if (cliente.cercaProdotto(id).getTipoProdotto() == null) {
                            System.out.print("NESSUN PRODOTTO CORRISPOINDENTE NEL CARRELLO. PREMERE INVIO PER CONTINUARE ");
                            scanner.next();
                            break;
                        }
                        Prodotto prodotto = cliente.cercaProdotto(id);
                        magazzino.aggiungiProdotto(prodotto);
                        cliente.rimuoviDalCarrello(prodotto);
                        break;
                    case 3:
                        if (cliente.isEmpty()) {
                            System.out.print("Nulla da mostrare nel carrello. PREMERE INVIO PER CONTINUARE: ");
                            scanner.next();
                            break;
                        }
                        System.out.println("**** PRODOTTI NEL CARRELLO ****");
                        cliente.mostraCarrello();
                        System.out.println("*******************************\n\n");
                        System.out.print("PREMERE INVIO PER CONTINUARE: ");
                        scanner.next();
                        break;
                    case 4:
                        if (cliente.isEmpty()) {
                            System.out.print("Il carrello è ancora vuoto. PREMERE INVIO PER CONTINUARE: ");
                            scanner.next();
                            break;
                        }

                        System.out.println("Il prezzo finale è: " + cliente.prezzoFinale() + ". PREMERE INVIO PER CONTINUARE: ");
                        cliente.finalizzaAcquisto();
                        salvaMagazzino(path, magazzino);
                        scanner.next();
                        break;
                    case 5:
                        salvaMagazzino(path, magazzino);
                        System.exit(0);
                }

            } catch (Exception e) {
                System.out.println("INSERIRE UN'OPZIONE VALIDA!");
                scanner.next();
            }
        }
    }
    public static void menuMagazziniere(Magazzino magazzino, String path) {
        while (true) {
            System.out.print("""
                     _____         _     _____                         _       _____                             _   _            \s
                    |_   _|       | |   /  __ \\                       | |     /  __ \\                           | | (_)           \s
                      | | ___  ___| |__ | /  \\/ __ _ _ __  _ __   ___ | | ___ | /  \\/ ___  _ __  _ __   ___  ___| |_ _  ___  _ __ \s
                      | |/ _ \\/ __| '_ \\| |    / _` | '_ \\| '_ \\ / _ \\| |/ _ \\| |    / _ \\| '_ \\| '_ \\ / _ \\/ __| __| |/ _ \\| '_ \\\s
                      | |  __/ (__| | | | \\__/\\ (_| | | | | | | | (_) | | (_) | \\__/\\ (_) | | | | | | |  __/ (__| |_| | (_) | | | |
                      \\_/\\___|\\___|_| |_|\\____/\\__,_|_| |_|_| |_|\\___/|_|\\___/ \\____/\\___/|_| |_|_| |_|\\___|\\___|\\__|_|\\___/|_| |_|
                      
                    [1] AGGIUNGI PRODOTTO AL MAGAZZINO
                    [2] RUMUOVI PRODOTTO DAL MAGAZZINO
                    [3] MOSTRA PRODOTTI PRESENTI IN MAGAZZINO
                    [4] CERCA PRODOTTI IN MAGAZZINO
                    [5] ESCI DAL MAGAZZINO

                    Inserire un numero corrispondente all'azione ->
                    """
            );


            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        String tipoProdotto;
                        String produttore;
                        String modello;
                        float dimensioneDisplay;
                        float dimensioneMemoria;
                        float prezzoAcquisto;
                        float prezzoVendita;
                        String descrizione;

                        System.out.print("Tipo di prodotto: "); //RENDERE TIPO PRODOTTO ENUM--------------------------------------
                        try {
                            tipoProdotto = scanner.next().trim();
                        } catch (Exception e) {
                            System.out.println("INPUT ERRATO!");
                            break;
                        }

                        System.out.print("Produttore: ");
                        produttore = scanner.next().trim();

                        System.out.print("Modello: ");
                        modello = scanner.next().trim();

                        System.out.print("Dimensione display: ");
                        try {
                            dimensioneDisplay = scanner.nextFloat();
                        } catch (Exception e) {
                            System.out.println("INPUT ERRATO!");
                            break;
                        }

                        System.out.print("Dimensione memoria: ");
                        try {
                            dimensioneMemoria = scanner.nextFloat();
                        } catch (InputMismatchException e) {
                            System.out.println("INPUT ERRATO!");
                            break;
                        }

                        System.out.print("Prezzo di acquisto: ");
                        try {
                            prezzoAcquisto = scanner.nextFloat();
                        } catch (InputMismatchException e) {
                            System.out.println("INPUT ERRATO!");
                            break;
                        }

                        System.out.print("Prezzo di vendita: ");
                        try {
                            prezzoVendita = scanner.nextFloat();
                        } catch (InputMismatchException e) {
                            System.out.println("INPUT ERRATO!");
                            break;
                        }

                        System.out.print("DESCRIZIONE: ");
                        descrizione = scanner.next().trim();

                        Prodotto prodotto = new Prodotto(tipoProdotto, produttore, modello, dimensioneDisplay, dimensioneMemoria, prezzoAcquisto, prezzoVendita, descrizione);
                        magazzino.aggiungiProdotto(prodotto);
                        salvaMagazzino(path, magazzino);
                        break;

                    case 2:
                        if (magazzino.stampaMagazzino()) {
                            System.out.print("Inserisci l'ID del prodotto da eliminare -> ");
                            try {
                                String idDaEliminare = scanner.next();
                                magazzino.rimuoviProdotto(idDaEliminare);
                                salvaMagazzino(path, magazzino);
                                System.out.print("PREMERE INVIO PER CONTINUARE ");
                                scanner.next();
                            } catch (InputMismatchException e) {
                                System.out.println("INPUT ERRATO!");
                            }
                            break;
                        }
                        break;



                    case 3:
                        magazzino.stampaMagazzino();
                        System.out.print("PREMERE INVIO PER CONTINUARE ");
                        scanner.next();
                        break;

                    case 4:
                        cercaProdottoInMagazzino(magazzino);
                        break;
                    case 5:
                        salvaMagazzino(path, magazzino);
                        System.exit(0);
                        break;

                    default:
                        System.out.println("INSERIRE UN'OPZIONE VALIDA!");
                }
            } catch (Exception e) {
                System.out.println("INSERIRE UN'OPZIONE VALIDA!");
                scanner.next();
            }
        }
    }
    public static void cercaProdottoInMagazzino(Magazzino magazzino) {
        System.out.print("""
                [1] CERCA PER ID
                [2] CERCA PER MODELLO
                [3] CERCA PER TIPO DI PRODOTTO
                [4] CERCA PER PRODUTTORE
                [5] CERCA PER PREZZO DI VENDITA
                [6] CERCA PER PREZZO DI ACQUISTO
                [7] CERCA PER RANGE DI PREZZO
                [8] TORNA INDIETRO
                """
        );
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Inserire ID -> ");
                    try {
                        Prodotto prodottoDaCercare = magazzino.cercaProdottoPerId(scanner.next());
                        if (Objects.equals(prodottoDaCercare.getTipoProdotto(), "inesistente")) {
                            System.out.println("PRODOTTO CORRISPONDENTE ALL'ID INESISTENTE. PREMERE INVIO PER CONTINUARE");
                            scanner.next();
                            break;
                        }
                        System.out.println(prodottoDaCercare);
                        System.out.print("PREMERE INVIO PER CONTINUARE!");
                        scanner.next();
                        break;
                    } catch (Exception e) {
                        System.out.println("INSERIRE UN ID VALIDO!");
                        scanner.next();
                        break;
                    }
                case 2:
                    System.out.print("Inserire modello del prodotto -> ");
                    magazzino.cercaProdottoPerModello(scanner.next());
                    System.out.print("PREMERE INVIO PER CONTINUARE");
                    scanner.next();
                    break;

                case 3:
                    System.out.print("Inserire tipo del prodotto -> ");
                    magazzino.cercaProdottoPerTipo(scanner.next());
                    System.out.print("PREMERE INVIO PER CONTINUARE");
                    scanner.next();
                    break;

                case 4:
                    System.out.print("Inserire produttore del prodotto -> ");
                    magazzino.cercaProdottoPerProduttore(scanner.next());
                    System.out.print("PREMERE INVIO PER CONTINUARE");
                    scanner.next();
                    break;

                case 5:
                    System.out.print("Inserire prezzo di vendita del prodotto -> ");

                    try {
                        float prezzo = scanner.nextFloat();
                        magazzino.cercaProdottoPerPrezzoVendita(prezzo);
                        System.out.print("PREMERE INVIO PER CONTINUARE");
                        scanner.next();
                        break;

                    } catch (Exception e) {
                        System.out.println("INPUT ERRATO");
                        break;
                    }


                case 6:
                    System.out.print("Inserire prezzo di acquisto del prodotto -> ");
                    try {
                        float prezzo = scanner.nextFloat();
                        magazzino.cercaProdottoPerPrezzoAcquito(prezzo);
                        System.out.print("PREMERE INVIO PER CONTINUARE");
                        scanner.next();
                        break;
                    } catch (Exception e) {
                        System.out.println("INPUT ERRATO");
                        break;
                    }

                case 7:
                    try {
                        System.out.print("Inserire limite inferiore: ");
                        float limiteInf = scanner.nextFloat();

                        System.out.print("Inserire limite superiore: ");
                        float limiteSup = scanner.nextFloat();

                        if (limiteSup < limiteInf) {
                            magazzino.cercaProdottoPerRangePrezzo(limiteSup, limiteInf);
                            System.out.print("PREMERE INVIO PER CONTINUARE");
                            scanner.next();
                            break;
                        }

                        magazzino.cercaProdottoPerRangePrezzo(limiteInf, limiteSup);
                        System.out.print("PREMERE INVIO PER CONTINUARE");
                        scanner.next();
                        break;

                    } catch (Exception e) {
                        System.out.println("INPUT ERRATO");
                        break;
                    }
                case 8:
                    break;
                default:
                    System.out.println("INSERIRE UN'OPZIONE VALIDA!");
            }
        } catch (Exception e) {
            System.out.println("INPUT ERRATO");
        }
    }
    public static void menuAdmin(Admin admin, Magazzino magazzino, UtentiRegistrati utenti, String PATH_ADMIN, String PATH_UTENTI, String PATH_MAGAZZINO) throws IOException {
        String choice;
        String id;

        while (true) {
            System.out.println("""         
                    *************** MENU ADMIN ***************
                    [1] VISUALIZZA LISTA UTENTI REGISTRATI
                    [2] ELIMINA UTENTE
                    [3] VISUALIZZA LISTA KEY MAGAZZINIERE
                    [4] CREA KEY MAGAZZINIERE
                    [5] ESCI DAL PROGRAMMA

                    Inserire un numero corrispondente all'azione ->
                    """
            );

            choice = scanner.next();
            switch (choice) {
                case "1":
                    System.out.println("CLIENTI: ");
                    if (utenti.getClientiRegistrati().isEmpty()){
                        System.out.println("Nessun Cliente registrato");
                    }else{
                        for (Cliente clienti : utenti.getClientiRegistrati()){
                            System.out.println(clienti);
                        }
                    }
                    System.out.println("MAGAZZINIERI: ");
                    if (utenti.getMagazzinieriRegistrati().isEmpty()){
                        System.out.println("Nessun Magazziniere registrato");
                    }else {
                        for (Magazziniere magazziniere : utenti.getMagazzinieriRegistrati()) {
                            System.out.println(magazziniere);
                        }
                    }
                    break;

                case "2":
                    System.out.println("Inserisci Username dell'utente da eliminare: ");
                    String username = scanner.next();
                    for (Cliente cliente : utenti.getClientiRegistrati()){
                        if (cliente.getNomeUtente().equals(username)){
                            utenti.eliminaCliente(cliente);
                            System.out.println("Cliente "+username+" eliminato con successo!");
                            salvaUtenti(PATH_UTENTI, utenti);
                            break;
                        }
                    }
                    for (Magazziniere magazziniere : utenti.getMagazzinieriRegistrati()){
                        if (magazziniere.getNomeUtente().equals(username)){
                            utenti.eliminaMagazziniere(magazziniere);
                            System.out.println("Magazziniere "+username+" eliminato con successo!");
                            salvaUtenti(PATH_UTENTI, utenti);
                            break;
                        }
                    }
                    System.out.println("Nessun Utente trovato con l'username inserito!");
                    break;

                case "3":
                    admin.visualizzaKeys();
                    break;

                case "4":
                    System.out.println("Inserisci nuova key magazziniere: ");
                    String key = scanner.next();
                    admin.addKey(key);
                    salvaAdmin(PATH_ADMIN, admin);
                    break;
                case "5":
                    System. exit(0);
                    break;
                default:
                    System.out.println("INSERIRE UN'OPZIONE VALIDA!");
                    break;

            }
        }
    }



    public static Utente menuIniziale(Admin admin,UtentiRegistrati utenti, String PATH_UTENTI,String PATH_ADMIN) throws IOException {
        String menuRegistra;
        menuRegistra = scanner.next();

        switch(menuRegistra){
            case "1":
                System.out.print("Inserisci nome utente -> ");
                String nomeUtente = scanner.next().trim();
                System.out.print("Inserisci la password -> ");
                String password = scanner.next();
                for (Cliente cliente : utenti.getClientiRegistrati()) {
                    if (cliente.getNomeUtente().equals(nomeUtente) && cliente.getPassword().equals(password)) {
                        return cliente;
                    }
                }
                for (Magazziniere magazziniere : utenti.getMagazzinieriRegistrati()) {
                    if (magazziniere.getNomeUtente().equals(nomeUtente) && magazziniere.getPassword().equals(password)) {
                        return magazziniere;
                    }
                }
                if (admin.getNomeUtente().equals(nomeUtente) && admin.getPassword().equals(password)){
                    return admin;
                }
                System.out.println("Username or Password invalid!");
                System. exit(0);
                break;
            case "2":
                return menuRegistrazioneCliente(admin, utenti, PATH_UTENTI);
            case "3":
                return menuRegistrazioneMagazziniere(admin, utenti, PATH_UTENTI, PATH_ADMIN);
            case "Nicola":
                secret();
                System. exit(0);
                break;

            default :
                System.out.println("Input errato!");
                System. exit(0);
                break;
        }
    return null;
    }
    public static Utente menuRegistrazioneCliente(Admin admin, UtentiRegistrati utenti, String path) throws IOException {
        System.out.print("INSERISCI NOME UTENTE -> ");
        String newUsername = scanner.next().trim();
        for (Cliente cliente : utenti.getClientiRegistrati()) {
            if (cliente.getNomeUtente().equals(newUsername)) {
                System.out.println("Username esistente");
                System. exit(0);
                return null;
            }
        }
        for (Magazziniere magazziniere : utenti.getMagazzinieriRegistrati()) {
            if (magazziniere.getNomeUtente().equals(newUsername)) {
                System.out.println("Username esistente");
                System. exit(0);
                return null;
            }
        }
        if (admin.getNomeUtente().equals(newUsername)) {
            System.out.println("Username esistente");
            System. exit(0);
            return null;
        }
        System.out.print("INSERISCI PASSWORD -> ");
        String password = scanner.next();
        Cliente cliente = new Cliente(newUsername, password);
        utenti.aggiungiCliente(cliente);
        salvaUtenti(path, utenti);
        System.out.println("Nuovo cliente registrato con successo!");
        return cliente;
    }
    public static Utente menuRegistrazioneMagazziniere(Admin admin, UtentiRegistrati utenti, String path, String pathAdmin) throws IOException {
        System.out.print("INSERISCI NOME UTENTE -> ");
        String newUsername = scanner.next().trim();
        for (Cliente cliente : utenti.getClientiRegistrati()) {
            if (cliente.getNomeUtente().equals(newUsername)) {
                System.out.println("Username esistente");
                System. exit(0);
                return null;
            }
        }
        for (Magazziniere magazziniere : utenti.getMagazzinieriRegistrati()) {
            if (magazziniere.getNomeUtente().equals(newUsername)) {
                System.out.println("Username esistente");
                System. exit(0);
                return null;
            }
        }
        if (admin.getNomeUtente().equals(newUsername)) {
            System.out.println("Username esistente");
            System. exit(0);
            return null;
        }

        System.out.print("INSERISCI PASSWORD -> ");
        String password = scanner.next();

        System.out.println("Inserisci chiave Magazziniere: ");
        String key = scanner.next();
        for(String keyCheck : admin.getKeys()){
            if (keyCheck.equals(key)){
                admin.removeKey(key);
                salvaAdmin(pathAdmin, admin);
                Magazziniere magazziniere = new Magazziniere(newUsername, password);
                utenti.aggiungiMagazziniere(magazziniere);
                salvaUtenti(path, utenti);
                System.out.println("Nuovo Magazziniere registrato con successo!");
                return magazziniere;
            }
        }
        System.out.println("Chiave Magazziniere Errata!");
        System. exit(0);
        return null;
    }
    public static Magazzino loadMagazzino(String path, Magazzino magazzino) {
        Gson gson = new Gson();

        try {
            File file = new File(path);
            if (file.exists()) {
                System.out.println("Magazzino presente. Lettura in corso...");
                FileReader fileReader = new FileReader(file);
                magazzino = gson.fromJson(fileReader, magazzino.getClass());
                fileReader.close();
            } else {
                System.out.println("Magazzino non presente. Creazione magazzino in corso...");
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(gson.toJson(magazzino));
                fileWriter.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return magazzino;
    }
    public static void salvaMagazzino(String path, Magazzino magazzino) throws IOException {
        File file = new File(path);
        Gson gson = new Gson();

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(gson.toJson(magazzino));
        fileWriter.close();
    }
    public static UtentiRegistrati loadUser(String path) {
        Gson gson = new Gson();
        UtentiRegistrati listaClienti = new UtentiRegistrati();

        try {
            File file = new File(path);

            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                listaClienti = gson.fromJson(fileReader, listaClienti.getClass());
                fileReader.close();
            } else {
                FileWriter fileWriter = new FileWriter(file);
                file.createNewFile();
                fileWriter.write(gson.toJson(listaClienti));
                fileWriter.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaClienti;
    }
    public static void salvaUtenti(String path, UtentiRegistrati newUser) throws IOException {
        File file = new File(path);
        Gson gson = new Gson();

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(gson.toJson(newUser));
        fileWriter.close();
    }
    public static Admin loadAdmin(String path){
        Gson gson = new Gson();
        Admin admin = new Admin();

        try {
            File file = new File(path);

            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                admin = gson.fromJson(fileReader, admin.getClass());
                fileReader.close();
            } else {
                admin = createNewAdmin(path);
                FileWriter fileWriter = new FileWriter(file);
                file.createNewFile();
                fileWriter.write(gson.toJson(admin));
                fileWriter.close();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return admin;
    }
    public static Admin createNewAdmin(String path) throws IOException {
        System.out.println("Inserisci Username Admin: ");
        String username = scanner.next();
        System.out.println("Inserisci Username Password: ");
        String password = scanner.next();
        Admin admin = new Admin(username, password);
        return admin;
    }
    public static void salvaAdmin(String path, Admin admin) throws IOException {
        File file = new File(path);
        Gson gson = new Gson();

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(gson.toJson(admin));
        fileWriter.close();
    }

    public static void secret(){
        System.out.println("Nicola ti amiamo <3");
    }
}
