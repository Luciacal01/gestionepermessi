package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.StatoUtente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.repository.DipendenteRepository;
import it.prova.gestionepermessi.repository.UtenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	@Autowired
	private DipendenteRepository dipendenteRepository;

	@Override
	public List<Dipendente> listAllUtenti() {
		return (List<Dipendente>) dipendenteRepository.findAll();
	}

	@Override
	public Dipendente caricaSingoloDipendente(Long id) {
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
	
	public Page<Dipendente> findByExample(Dipendente example, Integer pageNo, Integer pageSize, String sortBy){
		Specification<Dipendente> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();
			
			root.fetch("utente", JoinType.INNER);
			root.fetch("richiestapermesso", JoinType.LEFT);
			
			if(StringUtils.isNotEmpty(example.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%"+ example.getNome().toUpperCase()+"%" ));
			
			
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return dipendenteRepository.findAll(specificationCriteria, paging);
	}

	@Override
	public void inserisciNuovoConUtente(Utente utenteInstance, Dipendente dipendenteInstance) {
		String username= dipendenteInstance.getNome().substring(0)+"."+dipendenteInstance.getCognome();
		dipendenteInstance.getUtente().setUsername(username);
		dipendenteInstance.getUtente().setPassword("Password@01");
		//dipendenteInstance.getUtente().setRuoli(new Ruolo("Dipendente User", "ROLE_DIPENDENTE_USER"));
		dipendenteInstance.getUtente().setStato(StatoUtente.CREATO);
		dipendenteInstance.setEmail(username+ "@prova.it");
		utenteRepository.save(utenteInstance);
		dipendenteRepository.save(dipendenteInstance);
	}

}
