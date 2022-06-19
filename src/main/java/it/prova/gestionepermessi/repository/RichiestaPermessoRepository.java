package it.prova.gestionepermessi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepermessi.model.RichiestaPermesso;

public interface RichiestaPermessoRepository extends CrudRepository<RichiestaPermesso, Long>{
	//@Query("from Richiestapermesso r left join fetch r.dipendente where r.id=?1")
	List<RichiestaPermesso> findAllByDipendente_id(Long id);
	
	Page<RichiestaPermesso> findAll(Specification<RichiestaPermesso> specificationCriteria, Pageable paging);

	List<RichiestaPermesso> findAllByAttachment_id(Long id);
}
