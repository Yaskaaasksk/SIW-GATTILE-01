package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;

@Controller
public class MainController {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/registrazione")
	public String mostraFormRegistrazione(Model model) {
		model.addAttribute("utente", new Utente());
		return "registrazione";
	}

	@PostMapping("/registrazione")
    public String registraUtente(@ModelAttribute("utente") Utente utente) {
        
        // LA MAGIA: Criptiamo la password prima di salvare!
        String passwordCriptata = passwordEncoder.encode(utente.getPassword());
        utente.setPassword(passwordCriptata);
        
        // Di base, chi si registra da questa pagina diventa volontario
        utente.setRuolo("VISITATORE");
        
        // Salviamo tutto nel database
        utenteRepository.save(utente);
        
        return "redirect:/login"; // Rimandiamo alla pagina di accesso
    }
}	

