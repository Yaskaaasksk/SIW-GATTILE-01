package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Gatto;
import it.uniroma3.siw.model.GattoRepository;

@Service
public class GattoServiceImpl {
	
	@Autowired
	private GattoRepository gattoRepository;
	
	
	/**
	 * metodo che mi permette di salvare un gatto tramite la repository
	 * @param oggetto gatto da salvare
	 * */
	public void salvaGatto(Gatto gatto) {
		gattoRepository.save(gatto);
	}
	
	/**
	 * metodo che mi permette di prendere l'oggetto gatto tramite l'id
	 * @param id del gatto
	 * @return oggetto gatto cercato
	 * */
	public Gatto getGattoById(Long id) {
		Optional<Gatto> gatto = gattoRepository.findById(id);//uso optional perchè fa il controllo in automatico se esiste l'oggetto o no
		return gatto.get();	
	}
	
	/**
	 * metodo che mi permette di stampare la lista di tutti i gatti
	 * @return lista gatti*/
	public List<Gatto> tuttiIGatti(){
		return gattoRepository.findAll();
	}
	
	public void eliminaGatto(Gatto gatto) {
		gattoRepository.delete(gatto);
	}
	
	public void eliminaGatto(Long id) {
		
		
		gattoRepository.deleteById(id);
	}
}