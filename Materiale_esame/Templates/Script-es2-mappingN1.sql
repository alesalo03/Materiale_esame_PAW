-- Questo script elimina le tabelle se esistono, le ricrea e inserisce dati di esempio,
-- utilizzando GENERATED ALWAYS AS IDENTITY per le colonne ID (sintassi comune in DB2/PostgreSQL/SQL Server).
-- Assicurati che lo schema 'A1071529' esista nel tuo database prima di eseguire questo script.

-- -----------------------------------------------------
-- ELIMINAZIONE DELLE TABELLE SE ESISTONO (per un avvio pulito)
-- L'ordine è importante: prima le tabelle con chiavi esterne, poi quelle referenziate.
-- Nota: Alcuni DBMS (come DB2) potrebbero non supportare DROP TABLE IF EXISTS.
-- Potrebbe essere necessario eliminare manualmente le tabelle o gestire l'errore se esistono già.
-- Se il tuo DBMS non supporta DROP TABLE IF EXISTS, rimuovi queste due righe
-- e assicurati che le tabelle non esistano prima di eseguire lo script.
-- ----------------------------------------------------

CREATE TABLE A1071529.Stadio (
    Codice INT NOT NULL,
    Nome VARCHAR(100) NOT NULL UNIQUE,
    Citta VARCHAR(100) NOT NULL,
    PRIMARY KEY (CODICE)
);

CREATE TABLE A1071529.Partita (
    CodicePartita INT NOT NULL,
    Categoria VARCHAR(50) NOT NULL,
    Girone VARCHAR(10) NOT NULL,
    NomeSquadraCasa VARCHAR(50) NOT NULL,
    NomeSquadraOspite VARCHAR(50) NOT NULL,
    CodiceStadio INT NOT NULL,
    Data VARCHAR(20) NOT NULL,
    PRIMARY KEY (CodicePartita),
    FOREIGN KEY (CodiceStadio) REFERENCES A1071529.Stadio(Codice)
);


/*INSERT INTO A1071529.Stadio (Nome, Citta) VALUES
('Stadio1', 'Roma'),
('Stadio2', 'Napoli'),
('Stadio3', 'Milano'),
('Stadio4', 'Firenze'),
('Stadio5', 'Genova');


INSERT INTO A1071529.Partita (Categoria, Girone, NomeSquadraCasa, NomeSquadraOspite, CodiceStadio, Data) VALUES
('A', 'D', 'Squadra1', 'Squadra2', 1, '12/2/2022'),
('B', 'D', 'Squadra3', 'Squadra21', 1, '12/2/2022'),
('B', 'A', 'Squadra4', 'Squadra22', 2, '12/2/2022'),
('A', 'E', 'Squadra5', 'Squadra23', 2, '12/2/2022'),
('C', 'A', 'Squadra6', 'Squadra24', 2, '12/2/2022'),
('A', 'A', 'Squadra7', 'Squadra25', 3, '12/2/2022'),
('C', 'B', 'Squadra8', 'Squadra26', 4, '12/2/2022'),
('A', 'A', 'Squadra9', 'Squadra27', 5, '12/2/2022');*/
