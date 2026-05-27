package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Gatto;
import it.uniroma3.siw.model.GattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GattoPubblicoController {
	
	@Autowired
	private GattoRepository gattoRepository;
	
	@GetMapping("/galleria")
	public String mostraGalleria(Model model) {
		
		Iterable<Gatto> tuttiIGatti = gattoRepository.findAll();
		
		model.addAttribute("gattiDaMostrare", tuttiIGatti);
		
	return "galleria";
	}
}
