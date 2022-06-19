package it.prova.gestionepermessi.service;

import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;

public interface MessaggioService {
	
	public void inserisciMessaggio(Messaggio messaggioInstance, RichiestaPermesso richiestaInstance);

}
