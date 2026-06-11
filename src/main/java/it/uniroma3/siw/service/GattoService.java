package it.uniroma3.siw.service;

import java.util.List;

import it.uniroma3.siw.model.Gatto;

public interface GattoService {

	/**
	 * metodo che mi permette di salvare un gatto tramite la repository
	 * 
	 * @param oggetto gatto da salvare
	 */
	void salvaGatto(Gatto gatto);

	/**
	 * metodo che mi permette di prendere l'oggetto gatto tramite l'id
	 * 
	 * @param id del gatto
	 * @return oggetto gatto cercato
	 */
	Gatto getGattoById(Long id);

	/**
	 * metodo che mi permette di stampare la lista di tutti i gatti
	 * 
	 * @return lista gatti
	 */
	List<Gatto> tuttiIGatti();

	void eliminaGatto(Gatto gatto);

	void eliminaGatto(Long id);

}