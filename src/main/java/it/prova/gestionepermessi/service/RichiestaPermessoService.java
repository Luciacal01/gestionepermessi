package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.Utente;


public interface RichiestaPermessoService {
	public List<RichiestaPermesso> listAllRichieste() ;

	public RichiestaPermesso caricaSingolaRichiesta(Long id);
	
	public RichiestaPermesso caricaSingolaRichiestaConDipendente(Long id);
	
	public Page<RichiestaPermesso> findByExample(RichiestaPermessoDTO example, Integer pageNo, Integer pageSize, String sortBy);
}
