package it.prova.gestionepermessi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionepermessi.dto.AttachmentDTO;
import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.service.AttachmentService;

@RestController
@RequestMapping("/api/documento")
public class AttachmentController {
	@Autowired
	private AttachmentService attachmentService;
	
	@GetMapping
	public List<AttachmentDTO> getAll(){
		return AttachmentDTO.createAttachmentDTOListFromModelList(attachmentService.elencaTutti());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AttachmentDTO createNew(@RequestBody AttachmentDTO param) {
		return AttachmentDTO
				.buildAttachmentDTOFromModel(attachmentService.inserisciNuovo(param.buildAttachmentModel(param)));
	}
	
}
