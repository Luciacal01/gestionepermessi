package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import it.prova.gestionepermessi.dto.AttachmentDTO;
import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoSearchDTO;
import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.Utente;


public interface RichiestaPermessoService {
	public List<RichiestaPermesso> listAllRichieste() ;

	public RichiestaPermesso caricaSingolaRichiesta(Long id);
	
	public RichiestaPermesso caricaSingolaRichiestaConAttachment(Long id);
	
	public List<RichiestaPermesso> caricaRichiesteConDipendente(Long id);
	
	public List<RichiestaPermesso> caricaRichiesteConAttachment(Long id);
	
	public Page<RichiestaPermesso> findByExample(RichiestaPermessoSearchDTO example, Integer pageNo, Integer pageSize, String sortBy);

	void inserisciRichiestaConAttachment(RichiestaPermesso richiestaPermesso, MultipartFile file);

	public void rimuovi(Long idRichiestapermesso);
	
	public void aggiorna(Long idRichiestapermesso, MultipartFile file);

	RichiestaPermesso caricaSingolaRichiestaConDipendente(Long id);

	void aggiorna2(RichiestaPermesso richiestaModel);
}
