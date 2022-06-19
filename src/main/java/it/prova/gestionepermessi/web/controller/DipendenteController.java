package it.prova.gestionepermessi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RichiestaPermessoService;

@Controller
@RequestMapping(value = "/dipendente")
public class DipendenteController {
	
	@Autowired
	private DipendenteService dipendenteService;
	
	@Autowired
	private RichiestaPermessoService richiestaPermessoService;
	
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
}
