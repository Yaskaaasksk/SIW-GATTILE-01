package it.uniroma3.siw.model;

//import it.uniroma3.siw.model.Gatto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GattoRepository extends JpaRepository<Gatto, Long>{
	
}
