package it.uniroma3.siw.repository;

//import it.uniroma3.siw.model.Gatto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Gatto;

@Repository
public interface GattoRepository extends JpaRepository<Gatto, Long>{
	
}
