package it.prova.gestionepermessi.service;

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

import it.prova.gestionepermessi.dto.AttachmentDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.repository.AttachmentRepository;
import it.prova.gestionepermessi.repository.RichiestaPermessoRepository;

@Service
public class RichiestaPermessoServiceImpl implements RichiestaPermessoService {
	
	@Autowired
	private RichiestaPermessoRepository richiestaPermessoRepository;
	
	@Autowired AttachmentRepository attachmentRepository;
	
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
	public Page<RichiestaPermesso> findByExample(RichiestaPermessoDTO example, Integer pageNo, Integer pageSize,
			String sortBy) {
		Specification<RichiestaPermesso> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();
			
			root.fetch("dipendente", JoinType.INNER);
			
			if(StringUtils.isNotEmpty(example.getCodiceCertificato()))
				predicates.add(cb.like(cb.upper(root.get("CodiceCertificato")), "%"+ example.getCodiceCertificato().toUpperCase()+"%" ));
			
			if(StringUtils.isNotEmpty(example.getNote()))
				predicates.add(cb.like(cb.upper(root.get("note")), "%"+ example.getNote().toUpperCase()+"%" ));
			
			if(example.isApprovato() || !example.isApprovato())
				predicates.add(cb.like(cb.upper(root.get("approvato")), "%"+ example.isApprovato() ));
			
			if (example.getDataInizio() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataInizio"), example.getDataInizio()));
			
			if (example.getDataFine() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataFine"), example.getDataFine()));
			
			if (example.getTipoPermesso() != null)
				predicates.add(cb.equal(root.get("tipoPermesso"), example.getTipoPermesso()));
			
			if(example.getAttachment()!= null) {
				predicates.add(root.join("attachment").in(example.getAttachment()));
			}
			if(example.getDipendenteDTO()!= null) {
				predicates.add(root.join("dipendente").in(example.getDipendenteDTO()));
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
	public void inserisciRichiesta(RichiestaPermesso richiestaPermesso, AttachmentDTO attachmentDTO) {
		if(richiestaPermesso.getDataFine()==null) {
			Calendar calendar= Calendar.getInstance();
			calendar.setTime(richiestaPermesso.getDataInizio());
			calendar.add(Calendar.HOUR, 24);
			richiestaPermesso.setDataFine(calendar.getTime());
		}
		richiestaPermessoRepository.save(richiestaPermesso);
		
		if(attachmentDTO!=null) {
			Attachment attachment= new Attachment(attachmentDTO.getNomeFile(), attachmentDTO.getContentType(), attachmentDTO.getFile());
			attachmentRepository.save(attachment);
		}
		
	}

}
