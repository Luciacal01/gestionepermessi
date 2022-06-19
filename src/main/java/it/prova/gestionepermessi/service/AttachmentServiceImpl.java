package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.repository.AttachmentRepository;

@Service
public class AttachmentServiceImpl implements AttachmentService {
	
	@Autowired 
	private AttachmentRepository attachmentRepository;

	@Override
	public Attachment inserisciNuovo(Attachment attachment) {
		return attachmentRepository.save(attachment);
	}

	@Override
	public Attachment caricaSingolo(Long id) {
		return attachmentRepository.findById(id).orElse(null);
	}

	@Override
	public List<Attachment> elencaTutti() {
		return (List<Attachment>) attachmentRepository.findAll();
	}
	
}
