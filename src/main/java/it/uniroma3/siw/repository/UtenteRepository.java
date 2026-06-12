package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long>{

	Utente findByEmail(String email);
	Utente findByCognome(String cognome);
	
}
