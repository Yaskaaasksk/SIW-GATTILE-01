package it.uniroma3.siw.security;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        // 1. Cerchiamo l'utente nel database usando l'email inserita nel form di login
        Utente utente = utenteRepository.findByEmail(email);
        
        // Se l'email non esiste nel database, blocchiamo subito il tentativo
        if (utente == null) {
            throw new UsernameNotFoundException("Nessun utente trovato con l'email: " + email);
        }

        // 2. Traduciamo il nostro Utente nell'oggetto User richiesto da Spring Security.
        // Il metodo .roles() aggiungerà automaticamente il prefisso "ROLE_" (es. "ROLE_ADMIN")
        return User.builder()
                .username(utente.getEmail())     // Usiamo l'email come identificativo di login
                .password(utente.getPassword())  // La password già criptata nel database
                .roles(utente.getRuolo())        // Il ruolo letto dal DB (es: "ADMIN" o "VOLONTARIO")
                .build();
    }
}