package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	/**
	 * metodo GET che ritorna la pagina del Login
	 * 
	 * @return login.html, pagina del login
	 */
	@GetMapping("/login")
	public String mostraFormLogin() {
		return "login";
	}

	/**
	 * metodo POST che controlla la password quando l'utente preme invio
	 * 
	 * @return galleria.html se è l'autenticazione è andata a buon fine
	 */
	@PostMapping("/login")
	public String gestisciLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session) {
		if ("admin".equals(username) && "gatto123".equals(password)) {
			session.setAttribute("ruolo", "VOLONTARIO");

			return "redirect:/galleria";
		}

		return "redirect:/login?errore=true";
	}

	/**
	 * metodo GET che permette all'utente di fare il logout
	 * @return galleria.html se il logout è andato a buon fine
	 *  */
	@GetMapping("/logout")
	public String esci(HttpSession session) {
		session.invalidate();
		return "redirect:/galleria";
	}
	
	@GetMapping("/registrazione")
	public String registrazione() {
		return "registrazione";
	}

}
