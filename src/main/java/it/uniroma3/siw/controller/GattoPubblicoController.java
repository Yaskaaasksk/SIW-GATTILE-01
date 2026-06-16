package it.uniroma3.siw.controller;

import org.springframework.security.core.Authentication; // Import fondamentale per i ruoli!

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
	public String mostraGalleria(Model model, Authentication authentication) {
		
		// 1. Carichiamo i gatti
		model.addAttribute("gattiDaMostrare", gattoService.tuttiIGatti());
		
		// 2. Di base, impostiamo che TUTTI vedono il bottone (Anonimi e Visitatori)
		boolean mostraBottone = true;
		
		// 3. Se c'è un utente loggato, indaghiamo...
		if (authentication != null) {
			
			// Controlliamo se tra i suoi ruoli c'è "ADMIN" o "VOLONTARIO"
			boolean isStaff = authentication.getAuthorities().stream()
					.anyMatch(ruolo -> ruolo.getAuthority().contains("ADMIN") || ruolo.getAuthority().contains("VOLONTARIO"));
			
			// Se fa parte dello staff, gli nascondiamo il bottone!
			if (isStaff) {
				mostraBottone = false;
			}
		}
		
		// Passiamo la decisione finale alla pagina HTML
		model.addAttribute("mostraBottone", mostraBottone);
		
		return "galleria";
	}
}