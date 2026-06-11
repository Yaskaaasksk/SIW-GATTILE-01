package it.uniroma3.siw.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // 1. LE PAGINE PUBBLICHE (I tuoi lettori)
            	.requestMatchers("/","/login", "/registrazione", "/richiestaAdozione","/admin/**", "/error", "/user/**").permitAll()                
                // 2. LE PAGINE SEGRETE (La tua cucina privata)
                .requestMatchers("").authenticated())
            .formLogin(login -> login
                // Usa il login base di Spring e poi ti manda alla pagina di gestione
                .defaultSuccessUrl("/galleria", true) 
                .permitAll()
            )
            .logout(logout -> logout
                    // Comando super semplice che funziona grazie al CSRF disabilitato!
                    .logoutUrl("/logout") 
                    .logoutSuccessUrl("/") // Dopo l'uscita, torna alla Homepage pubblica
                    .permitAll()
                )
            // Disabilitiamo temporaneamente una protezione per farti testare i form tranquillamente
            .csrf(csrf -> csrf.disable()); 
        
        return http.build();
    }

    // IL TUO ACCOUNT SEGRETO
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails utenteAdmin = User.withDefaultPasswordEncoder()
            .username("angi")         // Il tuo nome utente
            .password("buzzi")     // La tua password (puoi cambiarla!)
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(utenteAdmin);
    }
}