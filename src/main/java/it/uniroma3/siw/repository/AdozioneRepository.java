package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.model.Adozione;

public interface AdozioneRepository extends CrudRepository<Adozione, Long> {
    
   public List<Adozione> findByEmailRichiedente(String email);
}
