/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.corsi;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model; // Mi permette di accedere al modello
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPeriodo"
    private TextField txtPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorso"
    private TextField txtCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorsiPerPeriodo"
    private Button btnCorsiPerPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="btnNumeroStudenti"
    private Button btnNumeroStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnStudenti"
    private Button btnStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnDivisioneStudenti"
    private Button btnDivisioneStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void corsiPerPeriodo(ActionEvent event) {
    	txtRisultato.clear();
    	
    	String periodo = txtPeriodo.getText();
    	int periodoNumerico;
    	
    	// Controllo che l'input sia corretto, ossia che sia stato inserito un numero
    	try {
    		periodoNumerico = Integer.parseInt(periodo);
    	} catch(NumberFormatException e) {
    		txtRisultato.setText("Inserisci un periodo numerico");
    		return; 
    	}
    	
    	if(periodoNumerico < 1 || periodoNumerico > 2) {
    		txtRisultato.setText("Inserisci 1 o 2 come periodo numerico");
    		return;
    	}
    	
    	List<Corso> corsi = this.model.getCorsiByPeriodo(periodoNumerico); // Dal modello recupero il risultato della query andando a richiamare il
    																	   // metodo per ottenere i corsi relativi ad un periodo didattico che viene
    																	   // passato per parametro
    	for(Corso c : corsi) {
    		txtRisultato.appendText(c + "\n");
    	}
    }

    @FXML
    void numeroStudenti(ActionEvent event) { // Metodo associato alla query di count del numero di studenti per corso
    	String periodo = txtPeriodo.getText();
    	int periodoNumerico;
    	try {
    		periodoNumerico = Integer.parseInt(periodo);
    	} catch(NumberFormatException e) {
    		txtRisultato.setText("Inserisci un periodo numerico");
    		return; // L'input è sbagliato perchè non contiene un numero
    	}
    	
    	if(periodoNumerico < 1 || periodoNumerico > 2) {
    		txtRisultato.setText("Inserisci 1 o 2 come periodo numerico");
    		return;
    	}
    	
    	Map<Corso, Integer> iscritti = this.model.getIscritti(periodoNumerico);
    	
    	for(Corso c : iscritti.keySet()) { // iscritti.keySet() -> restituisce l'elenco delle chiavi della mappa
    		txtRisultato.appendText(c + " " + iscritti.get(c) + "\n"); // iscritti.get(c) mi restituisce il valore della count associato a quel corso
    	}
    }

    @FXML
    void stampaDivisione(ActionEvent event) {
    	// Risultato query: Dato il codice di un corso, stampare la divisione degli studenti iscritti tra i vari corsi di studio (CDS)
    	txtRisultato.clear();
    	String codins = txtCorso.getText();
    	if(codins == null || codins == " ") {
    		txtRisultato.appendText("Inserisci un codice di un corso");
    		return;
    	}
    	
    	//TODO controllo che il corso esista
    	List<Divisione> risultato = this.model.getDivisioneStudenti(codins);
    	Collections.sort(risultato); // Dobbiamo dirgli in che modo fare l'ordinamento
    	// Il comparator ci permette di ordinare quando ci sono più criteri di ordinamento multipli
    	// Se le classi usano un unico metodo di comparazione e viene fatto direttamwnte dentro la classe Divisione in questo caso
    	for(Divisione d : risultato) {
    		txtRisultato.appendText(d.getCDS() + "\t" + d.getN() + "\n");
    	}
    }

    @FXML
    void stampaStudenti(ActionEvent event) {
    	// Risultato query: Elencare tutti gli studenti di un determinato corso. L'utente inserisce il codice di un corso, e il programma stampa tutti 
    	// gli studenti iscritti.
    	
    	// Ottenimento dell'input e suo controllo
    	txtRisultato.clear();
    	String codins = txtCorso.getText();
    	if(codins == null || codins == " ") {
    		txtRisultato.appendText("Inserisci un codice di un corso");
    		return;
    	}
    	
    	//TODO controllo che il corso esista
    	
    	for(Studente s : this.model.getStudentiByCorso(codins)) {
    		txtRisultato.appendText(s + "\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
    
}