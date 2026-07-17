package it.uniroma3.siw.controller;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Gatto;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;
import it.uniroma3.siw.service.GattoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PreferitoController {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private GattoService gattoService;

    /**
     * metodo GET per mostrare la pagina con tutti i gatti preferiti dell'utente
     */
    @GetMapping("/preferiti")
    public String mostraPreferiti(Model model, Authentication authentication) {
        Utente utente = utenteRepository.findByEmail(authentication.getName());
        model.addAttribute("preferiti", utente.getPreferiti());
        return "preferiti";
    }

    /**
     * Metodo POST che aggiunge un gatto ai preferiti
     */
    @PostMapping("/preferiti/aggiungi")
    public String aggiungiPreferito(@RequestParam("gattoId") Long gattoId, Authentication authentication) {
        Utente utente = utenteRepository.findByEmail(authentication.getName());
        Gatto gatto = gattoService.getGattoById(gattoId);

        utente.getPreferiti().add(gatto);
        utenteRepository.save(utente);

        return "redirect:/galleria";
    }

    /*
     * Rimuove un gatto dai preferiti dell'utente loggato
     */
    @PostMapping("/preferiti/rimuovi")
    public String rimuoviPreferito(@RequestParam("gattoId") Long gattoId, Authentication authentication) {
        Utente utente = utenteRepository.findByEmail(authentication.getName());
        Gatto gatto = gattoService.getGattoById(gattoId);

        utente.getPreferiti().remove(gatto);
        utenteRepository.save(utente);

        return "redirect:/preferiti";
    }

}
