package it.prova.gestionepermessi.service;

import java.util.Optional;

import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;

public interface MessaggioService {
	
	public Messaggio findByRichiesta(Long idRichiesta);
	
	public Optional<Messaggio> cercaSingoloMessaggio(Long id); 
	
	public void inserisciMessaggio(Messaggio messaggioInstance, RichiestaPermesso richiestaInstance);

	public void rimuovi(Long id);

}
