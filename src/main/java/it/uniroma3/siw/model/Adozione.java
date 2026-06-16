package it.uniroma3.siw.model;

import jakarta.persistence.*;

@Entity
public class Adozione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Chi fa la richiesta
    private String nomeRichiedente;
    private String cognomeRichiedente;
    private String emailRichiedente;
    
    @Column(length = 1000) // Spazio per un bel messaggio lungo
    private String motivazione;

    // Per ricordarci a che punto è la pratica
    private String stato = "IN ATTESA"; 

    // Il gatto che vogliono adottare!
    @ManyToOne
    private Gatto gatto;

    // --- GETTER E SETTER ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeRichiedente() { return nomeRichiedente; }
    public void setNomeRichiedente(String nomeRichiedente) { this.nomeRichiedente = nomeRichiedente; }

    public String getCognomeRichiedente() { return cognomeRichiedente; }
    public void setCognomeRichiedente(String cognomeRichiedente) { this.cognomeRichiedente = cognomeRichiedente; }

    public String getEmailRichiedente() { return emailRichiedente; }
    public void setEmailRichiedente(String emailRichiedente) { this.emailRichiedente = emailRichiedente; }

    public String getMotivazione() { return motivazione; }
    public void setMotivazione(String motivazione) { this.motivazione = motivazione; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }

    public Gatto getGatto() { return gatto; }
    public void setGatto(Gatto gatto) { this.gatto = gatto; }
}