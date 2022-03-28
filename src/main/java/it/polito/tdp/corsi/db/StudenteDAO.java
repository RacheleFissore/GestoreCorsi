package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {
	public List<Studente> getStudentiByCorso(String codins) {
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola "
				+ "AND i.codins = ?";
		List<Studente> result = new ArrayList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins); // Come parametro gli passo il codice del corso che è stato passato in input dall'utente
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				result.add(new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS")));
			}
			
			st.close();
			rs.close();
			conn.close();
			return result;
		} catch(SQLException e) {
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Divisione> getDivisioneStudenti(String codins) {
		// Per ogni corso di studio (CDS) devo stampare il numero di studenti iscritti al corso passato come parametro
		String sql = "SELECT s.CDS, COUNT(*) AS n "
				+ "FROM studente s , iscrizione i "
				+ "WHERE s.matricola = i.matricola "
				+ "AND i.codins = ? "
				+ "AND s.CDS <> '' " // Nel db c'era una riga vuota e allora non la prendo
				+ "GROUP BY s.CDS";
		
		List<Divisione> result = new ArrayList<Divisione>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins); 
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				result.add(new Divisione(rs.getString("CDS"), rs.getInt("n")));
			}
			
			st.close();
			rs.close();
			conn.close();
			return result;
		} catch(SQLException e) {
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
		
	}
}
