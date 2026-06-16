package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.model.Adozione;
import it.uniroma3.siw.model.Gatto;
import it.uniroma3.siw.service.AdozioneService;
import it.uniroma3.siw.service.GattoService;

@Controller
public class AdozioneController {

    @Autowired
    private GattoService gattoService;
    
    // MODIFICA: Ora usiamo il Service invece del Repository
    @Autowired
    private AdozioneService adozioneService;

    // Quando l'utente clicca "Adotta questo gatto"
    @GetMapping("/adotta")
    public String mostraFormAdozione(@RequestParam("gattoId") Long gattoId, Model model) {
        Gatto gatto = gattoService.getGattoById(gattoId);
        model.addAttribute("gatto", gatto);
        model.addAttribute("adozione", new Adozione());
        return "formAdozione";
    }

    // Quando l'utente clicca "Invia Richiesta" dal form
    @PostMapping("/salvaAdozione")
    public String salvaAdozione(@ModelAttribute("adozione") Adozione adozione, @RequestParam("gattoId") Long gattoId) {
        Gatto gatto = gattoService.getGattoById(gattoId);
        adozione.setGatto(gatto);
        
        // MODIFICA: Chiamiamo il metodo del Service per salvare nel database
        adozioneService.salvaAdozione(adozione);
        
        // Rimandiamo alla galleria attivando il messaggio verde di successo!
        return "redirect:/galleria?successo=true";
    }
    
 // Mostra la pagina con la barra di ricerca
    @GetMapping("/controllaStato")
    public String mostraRicercaStato() {
        return "controllaStato";
    }
    
 // Riceve l'email inserita e cerca nel database
    @PostMapping("/cercaLeMieRichieste")
    public String cercaRichieste(@RequestParam("emailCercata") String email, Model model) {
        List<Adozione> richiesteTrovate = adozioneService.trovaAdozioniPerEmail(email);
        
        model.addAttribute("richieste", richiesteTrovate);
        model.addAttribute("email", email); // Ci ricordiamo l'email per mostrarla a schermo
        
        return "controllaStato";
    }
    
    
    
}

