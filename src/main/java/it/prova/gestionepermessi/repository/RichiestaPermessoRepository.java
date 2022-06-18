package it.prova.gestionepermessi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepermessi.model.RichiestaPermesso;

public interface RichiestaPermessoRepository extends CrudRepository<RichiestaPermesso, Long>{
	@Query("from Richiestapermesso r join fetch r.dipendente where r.id=?1")
	Optional<RichiestaPermesso> findByIdEager(Long id);
}
