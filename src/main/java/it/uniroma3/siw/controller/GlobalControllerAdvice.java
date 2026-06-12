package it.uniroma3.siw.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

	//questa variabile mi serve per mostrare il tasto Esci a chiunque abbia fatto il login
	@ModelAttribute("isLoggato")
	public boolean utenteLoggato() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser");
	}
	
	
	//questa variabile serve per mostrare i tasti ROSSI solo allo staff
    @ModelAttribute("isVolontario")
    public boolean utenteVolontario() {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            
        	return auth.getAuthorities().stream().anyMatch(ruolo->ruolo.getAuthority().equals("ROLE_VOLONTARIO") |
        													ruolo.getAuthority().equals("ROLE_ADMIN"));
      
        }
        
        return false;
    }
}