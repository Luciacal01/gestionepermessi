package it.prova.gestionepermessi.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionepermessi.dto.AttachmentDTO;
import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoSearchDTO;
import it.prova.gestionepermessi.dto.RuoloDTO;
import it.prova.gestionepermessi.dto.UtenteDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.MessaggioService;
import it.prova.gestionepermessi.service.RichiestaPermessoService;

@Controller
@RequestMapping(value = "/Dipendente")
public class DipendenteController {
	
	@Autowired
	private DipendenteService dipendenteService;
	
	@Autowired
	private RichiestaPermessoService richiestaPermessoService;
	
	@Autowired
	private MessaggioService messaggioService;
	
	@GetMapping("/listRichiestePermesso")
	public ModelAndView listRichiestePermesso() {
		ModelAndView mv = new ModelAndView();
		Authentication  utenteInPagina= SecurityContextHolder.getContext().getAuthentication();
		Dipendente dipendente= dipendenteService.caricaSingoloDipendentePerUsername(utenteInPagina.getName());
		List<RichiestaPermesso> richiestaPermesso = richiestaPermessoService.caricaRichiesteConDipendente(dipendente.getId());
		mv.addObject("richiestepermessi_list_attribute", RichiestaPermessoDTO.createRichiestaPermessoDTOListFromModelList(richiestaPermesso));
		mv.setViewName("dipendente/listrichiestepermessi");
		return mv;
	}
	
	@GetMapping("/insertRichiesta")
	public String create(Model model) {
		model.addAttribute("insert_Richiestapermesso_attr", new RichiestaPermessoDTO());
		return "dipendente/insertRichiestaPermesso";
	}

	@PostMapping("/saveRichiestaPermesso")
	public String saveRichiestaPermesso(@Valid @ModelAttribute("insert_Richiestapermesso_attr") RichiestaPermessoDTO richiestaPermessoDTO,BindingResult result, RedirectAttributes redirectAttrs) {
		/*
		if (result.hasErrors()) {
			return "dipendente/insertRichiestaPermesso";
		}
		*/
		
		RichiestaPermesso richiestaPermesso=richiestaPermessoDTO.buildRichiestaPermessoModel();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null) {
			throw new RuntimeException("Errore!");
		}
		Dipendente dipendente = dipendenteService.caricaSingoloDipendentePerUsername(auth.getName());
		if (dipendente == null) {
			throw new RuntimeException("Errore!");
		}

		richiestaPermesso.setDipendente(dipendente);
		
		richiestaPermessoService.inserisciRichiestaConAttachment(richiestaPermesso, richiestaPermessoDTO.getAttachment());
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/Dipendente/listRichiestePermesso";
	}
	
	@GetMapping("/searchRichiestaPermesso")
	public String searchRichiestaPermesso(Model model) {
		return "dipendente/searchRichiestaPermesso";
	}
	

	@PostMapping("/listRichieste")
	public String listRichieste( RichiestaPermessoSearchDTO richiestePermessoExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {
		Authentication  utenteInPagina= SecurityContextHolder.getContext().getAuthentication();
		Dipendente dipendente= dipendenteService.caricaSingoloDipendentePerUsername(utenteInPagina.getName());
		Long id= dipendente.getId();
		List<RichiestaPermesso> richiestePermessi = richiestaPermessoService.findByExample(richiestePermessoExample, pageNo, pageSize, sortBy).getContent().stream().filter(richiesta -> richiesta.getDipendente().getId() == id)
				.collect(Collectors.toList());;
		model.addAttribute("richiestepermessi_list_attribute", RichiestaPermessoDTO.createRichiestaPermessoDTOListFromModelList(richiestePermessi));
		return "dipendente/listrichiestepermessi";
	}
	
	@GetMapping("/deleteRichiestapermesso/{idRichiestapermesso}")
	public String deleteRichiestapermesso(@PathVariable(required = true) Long idRichiestapermesso, Model model) {
		model.addAttribute("delete_richiestapermesso_attr",
				richiestaPermessoService.caricaSingolaRichiesta(idRichiestapermesso));

		return "dipendente/deleteRichiestaPermesso";
	}

	@PostMapping("/removeRichiestaPermesso")
	public String remove(@RequestParam(required = true) Long idRichiestapermesso, RedirectAttributes redirectAttrs) {

		Messaggio messaggioItem = messaggioService.findByRichiesta(idRichiestapermesso);

		if (messaggioItem != null) {
			messaggioService.rimuovi(messaggioItem.getId());
		}

		richiestaPermessoService.rimuovi(idRichiestapermesso);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:Dipendente/listRichiestaPermesso";
	}
	
	@GetMapping("/editRichiestaPermesso/{idRichiestaPermesso}")
	public String edit(@PathVariable(required = true) Long idRichiestaPermesso, Model model) {
		RichiestaPermesso richiestaPermessoModel= richiestaPermessoService.caricaSingolaRichiestaConAttachment(idRichiestaPermesso);
		model.addAttribute("edit_richiestapermesso_attr", RichiestaPermessoDTO.buildRichiestaPermessoFromModel(richiestaPermessoModel));
		return "dipendente/updateRichiestapermesso";
	}

	@PostMapping("/updateRichiestaPermesso")
	public String update(@Valid @ModelAttribute("edit_richiestapermesso_attr") RichiestaPermessoDTO richiestaPermessoDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {
		
//		if (result.hasErrors()) {
//			return "Dipendente/editRichiestaPermesso";
//		}
		RichiestaPermesso richiestaPermesso=richiestaPermessoDTO.buildRichiestaPermessoModel();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null) {
			throw new RuntimeException("Errore!");
		}
		Dipendente dipendente = dipendenteService.caricaSingoloDipendentePerUsername(auth.getName());
		if (dipendente == null) {
			throw new RuntimeException("Errore!");
		}

		richiestaPermesso.setDipendente(dipendente);
		
		richiestaPermessoService.aggiorna(richiestaPermesso.getId(), richiestaPermessoDTO.getAttachment());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/Dipendente/listRichiestePermesso";
	}
	
	
}
