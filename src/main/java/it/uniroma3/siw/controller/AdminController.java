package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Adozione;
import it.uniroma3.siw.model.Gatto;
import it.uniroma3.siw.repository.GattoRepository;
import it.uniroma3.siw.service.AdozioneService;
import it.uniroma3.siw.service.GattoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private GattoService gattoService;
	
	@Autowired
	private AdozioneService adozioneService;

	/*
	 * gestisce richiesta GET: /admin/formGatto e restituisce la pagina di aggiunta
	 * del gatto.
	 */
	@GetMapping("/formGatto")
	public String mostraFormAggiungiGatto(Model model) {
		model.addAttribute("gatto", new Gatto());
		return "formGatto";
	}

	/*
	 * gestisce richiesta POST: /admin/gatto e salva il gatto nel database. Una
	 * volta salvato rimando alla pagina galleria
	 */
	@PostMapping("/gatto")
	public String salvaGattoInDatabase(@ModelAttribute("gatto") Gatto gatto) {
		gattoService.salvaGatto(gatto);
		return "redirect:/galleria";
	}

	/*
	 * gestisce una richiesta GET: /admin/gestisciGatto e restituisce un form per
	 * modificare i dati del gatto. Ricerca tramite id del gatto
	 */
	@GetMapping("/gestisciGatto")
	public String mostraFormGestione(@RequestParam Long gattoId, Model model) {
		Gatto gattoDaModificare = gattoService.getGattoById(gattoId);
		model.addAttribute("gatto", gattoDaModificare);
		return "gestisciGatto";
	}

	/*
	 * gestisce una richiesta POST: /admin/aggiornaGatto e salva l'eventuale
	 * modifica effettuata ai dati del gatto. una volta salvato rimando alla pagina
	 * galleria
	 */
	@PostMapping("/aggiornaGatto")
	public String aggiornaGatto(@ModelAttribute("gatto") Gatto gatto) {
		gattoService.salvaGatto(gatto);
		return "redirect:/galleria";
	}

	/**
	 * gestisce una richiesta GET: /admin/eliminaGatto ed elimina un gatto che viene
	 * ricercato tramite id
	 * 
	 * @throw RuntimeException se il gatto da cancellare non esiste
	 */
	@GetMapping("/eliminaGatto")
	public String eliminaGatto(@RequestParam("gattoId") Long gattoId) {
		gattoService.eliminaGatto(gattoId);
		return "redirect:/galleria";
	}

	/**
	 * gestisce una richiesta GET: /admin/elencoAdozioni ed elenca tutte le
	 * richieste di adozione che arrivano ai volontari
	 * 
	 * @return elencoAdozioni.html pagina che visualizza tutte le richieste di
	 *         adozione
	 * TODO
	 */
	@GetMapping("/elencoAdozioni")
	public String elencaAdozioni(Model model) {
		model.addAttribute("tutteLeAdozioni", adozioneService.tutteLeAdozioni());
		return "elencoAdozioni";
	}
	
	/*
     * Area volontari: accetta una richiesta di adozione */
    @PostMapping("/accettaAdozione")
    public String accettaAdozione(@RequestParam("adozioneId") Long adozioneId) {
    	Adozione adozione = adozioneService.getAdozioneById(adozioneId);
    	
    	//cambiamo lo stato della pratica
    	if(adozione != null) {
    		adozione.setStato("ACCETTATA");
    		adozioneService.salvaAdozione(adozione);
    		
    		//segno il gatto come adottato in automatico
    		Gatto gatto = adozione.getGatto();
    		gatto.setAdottato(true);
    		gattoService.salvaGatto(gatto);
    	}
    	return "redirect:/admin/elencoAdozioni";
    }
    
    /*
     * Area volontari: rifiuta adozione*/
    @PostMapping("/rifiutaAdozione")
    public String rifiutaAdozione(@RequestParam("adozioneId") Long adozioneId) {
    	Adozione adozione = adozioneService.getAdozioneById(adozioneId);
    	
    	//cambiamo lo stato della pratica
    	if(adozione != null) {
    		adozione.setStato("RIFIUTATA");
    		adozioneService.salvaAdozione(adozione);
    		
    	}
    	return "redirect:/admin/elencoAdozioni";
    
    }
    
}