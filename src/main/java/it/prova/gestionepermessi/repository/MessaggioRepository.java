package it.prova.gestionepermessi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioRepository extends CrudRepository<Messaggio, Long>{
	Messaggio findByRichiestaPermesso_Id(Long idRichiesta);
}
