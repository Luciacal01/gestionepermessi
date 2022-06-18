package it.prova.gestionepermessi.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Sesso;
import it.prova.gestionepermessi.model.Utente;


public class DipendenteDTO {

	private Long id;

	@NotBlank(message = "{nome.notBlanck}")
	private String nome;

	@NotBlank(message = "{cognome.notBlanck}")
	private String cognome;

	@NotBlank(message = "{codiceFiscale.notBlanck}")
	@Size(min = 16, max = 16, message = "il valore inserito '${validatedValue}' deve essere di {min} caratteri")
	private String codiceFiscale;

	private String email;

	@NotNull(message = "{dataNascita.notNull}")
	private Date dataNascita;

	private Date dataAssunzione;

	private Date dataDimissioni;

	@NotNull(message = "{sesso.notNull}")
	private Sesso sesso;

	private UtenteDTO utenteDTO;

	private Long[] richiestaPermessiIds;

	public DipendenteDTO() {
		super();
	}

	public DipendenteDTO(Long id) {
		super();
		this.id = id;
	}

	

	public DipendenteDTO(Long id, @NotBlank(message = "{nome.notBlanck}") String nome,
			@NotBlank(message = "{cognome.notBlanck}") String cognome,
			@NotBlank(message = "{codiceFiscale.notBlanck}") @Size(min = 16, max = 16, message = "il valore inserito '${validatedValue}' deve essere di {min} caratteri") String codiceFiscale,
			String email, @NotNull(message = "{dataNascita.notNull}") Date dataNascita, Date dataAssunzione,
			@NotNull(message = "{sesso.notNull}") Sesso sesso) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.sesso = sesso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Date getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	public Date getDataDimissioni() {
		return dataDimissioni;
	}

	public void setDataDimissioni(Date dataDimissioni) {
		this.dataDimissioni = dataDimissioni;
	}

	public Sesso getSesso() {
		return sesso;
	}

	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
	}

	public UtenteDTO getUtenteDTO() {
		return utenteDTO;
	}

	public void setUtenteDTO(UtenteDTO utenteDTO) {
		this.utenteDTO = utenteDTO;
	}

	public Long[] getRichiestaPermessiIds() {
		return richiestaPermessiIds;
	}

	public void setRichiestaPermessiIds(Long[] richiestaPermessiIds) {
		this.richiestaPermessiIds = richiestaPermessiIds;
	}

	public static DipendenteDTO buildDipendenteFromModel(Dipendente dipendenteModel) {
		DipendenteDTO result = new DipendenteDTO(dipendenteModel.getId(), dipendenteModel.getNome(),
				dipendenteModel.getCognome(), dipendenteModel.getCodiceFiscale(), dipendenteModel.getEmail(),
				dipendenteModel.getDataNascita(), dipendenteModel.getDataAssunzione(),dipendenteModel.getSesso());
		
		/*
			if(!dipendenteModel.getRichiestePermessi().isEmpty()) {
				result.richiestaPermessiIds=dipendenteModel.getRichiestePermessi().stream().map(r->r.getId()).collect(Collectors.toList()).toArray(new Long[] {});
			}
		*/
		
		return result;
	}
	
	public static List<DipendenteDTO> createDipendenteDTOListFromModelList(List<Dipendente> modelListInput) {
		return modelListInput.stream().map(utenteEntity -> {
			return DipendenteDTO.buildDipendenteFromModel(utenteEntity);
		}).collect(Collectors.toList());
	}
	
	public Dipendente buildDipendenteModel() {
		return new Dipendente(this.id, this.nome, this.cognome, this.codiceFiscale,this.dataNascita, this.dataAssunzione, this.dataDimissioni, this.sesso);
	}
	
	public Dipendente buildDipendenteModelConUtente() {
		return new Dipendente(this.id, this.nome, this.cognome, this.codiceFiscale,this.dataNascita, this.dataAssunzione, this.dataDimissioni, this.sesso, this.utenteDTO);
	}

}
