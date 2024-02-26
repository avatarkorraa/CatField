<p align="center"><img src="https://i.postimg.cc/fTL0rN0N/OIG4.jpg" alt="Quixel_Texel_Logo" height="400"></p>

# CatField

CatField è un progetto Java che implementa un sistema di gestione per un gattile utilizzando il linguaggio di programmazione Java, la tecnologia JDBC per l'accesso al database MySQL.

## Descrizione del Progetto

CatField è un'applicazione progettata per aiutare a gestire un gattile, fornendo un'interfaccia su terminale per registrare e gestire i gatti ospitati e altre informazioni rilevanti.

## Tecnologie Utilizzate

Il progetto CatField fa uso delle seguenti tecnologie:

- **Java:** Linguaggio di programmazione principale per lo sviluppo dell'applicazione.
- **JDBC (Java Database Connectivity):** Utilizzato per stabilire la connessione con il database MySQL e per eseguire le query.
- **MySQL:** Database relazionale utilizzato per memorizzare e gestire i dati relativi ai gatti, ai loro proprietari e altre informazioni pertinenti.

## Struttura del Progetto

Il progetto CatField è strutturato nel seguente modo:

- `src/`: Contiene il codice sorgente Java dell'applicazione.
  - `Main.java`: File principale contenente il punto di ingresso dell'applicazione.
  - Altri file Java contenenti le classi per gestire le operazioni del gattile, della connessione al database, etc.
- `sql/`: Contiene lo script SQL per la creazione del database e la definizione delle tabelle.

## Configurazione

Per eseguire correttamente il progetto CatField, seguire i passaggi seguenti:

1. Assicurarsi di avere installato Java Development Kit (JDK) sul proprio sistema.
2. Installare e configurare un'istanza MySQL sul sistema.
3. Creare un database MySQL chiamato `catfield` utilizzando lo script fornito in `sql/`.
4. Modificare le credenziali di accesso al database nel file di configurazione JDBC (`Connector.java`) con le proprie informazioni di accesso al database MySQL.

(ESEMPIO STRINGA JDBC -> `jdbc:mysql://localhost:3306/catfield?CREDENZIALI`)

## Utilizzo

Dopo aver configurato correttamente l'applicazione, è possibile eseguire `Main.java` per avviare l'applicazione. Seguire le istruzioni fornite nell'interfaccia utente per registrare nuovi gatti, aggiornare le informazioni esistenti e gestire l'archivio del gattile.

## Documentazione

Potete trovare la JavaDoc completa a questo link: <a href="https://avatarkorraa.github.io/CatField/">JAVADOC</a>.
