package it.polito.tdp.corsi.model;

import java.util.List;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {
	
	// Il controller non chiama direttamente il DAO, ma chiama prima il model che poi chiama il DAO
	private CorsoDAO corsoDao;
	
	public Model() {
		this.corsoDao = new CorsoDAO();
	}
	
	public List<Corso> getCorsiByPeriodo(int periodo) {
		return this.corsoDao.getCorsiByPeriodo(periodo); // CHiede il risultato e lo ritorna
	}
	
}
