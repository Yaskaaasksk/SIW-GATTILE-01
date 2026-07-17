package it.uniroma3.siw.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(auth -> auth
                                                // Pagine che tutti possono vedere senza fare il login (aggiunti i
                                                // percorsi
                                                // delle adozioni!)
                                                .requestMatchers("/", "/galleria", "/registrazione", "/login",
                                                                "/adotta", "/salvaAdozione",
                                                                "/controllaStato", "/cercaLeMieRichieste")
                                                .permitAll()

                                                .requestMatchers("/preferiti/**").authenticated()

                                                .requestMatchers("/admin/**").hasAnyRole("ADMIN", "VOLONTARIO")

                                                .anyRequest().permitAll())
                                .formLogin(form -> form
                                                .loginPage("/login") // La pagina che abbiamo creato
                                                .usernameParameter("email") // FONDAMENTALE! Diciamo a Spring di usare
                                                                            // l'email
                                                .defaultSuccessUrl("/galleria", true)// Dove andare se il login ha
                                                                                     // successo
                                                .failureUrl("/login?errore=true") // Dove andare se si sbaglia password
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout") // Il modulo in POST per uscire
                                                .logoutSuccessUrl("/galleria") // Dove andare dopo essere usciti
                                                .permitAll());

                return http.build();
        }

        // Il nostro criptatore per le password vere nel database
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}