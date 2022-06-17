package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;

public interface DipendenteService {
	public List<Dipendente> listAllUtenti() ;

	public Dipendente caricaSingoloDipendente(Long id);

	public void aggiorna(Dipendente utenteInstance);

	public void inserisciNuovo(Dipendente utenteInstance);

	public void rimuovi(Dipendente utenteInstance);
	
	public void inserisciNuovoConUtente(Utente utenteInstance, Dipendente dipendenteInstance);
	
	public Page<Dipendente> findByExample(Dipendente example, Integer pageNo, Integer pageSize, String sortBy);
}
