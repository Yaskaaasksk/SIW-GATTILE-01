package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	/**
	 * metodo che mostra la pagina con il modulo per inserire la password
	 * @return login*/
	@GetMapping("/login")
	public String mostraFormLogin() {
		return "login";
	}
	
	/**
	 * metodo che controlla la password quando l'utente preme invio
	 * @return Login*/
	@PostMapping("/login")
	public String gestisciLogin(@RequestParam("username") String username,
								@RequestParam("password") String password,
								HttpSession session) {
		if("admin".equals(username) && "gatto123".equals(password)) {
			session.setAttribute("ruolo", "VOLONTARIO");
			
			return "redirect:/galleria";
		}
		
		return "redirect:/login?errore=true";
	}
	
	@GetMapping("/logout")
	public String esci(HttpSession session) {
		session.invalidate();
		return "redirect:/galleria";
	}
	

}
