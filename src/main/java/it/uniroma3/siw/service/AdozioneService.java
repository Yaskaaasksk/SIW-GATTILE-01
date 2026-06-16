package it.uniroma3.siw.service;

import java.util.List;
import it.uniroma3.siw.model.Adozione;

public interface AdozioneService {
    
    // Salva una nuova richiesta nel database
    public void salvaAdozione(Adozione adozione);
    
    // Recupera tutte le richieste per la bacheca dei volontari
    public List<Adozione> tutteLeAdozioni();
    
 // Trova una specifica adozione tramite il suo ID
    public Adozione getAdozioneById(Long id);
    
 // Trova tutte le richieste fatte da una specifica email
    public List<Adozione> trovaAdozioniPerEmail(String email);
}