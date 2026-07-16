package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Adozione;
import it.uniroma3.siw.model.Gatto;
import it.uniroma3.siw.service.AdozioneService;
import it.uniroma3.siw.service.GattoService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private GattoService gattoService;

	@Autowired
	private AdozioneService adozioneService;

	@GetMapping("/formGatto")
	public String mostraFormAggiungiGatto(Model model) {
		model.addAttribute("gatto", new Gatto());
		return "formGatto";
	}

	@GetMapping("/gestisciGatto")
	public String mostraFormGestione(@RequestParam Long gattoId, Model model) {
		model.addAttribute("gatto", gattoService.getGattoById(gattoId));
		return "gestisciGatto";
	}

	/*
	 * Aggiorna un gatto esistente. Se non viene caricata una nuova foto,
	 * mantiene quella già presente nel database.
	 */
	@PostMapping("/aggiornaGatto")
	public String aggiornaGatto(@ModelAttribute("gatto") Gatto gatto,
			@RequestParam(value = "fileFoto", required = false) MultipartFile fileFoto) {

		if (fileFoto != null && !fileFoto.isEmpty()) {
			impostaFoto(gatto, fileFoto);
		} else {
			Gatto gattoVecchio = gattoService.getGattoById(gatto.getId());
			if (gattoVecchio != null) {
				gatto.setFoto(gattoVecchio.getFoto());
			}
		}

		gattoService.salvaGatto(gatto);
		return "redirect:/galleria";
	}

	@GetMapping("/eliminaGatto")
	public String eliminaGatto(@RequestParam("gattoId") Long gattoId) {
		gattoService.eliminaGatto(gattoId);
		return "redirect:/galleria";
	}

	@GetMapping("/elencoAdozioni")
	public String elencaAdozioni(Model model) {
		model.addAttribute("tutteLeAdozioni", adozioneService.tutteLeAdozioni());
		return "elencoAdozioni";
	}

	@PostMapping("/accettaAdozione")
	public String accettaAdozione(@RequestParam("adozioneId") Long adozioneId) {
		Adozione adozione = adozioneService.getAdozioneById(adozioneId);
		if (adozione != null) {
			adozione.setStato("ACCETTATA");
			adozioneService.salvaAdozione(adozione);

			Gatto gatto = adozione.getGatto();
			gatto.setAdottato(true);
			gattoService.salvaGatto(gatto);
		}
		return "redirect:/admin/elencoAdozioni";
	}

	@PostMapping("/rifiutaAdozione")
	public String rifiutaAdozione(@RequestParam("adozioneId") Long adozioneId) {
		Adozione adozione = adozioneService.getAdozioneById(adozioneId);
		if (adozione != null) {
			adozione.setStato("RIFIUTATA");
			adozioneService.salvaAdozione(adozione);
		}
		return "redirect:/admin/elencoAdozioni";
	}

	/*
	 * Crea un nuovo gatto con foto obbligatoria.
	 */
	@PostMapping("/gatto")
	public String salvaGattoInDatabase(@ModelAttribute("gatto") Gatto gatto,
			@RequestParam("fileFoto") MultipartFile fileFoto) {

		impostaFoto(gatto, fileFoto);
		gattoService.salvaGatto(gatto);
		return "redirect:/galleria";
	}

	/*
	 * Metodo di supporto: converte il file caricato in byte[] e lo assegna al
	 * gatto.
	 * Se la lettura fallisce, lancia una RuntimeException invece di nasconderla:
	 * Spring gestirà l'errore mostrando la pagina di errore di default
	 * (whitelabel),
	 * o un @ExceptionHandler dedicato se ne aggiungerai uno in futuro.
	 */
	private void impostaFoto(Gatto gatto, MultipartFile fileFoto) {
		try {
			gatto.setFoto(fileFoto.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Errore durante la lettura della foto per il gatto con id " + gatto.getId(), e);
		}
	}
}