package it.prova.gestionepermessi.service;

import java.util.List;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;

public interface DipendenteService {
	public List<Dipendente> listAllUtenti() ;

	public Dipendente caricaSingoloUtente(Long id);

	public void aggiorna(Dipendente utenteInstance);

	public void inserisciNuovo(Dipendente utenteInstance);

	public void rimuovi(Dipendente utenteInstance);
}
