package it.prova.gestionepermessi.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;

public class MessaggioDTO {
	private Long id;
	
	private String testo;
	
	private String oggetto;
	
	private boolean letto;
	
	private Date dataInserimento;
	
	private Date dataLettura;
	
	private RichiestaPermesso richiestaPermesso;

	public MessaggioDTO() {
		super();
	}

	public MessaggioDTO(Long id, String testo, String oggetto, boolean letto, Date dataInserimento, Date dataLettura,
			RichiestaPermesso richiestaPermesso) {
		super();
		this.id = id;
		this.testo = testo;
		this.oggetto = oggetto;
		this.letto = letto;
		this.dataInserimento = dataInserimento;
		this.dataLettura = dataLettura;
		this.richiestaPermesso = richiestaPermesso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public boolean isLetto() {
		return letto;
	}

	public void setLetto(boolean letto) {
		this.letto = letto;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataLettura() {
		return dataLettura;
	}

	public void setDataLettura(Date dataLettura) {
		this.dataLettura = dataLettura;
	}

	public RichiestaPermesso getRichiestaPermesso() {
		return richiestaPermesso;
	}

	public void setRichiestaPermesso(RichiestaPermesso richiestaPermesso) {
		this.richiestaPermesso = richiestaPermesso;
	}
	
	public static MessaggioDTO buildRichiestaPermessoFromModel(Messaggio messaggio) {
		MessaggioDTO result = new MessaggioDTO(messaggio.getId(),
				messaggio.getTesto(), messaggio.getOggetto(), messaggio.isLetto(),
				messaggio.getDataInserimento(), messaggio.getDataLettura(), messaggio.getRichiestaPermesso());
		/*
		 * if(includeDipendente) { if(richiestaPermesso.getDipendente()!=null) {
		 * DipendenteDTO dipendenteDTO=
		 * DipendenteDTO.buildDipendenteFromModel(richiestaPermesso.getDipendente());
		 * result.setDipendenteDTO(dipendenteDTO); } }
		 */

		return result;
	}

	public static List<MessaggioDTO> createMessaggioDTOListFromModelList(
			List<Messaggio> modelListInput) {
		return modelListInput.stream().map(ric -> {
			return MessaggioDTO.buildRichiestaPermessoFromModel(ric);
		}).collect(Collectors.toList());
	}

	public Messaggio buildMessaggioModel() {
		return new Messaggio(this.id, this.testo, this.oggetto, this.letto, this.dataInserimento,
				this.dataLettura, this.richiestaPermesso);
	}

}
