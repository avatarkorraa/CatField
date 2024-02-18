package it.unisa.progetto.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * La classe Query fornisce metodi per eseguire diverse query sul database.
 */

public class Query {

    /**
     * Stampa i codici dei gatti di una determinata specie.
     *
     * @param specie la specie dei gatti da cercare.
     */

    public static void printGatti(String specie) {

        try {
            Statement stmn = Connector.getConnection().createStatement();
            String sql = "select codiceidentificativounico from gatto " +
                    " where genere = \"maschio\" and specie = '" + specie + "' or genere = \"femmina\" \n" +
                    " and specie = '" + specie + "'";
            ResultSet rs = stmn.executeQuery(sql);

            while (rs.next()) {

                System.out.println("Codice gatto: " + rs.getString(1));

            }

            rs.close();
            stmn.close();
        } catch (SQLException e){

            e.printStackTrace();

        }

    }

    /**
     * Stampa i dettagli dei gatti visitati in una determinata data.
     *
     * @param data la data delle visite da cercare.
     * @throws SQLException se si verifica un errore SQL.
     */

    public static void printGattiVisita(String data)
        throws SQLException{

        try{
        Statement stmn = Connector.getConnection().createStatement();
        String sql = "select gatto.codiceidentificativounico, id, veterinario.matricolaMedica" +
                " from gatto, visita, veterinario where gatto.codiceidentificativounico = visita.codiceidentificativounico" +
                " and visita.matricolaMedica = veterinario.matricolaMedica and dataVisita ='" + data + "'";
        ResultSet rs = stmn.executeQuery(sql);

        while(rs.next()){

            System.out.println("Codice gatto: " + rs.getString(1));
            System.out.println("Id visita: " + rs.getInt(2));
            System.out.println("Matricola Medica: " + rs.getString(3));

        }

        rs.close();
        stmn.close();

        } catch (SQLException e){

            e.printStackTrace();

        }

    }

    /**
     * Stampa la somma di tutte le quote dei buoni.
     *
     * @throws SQLException se si verifica un errore SQL.
     */

    public static void printSommaBuoni()
            throws SQLException {

        try{
        Statement stmn = Connector.getConnection().createStatement();
        String sql = "select SUM(buono.quota) from buono";
        ResultSet rs = stmn.executeQuery(sql);

        if(rs.next()){

            System.out.println("Somma di tutti i buoni: " + rs.getInt(1));

        }

        rs.close();
        stmn.close();

        } catch (SQLException e){

            e.printStackTrace();

        }

    }

    /**
     * Stampa il numero di visite per ogni gatto nel database.
     *
     * @throws SQLException se si verifica un errore SQL.
     */

    public static void printGattoVisite()
            throws SQLException {

        try{

        Statement stmn = Connector.getConnection().createStatement();
        String sql = "select gatto.codiceidentificativounico, COUNT(visita.id) from gatto, visita" +
                " where gatto.codiceidentificativounico = visita.codiceidentificativounico" +
                " group by codiceidentificativounico";
        ResultSet rs = stmn.executeQuery(sql);

        while(rs.next()){

            System.out.println("Codice gatto: " + rs.getString(1));
            System.out.println("Numero visite: " + rs.getInt(2));

        }

        rs.close();
        stmn.close();

        } catch (SQLException e){

            e.printStackTrace();

        }

    }

    /**
     * Stampa il numero di dipendenti con meno di 40 ore e la somma degli stipendi per quelle ore.
     *
     * @param ora il limite massimo di ore lavorative.
     * @throws SQLException se si verifica un errore SQL.
     */

    public static void printSommaStipendiPerOre(int ora)
            throws SQLException {

        try{

        Statement stmn = Connector.getConnection().createStatement();
        String sql = "select COUNT(dipendente.matricola) as numero_dipendenti, dipendente.ore, SUM(dipendente.stipendio) " +
                " from dipendente" +
                " where ore < 40 group by ore";
        ResultSet rs = stmn.executeQuery(sql);

        while(rs.next()){

            System.out.println("Numero dipendenti: " + rs.getInt(1));
            System.out.println("ore: " + rs.getInt(2));
            System.out.println("Stipendi totale: " + rs.getInt(3));

        }

        rs.close();
        stmn.close();

        } catch (SQLException e){

            e.printStackTrace();

        }

    }

    /**
     * Stampa il numero di dipendenti, il numero di ore e la somma degli stipendi
     * per ore lavorative superiori a un certo limite e con una quota oraria massima superiore a un certo valore.
     *
     * @param ora   il limite minimo di ore lavorative.
     * @param quota il limite minimo di quota oraria.
     * @throws SQLException se si verifica un errore SQL.
     */

    public static void printStipendiPerOreEQuotaOraria(int ora, int quota)
            throws SQLException {

        try{

        Statement stmn = Connector.getConnection().createStatement();
        String sql = "drop view if exists dipendenti;" +
                "create view dipendenti as" +
                "select COUNT(dipendente.matricola) as numero_dipendenti, dipendente.ore, SUM(dipendente.stipendio) as somma from dipendente" +
                "group by dipendente.ore having dipendente.ore >" + ora + " and MAX(dipendente.quotaOraria) >" + quota +";" +
                "select *" +
                "from dipendenti" +
                "where somma=(select max(somma) from dipendenti);";
        ResultSet rs = stmn.executeQuery(sql);

        while(rs.next()){

            System.out.println("Numero dipendenti: " + rs.getInt(1));
            System.out.println("Numero ore: " + rs.getInt(2));
            System.out.println("Somma Stipendio: " + rs.getInt(3));

        }

        rs.close();
        stmn.close();

        } catch (SQLException e){

            e.printStackTrace();

        }

    }

    /**
     * Stampa le matricole dei volontari che non hanno adozioni associate.
     *
     * @throws SQLException se si verifica un errore SQL.
     */

    public static void printVolontariCheNonHannoAdozioni()
            throws SQLException {

        try{

        Statement stmn = Connector.getConnection().createStatement();
        String sql = "select volontario.matricola from volontario where volontario.matricola" +
                " not in (select gatto.matricolaVolontario from gatto" +
                " where gatto.dataAdozione is not null)";
        ResultSet rs = stmn.executeQuery(sql);

        while(rs.next()){

            System.out.println("Matricola volontario: " + rs.getString(1));

        }

        rs.close();
        stmn.close();

        } catch (SQLException e){

            e.printStackTrace();

        }

    }

    /**
     * Stampa i codici dei gatti che sono stati visitati da almeno un veterinario.
     *
     * @throws SQLException se si verifica un errore SQL.
     */

    public static void printGattiCheSonoStatiVisitati()
            throws SQLException {

        try{

        Statement stmn = Connector.getConnection().createStatement();
        String sql = "select gatto.codiceidentificativounico from gatto " +
                " where not exists ( select * from veterinario where not exists(" +
                " select * from visita where visita.codiceidentificativounico =  gatto.codiceidentificativounico))";
        ResultSet rs = stmn.executeQuery(sql);

        while(rs.next()){

            System.out.println("Gatto: " + rs.getString(1));

        }

        rs.close();
        stmn.close();

        } catch (SQLException e){

            e.printStackTrace();

        }

    }

    /**
     * Inserire un nuovo gatto nel database.
     *
     * @param ciu           il codice identificativo unico del gatto.
     * @param eta           l'età del gatto.
     * @param specie        la specie del gatto.
     * @param genere        il genere del gatto.
     * @param matricolaDip  la matricola del dipendente associato al gatto.
     * @param matricolaVol  la matricola del volontario associato al gatto.
     */

    public static void insertGatto(String ciu, int eta, String specie,
                                    String genere, String matricolaDip,String matricolaVol){

        try {
            Statement stmn = Connector.getConnection().createStatement();
            String sql = "INSERT INTO Gatto (codiceidentificativounico, età, specie, genere, matricolaDipendente, matricolaVolontario)\n" +
                    "VALUES ('" + ciu + "'," + eta + ",'" + specie + "','" + genere + "','" + matricolaDip +"','" +
                    matricolaVol + "')";
            int result = stmn.executeUpdate(sql);

            stmn.close();
        } catch (SQLException e){

            e.printStackTrace();

        }

    }

    /**
     * Stampa i codici dei gatti nel database.
     */

    public static void stampaGatti(){

        try {
            Statement stmn = Connector.getConnection().createStatement();
            String sql = "select gatto.codiceidentificativounico from gatto";
            ResultSet rs = stmn.executeQuery(sql);

            while (rs.next()) {

                System.out.println("Codice gatto: " + rs.getString(1));

            }

            rs.close();
            stmn.close();

        } catch (SQLException e){

            e.printStackTrace();

        }

    }

    /**
     * Modifica la data di adozione di un gatto nel database.
     *
     * @param dataAdozione la nuova data di adozione.
     * @param ciu          il codice identificativo unico del gatto da modificare.
     */

    public static void modificaGatto(String dataAdozione, String ciu){

        try {

            Statement stmn = Connector.getConnection().createStatement();
            String sql = "update gatto set dataAdozione ='" + dataAdozione + "'" + " where codiceidentificativounico = '" +
                    ciu + "'";

            int result = stmn.executeUpdate(sql);

            stmn.close();
        } catch (SQLException e){

            e.printStackTrace();

        }

    }

    /**
     * Cancella un gatto dal database.
     *
     * @param ciu il codice identificativo unico del gatto da eliminare.
     */

    public static void cancellaGatto(String ciu){

        try {
            Statement stmn = Connector.getConnection().createStatement();
            String sql = "delete from gatto where gatto.codiceidentificativounico ='" + ciu +"'";

            int result = stmn.executeUpdate(sql);

            stmn.close();

        } catch (SQLException e){

            e.printStackTrace();

        }

    }

}