package it.unisa.progetto;

import it.unisa.progetto.Database.Query;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
            throws SQLException {

        while(true){

            System.out.println("1- inserimento\n" +
                    "2- modifica\n" +
                    "3- cancellazione\n" +
                    "4- visualizzazione\n");
            System.out.println("Seleziona l'operazione da svolgere\n");

            int operazione;
            Scanner in = new Scanner(System.in);

            operazione = in.nextInt();

            switch (operazione){

                case 1:
                    creaInserimento();
                    break;

                case 2:
                    creaModifica();
                    break;

                case 3:
                    cancellazione();
                    break;

                case 4:
                    eseguiUnaDelleView();
                    break;

            }

        }

    }

    private static void creaInserimento(){

        System.out.println("Inserisci " +
                                "ciu, età, specie, genere, matricola Dipendente, matricola Volontario");

        Scanner in = new Scanner(System.in);
        String ciu = in.next();
        int eta = in.nextInt();

        String specie = in.next();
        String genere = in.next();
        String matricolaD = in.next();
        String matricola = in.next();

        Query.insertGatto(ciu, eta, specie, genere, matricolaD, matricola);

    }

    public static void creaModifica(){

        Query.stampaGatti();

        System.out.println("Seleziona il gatto che è stato adottato:");

        Scanner in = new Scanner(System.in);
        String ciu = in.next();
        String data = in.next();

        Query.modificaGatto(data, ciu);

    }

    private static void cancellazione(){

        Query.stampaGatti();
        System.out.println("Inserisci ciu del gatto che vuoi eliminare");

        Scanner in = new Scanner(System.in);

        Query.cancellaGatto(in.next());

    }

    private static void eseguiUnaDelleView()
            throws SQLException {

        System.out.println("Cosa vuoi visualizzare?\n" +
                "1- Gatto a partire da una razza\n" +
                "2- Gatto che hanno fatto visita in una data\n" +
                "3- Totale Buoni\n" +
                "4- Conteggio Visite dei gatti\n" +
                "5- Stipedio per orario dei dipendenti\n" +
                "6- Stipendio per orario e da una certa quota oraria\n" +
                "7- Volontari senza adozioni gestite\n" +
                "8- Gatti che non sono stati adottati\n");

        System.out.println("Seleziona l'operazione da svolgere\n");

        int operazione;
        Scanner in = new Scanner(System.in);

        operazione = in.nextInt();

        switch(operazione){

            case 1:
                System.out.println("Inserisci razza:");
                Query.printGatti(in.next());
                break;

            case 2:
                System.out.println("Inserisci data:");
                Query.printGattiVisita(in.next());
                break;

            case 3:
                Query.printSommaBuoni();
                break;

            case 4:
                Query.printGattoVisite();
                break;

            case 5:
                System.out.println("Inserisci orario:");
                Query.printSommaStipendiPerOre(in.nextInt());
                break;

            case 6:
                System.out.println("Inserisci orario e data:");
                Query.printStipendiPerOreEQuotaOraria(in.nextInt(), in.nextInt());
                break;

            case 7:
                Query.printVolontariCheNonHannoAdozioni();
                break;

            case 8:
                Query.printGattiCheSonoStatiVisitati();
                break;

        }

    }

}