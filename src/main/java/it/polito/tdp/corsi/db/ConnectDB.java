package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	// Classe usata per recuperare la connessione al database e anche per fare le query
	
	public static Connection getConnection() {
		// Metodo che ritorna una connessione al database
		
		try {
			String url = "jdbc:mysql://localhost/iscritticorsi?user=root&password=root";
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.err.println("Errore di connessione!");
			e.printStackTrace();
			return null;
		}
		
	}
	
	// Pattern DAO mettere tutto il codice per accedere al database in un posto separato e per ogni tabella di interesse creiamo un DAO separato
	// Nel nostro db abbiamo due tabelle di interesse: studenti e corsi e quindi ho bisogno di due DAO diversi:
	// 1. StudenteDAO: fornisce tutti i metodi per ottenere e modificare le informazioni degli studenti
	// 2. CorsoDAO: fornisce tutti i metodi per ottenere e modificare le informazioni dei corsi
}
