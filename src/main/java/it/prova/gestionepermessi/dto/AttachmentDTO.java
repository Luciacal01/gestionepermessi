package it.prova.gestionepermessi.dto;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import it.prova.gestionepermessi.model.Attachment;

public class AttachmentDTO implements MultipartFile {
	private Long id;
	private String nomeFile;
	private String contentType;

	private byte[] file;

	public AttachmentDTO(Long id, String nomeFile, String contentType, byte[] file) {
		super();
		this.id = id;
		this.nomeFile = nomeFile;
		this.contentType = contentType;
		this.file = file;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
	
	public Attachment buildAttachmentModel(AttachmentDTO attachmentDTO) {
		return new Attachment(attachmentDTO.getId(), attachmentDTO.getNomeFile(), attachmentDTO.getContentType(), attachmentDTO.getFile());
	}
	
	public static AttachmentDTO buildAttachmentDTOFromModel(Attachment attachment) {
		AttachmentDTO res= new AttachmentDTO(attachment.getId(), attachment.getNomeFile(), attachment.getContentType(), attachment.getpayload());
		return res;
	}
	
	public static List<AttachmentDTO> createAttachmentDTOListFromModelList(List<Attachment> modelListInput) {
		return modelListInput.stream().map(attachmentItem -> new AttachmentDTO(attachmentItem.getId(),
				attachmentItem.getNomeFile(), attachmentItem.getContentType(), attachmentItem.getpayload()))
				.collect(Collectors.toList());
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOriginalFilename() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] getBytes() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		// TODO Auto-generated method stub
		
	}
}
