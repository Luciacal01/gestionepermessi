package it.prova.gestionepermessi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.prova.gestionepermessi.dto.AttachmentDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoSearchDTO;
import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.repository.AttachmentRepository;
import it.prova.gestionepermessi.repository.RichiestaPermessoRepository;

@Service
public class RichiestaPermessoServiceImpl implements RichiestaPermessoService {
	
	@Autowired
	private RichiestaPermessoRepository richiestaPermessoRepository;
	
	@Autowired 
	private AttachmentRepository attachmentRepository;
	
	@Autowired 
	private MessaggioService messaggioService;
	
	@Override
	public List<RichiestaPermesso> listAllRichieste() {
		return (List<RichiestaPermesso>) richiestaPermessoRepository.findAll();
	}

	@Override
	public RichiestaPermesso caricaSingolaRichiesta(Long id) {
		return richiestaPermessoRepository.findById(id).orElse(null);
	}

	//@Override
	//public RichiestaPermesso caricaSingolaRichiestaConDipendente(Long id) {
		//return richiestaPermessoRepository.findByIdEager(id).orElse(null);
	//}

	@Override
	public Page<RichiestaPermesso> findByExample(RichiestaPermessoSearchDTO example, Integer pageNo, Integer pageSize,
			String sortBy) {
		Specification<RichiestaPermesso> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();
			
			root.fetch("dipendente", JoinType.INNER);
			
			if(StringUtils.isNotEmpty(example.getCodiceCertificato()))
				predicates.add(cb.like(cb.upper(root.get("CodiceCertificato")), "%"+ example.getCodiceCertificato().toUpperCase()+"%" ));
			
			if (example.getDataInizio() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataInizio"), example.getDataInizio()));
			
			if (example.getDataFine() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataFine"), example.getDataFine()));
			
			if (example.getTipoPermesso() != null)
				predicates.add(cb.equal(root.get("tipoPermesso"), example.getTipoPermesso()));
		
			if (example.getDipendente() != null && example.getDipendente().getId() != null) {
				predicates.add(
						cb.equal(root.join("dipendente").get("id"), example.getDipendente().getId()));
			}
			query.distinct(true);
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return richiestaPermessoRepository.findAll(specificationCriteria, paging);
	}

	@Override
	public List<RichiestaPermesso> caricaRichiesteConDipendente(Long id) {
		return richiestaPermessoRepository.findAllByDipendente_id(id);
	}

	@Override
	public void inserisciRichiestaConAttachment(RichiestaPermesso richiestaPermesso,  MultipartFile file) {
		
		if(file!=null) {
			Attachment attachment= new Attachment();
			attachment.setNomeFile(file.getOriginalFilename());
			attachment.setContentType(file.getContentType());
			try {
				attachment.setpayload(file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			richiestaPermesso.setAttachment( attachment);
			attachmentRepository.save(attachment);
			richiestaPermessoRepository.save(richiestaPermesso);
		}
		richiestaPermessoRepository.save(richiestaPermesso);
		messaggioService.inserisciMessaggio(new Messaggio(), richiestaPermesso);
		
	}

	@Override
	public List<RichiestaPermesso> caricaRichiesteConAttachment(Long id) {
		return richiestaPermessoRepository.findAllByAttachment_id(id);
	}

}
