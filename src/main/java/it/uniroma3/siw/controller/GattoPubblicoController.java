package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Gatto;
import it.uniroma3.siw.model.GattoRepository;
import it.uniroma3.siw.service.GattoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GattoPubblicoController {
	
	@Autowired
	private GattoService gattoService;
	
	@GetMapping("/galleria")
	public String mostraGalleria(Model model) {
		model.addAttribute("gattiDaMostrare", gattoService.tuttiIGatti());
		return "galleria";
	}
}
