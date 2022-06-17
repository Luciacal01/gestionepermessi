package it.prova.gestionepermessi.dto;

import java.util.Date;


import it.prova.gestionepermessi.model.StatoUtente;

public class UtenteSearchDTO {
	private Long id;
	
	private String nome;
	
	private String cognome;
	
	private String username;

	private String password;
	
	private String codiceFiscale;

	private String confermaPassword;

	private Date dateCreated;

	private StatoUtente stato;

	private Long[] ruoliIds;

	public UtenteSearchDTO(Long id, String nome, String cognome, String username, String password, String codiceFiscale,
			String confermaPassword, Date dateCreated, StatoUtente stato, Long[] ruoliIds) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.setCodiceFiscale(codiceFiscale);
		this.confermaPassword = confermaPassword;
		this.dateCreated = dateCreated;
		this.stato = stato;
		this.ruoliIds = ruoliIds;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfermaPassword() {
		return confermaPassword;
	}

	public void setConfermaPassword(String confermaPassword) {
		this.confermaPassword = confermaPassword;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public Long[] getRuoliIds() {
		return ruoliIds;
	}

	public void setRuoliIds(Long[] ruoliIds) {
		this.ruoliIds = ruoliIds;
	}
	
	
}
