package it.prova.gestionepermessi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import it.prova.gestionepermessi.dto.UtenteSearchDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;

public interface UtenteService {
	public List<Utente> listAllUtenti() ;

	public Utente caricaSingoloUtente(Long id);
	
	public Utente caricaSingoloUtenteConRuoli(Long id);

	public void aggiorna(Utente utenteInstance);
	
	public void aggiorna2(Utente utenteInstance);

	public void inserisciNuovo(Utente utenteInstance);

	public void rimuovi(Utente utenteInstance);
	
	public void inserisciNuovoConDipendente(Utente utenteInstance, Dipendente dipendenteInstance);
	
	public Page<Utente> findByExample(UtenteSearchDTO example, Integer pageNo, Integer pageSize, String sortBy);
	
	public Utente findByUsernameAndPassword(String username, String password);
	
	public Utente eseguiAccesso(String username, String password);
	
	public void changeUserAbilitation(Long utenteInstanceId);
	
	public Utente findByUsername(String username);
	
	public void resetPasswordService(Long idUtente);
	
	public Utente cercaSingoloUtenteEager(Long id);
}
