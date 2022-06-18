package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.StatoUtente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.repository.DipendenteRepository;
import it.prova.gestionepermessi.repository.RuoloRepository;
import it.prova.gestionepermessi.repository.UtenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	@Autowired
	private RuoloRepository ruoloRepository;
	@Autowired
	private DipendenteRepository dipendenteRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Dipendente> listAllDipendenti() {
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
		dipendenteInstance.setEmail(username+ "@prova.it");
		dipendenteRepository.save(dipendenteInstance);
	}

	@Override
	public void rimuovi(Dipendente dipendenteInstance) {
		dipendenteInstance.getUtente().setStato(StatoUtente.DISABILITATO);

	}
	
	public Page<Dipendente> findByExample(DipendenteDTO example, Integer pageNo, Integer pageSize, String sortBy){
		Specification<Dipendente> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();
			
			root.fetch("utente", JoinType.INNER);
			root.fetch("richiestePermessi", JoinType.LEFT);
			
			if(StringUtils.isNotEmpty(example.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%"+ example.getNome().toUpperCase()+"%" ));
			
			if(StringUtils.isNotEmpty(example.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%"+ example.getCognome().toUpperCase()+"%" ));
			
			if(StringUtils.isNotEmpty(example.getEmail()))
				predicates.add(cb.like(cb.upper(root.get("email")), "%"+ example.getEmail().toUpperCase()+"%" ));
			
			if(StringUtils.isNotEmpty(example.getCodiceFiscale()))
				predicates.add(cb.like(cb.upper(root.get("codiceFiscale")), "%"+ example.getCodiceFiscale().toUpperCase()+"%" ));
			
			if (example.getDataNascita() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataNascita"), example.getDataNascita()));
			
			if (example.getDataAssunzione() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataAssunzione"), example.getDataAssunzione()));
			
			if (example.getDataDimissioni() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataDimissioni"), example.getDataDimissioni()));
			
			if (example.getSesso() != null)
				predicates.add(cb.equal(root.get("sesso"), example.getSesso()));
			
			//Da modificare
			
			query.distinct(true);
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
		dipendenteInstance.setEmail(username+ "@prova.it");
		utenteRepository.save(utenteInstance);
		dipendenteRepository.save(dipendenteInstance);
	}

	@Override
	public List<Dipendente> cercaByCognomeENomeILike(String term) {
		return dipendenteRepository.findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(term, term);
	}

}
