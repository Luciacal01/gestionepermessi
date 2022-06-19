package it.prova.gestionepermessi.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
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
import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoSearchDTO;
import it.prova.gestionepermessi.dto.RuoloDTO;
import it.prova.gestionepermessi.dto.UtenteDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.StatoUtente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RichiestaPermessoService;
import it.prova.gestionepermessi.service.RuoloService;

@Controller
@RequestMapping(value = "/backoffice")
public class BackofficeController {

	@Autowired
	private DipendenteService dipendenteService;
	
	@Autowired
	private RichiestaPermessoService richiestaPermessoService;

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

	@PostMapping("/saveDipendente")
	public String saveDipendente(@Valid @ModelAttribute("insert_dipendente_attr") DipendenteDTO dipendenteDTO,
			BindingResult result, RedirectAttributes redirectAttrs) {
		
		if (result.hasErrors()) {
			return "backoffice/insertDipendente";
		}
		Dipendente dipendente= dipendenteDTO.buildDipendenteModel();
		Utente utente =new Utente();
		utente.setDipendente(dipendente);
	
		dipendenteService.inserisciNuovoConUtente(utente, dipendente);

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
	
	@GetMapping("/editDipendente/{idDipendente}")
	public String editDipendente(@PathVariable(required = true) Long idDipendente, Model model) {
		model.addAttribute("edit_dipendente_attr",
				DipendenteDTO.buildDipendenteFromModel(dipendenteService.caricaSingoloDipendente(idDipendente)));
		return "backoffice/editdipendente";
	}

	@PostMapping("/updateDipendente")
	public String updateDipendente(@Valid @ModelAttribute("edit_dipendente_attr") DipendenteDTO dipendenteDTO, BindingResult result,
			RedirectAttributes redirectAttrs, HttpServletRequest request) {
		
		if (result.hasErrors()) {
			return "backoffice/editDipendente";
		}
		dipendenteService.aggiorna(dipendenteDTO.buildDipendenteModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/backoffice/listDipendenti";
	}
	
	@GetMapping("/listRichiestePermesso")
	public ModelAndView listRichiestePermesso() {
		ModelAndView mv = new ModelAndView();
		List<RichiestaPermesso> richiestePermessi = richiestaPermessoService.listAllRichieste();
		mv.addObject("richiestepermessi_list_attribute", RichiestaPermessoDTO.createRichiestaPermessoDTOListFromModelList(richiestePermessi));
		mv.setViewName("backoffice/listrichiestepermessi");
		return mv;
	}
	
	@GetMapping("/searchRichiestaPermesso")
	public String searchRichieste(ModelMap model) {
		model.addAttribute("search_richiestapermesso_dipendente_attr",
				DipendenteDTO.createDipendenteDTOListFromModelList(dipendenteService.listAllDipendenti()));
		model.addAttribute("search_richiestapermesso_attr", new RichiestaPermessoDTO());
		return "backoffice/searchRichiestaPermesso";
	}
	
	@PostMapping("/listForSearchRichiestaPermesso")
	public String list(RichiestaPermessoSearchDTO richiestaPermesso, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {
		
		List<RichiestaPermesso> richiestePermessi = richiestaPermessoService.findByExample(richiestaPermesso, pageNo, pageSize, sortBy).getContent();
		model.addAttribute("richiestapermesso_dipendente_list_attribute", RichiestaPermessoDTO.createRichiestaPermessoDTOListFromModelList(richiestePermessi));
		return "backoffice/listRichiestePermessi";
	}
}
