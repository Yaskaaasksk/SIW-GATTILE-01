package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Gatto;
import it.uniroma3.siw.model.GattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class GattoAdminController{
	
	@Autowired
	private GattoRepository gattoRepository;
	
	@GetMapping("/admin/formGatto")
	public String mostraFormAggiungiGatto(Model model) {
		model.addAttribute("gatto", new Gatto());
		return "formGatto";
	}
	
	@PostMapping("/admin/gatto")
	public String salvaGattoInDatabase(@ModelAttribute("gatto") Gatto gatto) {
		gattoRepository.save(gatto);
		
		return "redirect:/galleria";
	}
}