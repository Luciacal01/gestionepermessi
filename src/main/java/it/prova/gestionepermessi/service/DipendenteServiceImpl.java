package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.StatoUtente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.repository.DipendenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {
	
	private DipendenteRepository dipendenteRepository;

	@Override
	public List<Dipendente> listAllUtenti() {
		return (List<Dipendente>) dipendenteRepository.findAll();
	}

	@Override
	public Dipendente caricaSingoloUtente(Long id) {
		return dipendenteRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Dipendente dipendenteInstance) {
		Dipendente dipendenteReloaded= dipendenteRepository.findById(dipendenteInstance.getId()).orElse(null);
		if(dipendenteReloaded==null) throw new RuntimeException("Elemento non trovato");
		//da aggiornare
		dipendenteRepository.save(dipendenteReloaded);
	}

	@Override
	public void inserisciNuovo(Dipendente dipendenteInstance) {
		String username= dipendenteInstance.getNome().substring(0)+"."+dipendenteInstance.getCognome();
		dipendenteInstance.getUtente().setUsername(username);
		dipendenteInstance.getUtente().setPassword("Password@01");
		dipendenteInstance.getUtente().setStato(StatoUtente.CREATO);
		dipendenteInstance.setEmail(username+ "@prova.it");
		dipendenteRepository.save(dipendenteInstance);
	}

	@Override
	public void rimuovi(Dipendente dipendenteInstance) {
		// TODO Auto-generated method stub

	}

}
