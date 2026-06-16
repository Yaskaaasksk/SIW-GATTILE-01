package it.uniroma3.siw.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Adozione;
import it.uniroma3.siw.repository.AdozioneRepository;

@Service
public class AdozioneServiceImpl implements AdozioneService {

    @Autowired
    private AdozioneRepository adozioneRepository;

    @Override
    public void salvaAdozione(Adozione adozione) {
        adozioneRepository.save(adozione);
    }

    @Override
    public List<Adozione> tutteLeAdozioni() {
        // Il cast a (List<Adozione>) serve perché findAll() restituisce un Iterable
        return (List<Adozione>) adozioneRepository.findAll();
    }
    
    @Override
    public Adozione getAdozioneById(Long id) {
    	
    	return adozioneRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<Adozione> trovaAdozioniPerEmail(String email) {
        return adozioneRepository.findByEmailRichiedente(email);
    }
}