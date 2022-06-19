package it.prova.gestionepermessi.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.repository.MessaggioRepository;

@Service
public class MessaggioServiceImpl implements MessaggioService{
	
	@Autowired
	private MessaggioRepository messaggioRepository;
	
	

	@Override
	public void inserisciMessaggio(Messaggio messaggioInstance, RichiestaPermesso richiestaInstance) {
		String note = richiestaInstance.getNote().isBlank() ? "" : " , le note del dipendente " + richiestaInstance.getNote();
		String codiceCertificato = richiestaInstance.getCodiceCertificato().isBlank() ? ""
				: " , il Codice del Certificato: " + richiestaInstance.getCodiceCertificato();
		String attachment = richiestaInstance.getAttachment() == null ? "" : " , il file allegato";
		String parteFinaleMessaggio = ".";

		if (!note.isBlank() || !codiceCertificato.isBlank() || !attachment.isBlank()) {
			parteFinaleMessaggio += " E' allegato il certificato :";
			parteFinaleMessaggio += note.isBlank() ? "" : " " + note;
			parteFinaleMessaggio += codiceCertificato.isBlank() ? "" : " " + codiceCertificato;
			parteFinaleMessaggio += attachment.isBlank() ? "" : " " + attachment;
			parteFinaleMessaggio += ".";
		}

		messaggioInstance.setOggetto("Richiesta Permesso di " + richiestaInstance.getDipendente().getNome() + " "
				+ richiestaInstance.getDipendente().getCognome());

		messaggioInstance.setTesto(
				"Il dipendente " + richiestaInstance.getDipendente().getNome() + " " + richiestaInstance.getDipendente().getCognome()
						+ " ha richiesto un permesso per " + richiestaInstance.getTipoPermesso() + " dal giorno "
						+ richiestaInstance.getDataInizio() + " al giorno " + richiestaInstance.getDataFine() + parteFinaleMessaggio);

		messaggioInstance.setDataInserimento(new Date());
		messaggioInstance.setLetto(true);
		messaggioInstance.setRichiestaPermesso(richiestaInstance);

		messaggioRepository.save(messaggioInstance);
		
	}

	@Override
	public void rimuovi(Long id) {
		Messaggio messaggio = messaggioRepository.findById(id).orElse(null);
		messaggioRepository.delete(messaggio);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Messaggio> cercaSingoloMessaggio(Long id) {
		return messaggioRepository.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Messaggio findByRichiesta(Long idRichiesta) {
		return messaggioRepository.findByRichiestaPermesso_Id(idRichiesta);
	}

}
