package it.prova.gestionepermessi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepermessi.model.Dipendente;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long> {

	Page<Dipendente> findAll(Specification<Dipendente> specificationCriteria, Pageable paging);

}
