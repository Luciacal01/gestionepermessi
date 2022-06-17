package it.prova.gestionepermessi;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.Sesso;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.service.UtenteService;

@SpringBootApplication
public class GestionepermessiApplication implements CommandLineRunner{
	
	@Autowired
	private RuoloService ruoloServiceInstance;
	
	@Autowired 
	private DipendenteService dipendenteService;
	@Autowired
	private UtenteService utenteServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(GestionepermessiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("BackOffice User", "ROLE_BO_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("BackOffice User", "ROLE_BO_USER"));

		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Dipendente User", "ROLE_DIPENDENTE_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Dipendente User", "ROLE_DIPENDENTE_USER"));

		}
		
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Lucia", "Calabria", new Date());
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
			Dipendente dipendente = new Dipendente("Lucia", "Calabria","l.calabria@prova.it", "CLBLNT01T56E977V",new SimpleDateFormat("dd-MM-yyyy").parse("16-12-2001") , new SimpleDateFormat("dd-MM-yyyy").parse("18-06-2022"), Sesso.F);
			admin.setDipendente(dipendente);
			dipendente.setUtente(admin);
			
			utenteServiceInstance.inserisciNuovoConDipendente(admin, dipendente);
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}
		
		if (utenteServiceInstance.findByUsername("bo1") == null) {
			Utente backOffice = new Utente("bo1", "bo", "Saverio", "Carelli", new Date());
			backOffice.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("BackOffice User", "ROLE_BO_USER"));
			Dipendente dipendente = new Dipendente("Saverio", "Carelli","s.carelli@prova.it", "SVRCRL21D77W631C",new SimpleDateFormat("dd-MM-yyyy").parse("21-03-1993") , new SimpleDateFormat("dd-MM-yyyy").parse("11-02-2022"), Sesso.M);
			backOffice.setDipendente(dipendente);
			dipendente.setUtente(backOffice);
			
			utenteServiceInstance.inserisciNuovoConDipendente(backOffice, dipendente);
			utenteServiceInstance.changeUserAbilitation(backOffice.getId());
		}
		
		if (utenteServiceInstance.findByUsername("bo2") == null) {
			Utente backOffice = new Utente("bo2", "bo2", "Cristian", "Marcelli", new Date());
			backOffice.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("BackOffice User", "ROLE_BO_USER"));
			Dipendente dipendente = new Dipendente("Cristian", "Marcelli","c.marcelli@prova.it", "CRSMRC02R92E920S",new SimpleDateFormat("dd-MM-yyyy").parse("02-11-1995") , new SimpleDateFormat("dd-MM-yyyy").parse("29-08-2022"), Sesso.M);
			backOffice.setDipendente(dipendente);
			dipendente.setUtente(backOffice);
			
			utenteServiceInstance.inserisciNuovoConDipendente(backOffice, dipendente);
			utenteServiceInstance.changeUserAbilitation(backOffice.getId());
		}
		
		if (utenteServiceInstance.findByUsername("dipendenteU") == null) {
			Utente dipendenteUser = new Utente("dipendenteU", "dipendenteU", "Claudio", "Buzi", new Date());
			dipendenteUser.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Dipendente User", "ROLE_DIPENDENTE_USER"));
			Dipendente dipendente = new Dipendente("Claudio", "Buzi","c.buzi@prova.it", "CLDBZU15G72A481Y",new SimpleDateFormat("dd-MM-yyyy").parse("15-12-1887") , new SimpleDateFormat("dd-MM-yyyy").parse("31-10-2022"), Sesso.M);
			dipendenteUser.setDipendente(dipendente);
			dipendente.setUtente(dipendenteUser);
			
			utenteServiceInstance.inserisciNuovoConDipendente(dipendenteUser, dipendente);
			utenteServiceInstance.changeUserAbilitation(dipendente.getId());
		}
		
	}
	

}
