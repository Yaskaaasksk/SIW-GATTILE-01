package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Adozione;
import it.uniroma3.siw.model.GattoRepository;
import it.uniroma3.siw.repository.AdozioneRepository;

@Controller
public class AdozioneController {

	@Autowired
	private AdozioneRepository adozioneRepository;
	
	@Autowired
	private GattoRepository gattoRepository;
	
	@PostMapping("/richiestaAdozione")
	public String creaRichiestaAdozione(@RequestParam long gattoId, @RequestParam String nomeUtente,
										@RequestParam String email) {
		
		var gatto = gattoRepository.findById(gattoId).orElse(null);
		
		Adozione nuovaAdozione = new Adozione();
		nuovaAdozione.setGatto(gatto);
		nuovaAdozione.setNomeUtente(nomeUtente);
		nuovaAdozione.setEmail(email);
		
		adozioneRepository.save(nuovaAdozione);
		
		return "redirect:/galleria";

	}
	
	@GetMapping("/adotta")
    public String mostraFormAdozione(@RequestParam Long gattoId, Model model) {
        model.addAttribute("gatto", gattoRepository.findById(gattoId).get());
        return "richiestaAdozione";
    }
	
	@GetMapping("/successoAdozione")
	public String mostraPaginaSuccesso() {
	    return "successoAdozione"; 
	}
	
	@GetMapping("/admin/elencoAdozioni")
	public String elencaAdozioni(Model model) {
		model.addAttribute("tutteLeAdozioni", adozioneRepository.findAll());
		return "elencoAdozioni";
	}
	
}
