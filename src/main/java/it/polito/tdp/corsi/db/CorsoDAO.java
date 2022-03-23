package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {
	public List<Corso> getCorsiByPeriodo(int periodo) {
		// Restituisce i corsi dato il periodo didattico --> Faccio la query
		List<Corso> result = new ArrayList<Corso>();
		String sql = "SELECT * "
				+ "FROM corso "
				+ "WHERE pd = ?"; // Stringa che contiene la query -> se la query è su più righe devo mettere uno spazio alla fine prima dell' " finale
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql); // La connessione ci impacchetta un prepared statement con la query nella stringa
			st.setInt(1, periodo);  // Parametro 1 della query che corrisponde al primo ?
			ResultSet rs = st.executeQuery(); // Eseguo la query e salvo il risultato della query nel result set
			
			// Da questo set di risultato devo estrarre un pezzo alla volta
			while (rs.next()) { // All'inizio il result set punta prima della prima riga, facendo rs.next() il result set si posiziona alla prima riga
								// se siamo all'ultima riga rs.next() ritorna false e si esce dal while
				
				// Tra apici DEVO mettere il NOME DELLA COLONNA DEL DATABASE
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd")); 
				result.add(c);	
			}
			
			st.close();
			rs.close();
			conn.close(); // Devo sempre chiudere la connessione
			return result;
			
		} catch(SQLException e) {
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
	}
	
	// COUNT(*) -> conto le righe --> siccome ho una funzione di aggregazione devo usare una GROUP BY in cui sono contenuti tutti gli attributi
	// messi prima della COUNT
	
	/*
	SELECT c.codins, c.crediti, c.nome, c.pd, COUNT(*) AS n
	FROM corso c, iscrizione i
	WHERE c.pd = 1
	AND c.codins = i.codins
	GROUP BY c.codins, c.crediti, c.nome, c.pd
	*/
}
