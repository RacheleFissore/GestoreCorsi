package it.polito.tdp.corsi.model;

import java.util.*;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	
	// Il controller non chiama direttamente il DAO, ma chiama prima il model che poi chiama il DAO
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model() {
		this.corsoDao = new CorsoDAO();
		this.studenteDao = new StudenteDAO();
	}
	
	public List<Corso> getCorsiByPeriodo(int periodo) {
		return this.corsoDao.getCorsiByPeriodo(periodo); // Chiede il risultato e lo ritorna
	}
	
	public Map<Corso, Integer> getIscritti(int periodo) {
		return this.corsoDao.getIscritti(periodo); // Chiede il risultato e lo ritorna
	}
	
	public List<Studente> getStudentiByCorso(String codins) {
		return this.studenteDao.getStudentiByCorso(codins);
	}
	
	public List<Divisione> getDivisioneStudenti(String codins) {
		return this.studenteDao.getDivisioneStudenti(codins);
	}
}
