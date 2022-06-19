package it.prova.gestionepermessi.service;

import java.util.List;

import it.prova.gestionepermessi.model.Attachment;

public interface AttachmentService {
	public Attachment inserisciNuovo(Attachment attachment);
	
	public Attachment caricaSingolo(Long id);
	
	List<Attachment> elencaTutti();
}
