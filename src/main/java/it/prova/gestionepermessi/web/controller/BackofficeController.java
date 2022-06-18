package it.prova.gestionepermessi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.RuoloDTO;
import it.prova.gestionepermessi.dto.UtenteDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.StatoUtente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.validation.ValidationNoPassword;
import it.prova.gestionepermessi.validation.ValidationWithPassword;

@Controller
@RequestMapping(value = "/backoffice")
public class BackofficeController {

	@Autowired
	private DipendenteService dipendenteService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RuoloService ruoloService;

	@GetMapping("/listDipendenti")
	public ModelAndView listAllDipendenti() {
		ModelAndView mv = new ModelAndView();
		List<Dipendente> dipendenti = dipendenteService.listAllDipendenti();
		mv.addObject("dipendente_list_attribute", DipendenteDTO.createDipendenteDTOListFromModelList(dipendenti));
		mv.setViewName("backoffice/listdipendente");
		return mv;
	}

	@GetMapping("/showdipendente/{idDipendente}")
	public String showDipendente(@PathVariable(required = true) Long idDipendente, Model model) {
		model.addAttribute("show_dipendente_attr", dipendenteService.caricaSingoloDipendente(idDipendente));
		return "backoffice/showdipendente";
	}

	@GetMapping("/searchDipendente")
	public String searchDipendente(Model model) {
		// model.addAttribute("ruoli_totali_attr",
		// RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));

		return "backoffice/searchdipendente";
	}

	@PostMapping("/listAllDipendenti")
	public String listDipendenti(DipendenteDTO dipendenteExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {

		List<Dipendente> dipendenti = dipendenteService.findByExample(dipendenteExample, pageNo, pageSize, sortBy)
				.getContent();

		model.addAttribute("dipendente_list_attribute", DipendenteDTO.createDipendenteDTOListFromModelList(dipendenti));
		return "backoffice/listdipendente";
	}

	@GetMapping("/insertDipendente")
	public String create(Model model) {
		// model.addAttribute("ruoli_totali_attr",
		// RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
		model.addAttribute("insert_dipendente_attr", new DipendenteDTO());
		return "backoffice/insertdipendente";
	}

	// per la validazione devo usare i groups in quanto nella insert devo validare
	// la pwd, nella edit no
	@PostMapping("/saveDipendente")
	public String save(
			@Validated({ ValidationWithPassword.class,
					ValidationNoPassword.class }) @ModelAttribute("insert_dipendente_attr") DipendenteDTO dipendenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {
		UtenteDTO utente = new UtenteDTO();
		
		String username = dipendenteDTO.getNome().substring(0) + "." + dipendenteDTO.getCognome();
		utente.setUsername(username);
		utente.setPassword(passwordEncoder.encode("Password@01"));
		utente.setStato(StatoUtente.CREATO);
		Utente utenteModel = utente.buildUtenteModel(true);
		utenteModel.getRuoli().add(ruoloService.cercaPerDescrizioneECodice("Dipendente User", "ROLE_DIPENDENTE_USER"));
		Dipendente dipendenteModel = dipendenteDTO.buildDipendenteModel();
		utenteModel.setDipendente(dipendenteModel);
		dipendenteModel.setUtente(utenteModel);
		/*
		if (result.hasErrors()) {
			return "backoffice/insertDipendente";
		}
		*/
	
		dipendenteService.inserisciNuovoConUtente(utenteModel, dipendenteModel);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/backoffice/listDipendenti";
	}

	@GetMapping(value = "/searchDipendentiAjax", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String searchDipendenti(@RequestParam String term) {

		List<Dipendente> listaRegistaByTerm = dipendenteService.cercaByCognomeENomeILike(term);
		return buildJsonResponse(listaRegistaByTerm);
	}

	private String buildJsonResponse(List<Dipendente> listaDipendenti) {
		JsonArray ja = new JsonArray();

		for (Dipendente registaItem : listaDipendenti) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", registaItem.getId());
			jo.addProperty("label", registaItem.getNome() + " " + registaItem.getCognome());
			ja.add(jo);
		}

		return new Gson().toJson(ja);
	}
}
