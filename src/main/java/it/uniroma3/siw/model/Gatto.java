package it.uniroma3.siw.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "gatto")
public class Gatto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "eta")
	private Integer eta;
	
	@Column(name = "colore")
	private String colore;
	
	@Column(name = "razza")
	private String razza;
	
	@Column(name = "adottato")
	private Boolean adottato;
	
	@Column(name = "fiv")
	private Boolean fiv = false;
	
	@Column(name = "felv")
	private Boolean felv = false;
	
	@Column(name = "vaccini")
	private String vaccini;
	
	@Column(name = "problematiche")
	private String problematiche;
	 

	/**
	 * @return the fiv
	 */
	public Boolean getFiv() {
		return fiv;
	}

	/**
	 * @param fiv the fiv to set
	 */
	public void setFiv(Boolean fiv) {
		this.fiv = fiv;
	}

	/**
	 * @return the felv
	 */
	public Boolean getFelv() {
		return felv;
	}

	/**
	 * @param felv the felv to set
	 */
	public void setFelv(Boolean felv) {
		this.felv = felv;
	}

	/**
	 * @return the vaccini
	 */
	public String getVaccini() {
		return vaccini;
	}

	/**
	 * @param vaccini the vaccini to set
	 */
	public void setVaccini(String vaccini) {
		this.vaccini = vaccini;
	}

	/**
	 * @return the problematiche
	 */
	public String getProblematiche() {
		return problematiche;
	}

	/**
	 * @param problematiche the problematiche to set
	 */
	public void setProblematiche(String problematiche) {
		this.problematiche = problematiche;
	}

	// Diciamo: l'adozione è gestita dalla variabile "gatto" dall'altra parte,
	// ma se io (Gatto) vengo eliminato, distruggi in automatico anche l'adozione!
	@OneToOne(mappedBy = "gatto", cascade = CascadeType.REMOVE)
	private Adozione adozione;

	public Adozione getAdozione() {
		return adozione;
	}

	public void setAdozione(Adozione adozione) {
		this.adozione = adozione;
	}

	public Gatto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getEta() {
		return eta;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getRazza() {
		return razza;
	}

	public void setRazza(String razza) {
		this.razza = razza;
	}

	public Boolean getAdottato() {
		return adottato;
	}
	
	public void setAdottato(Boolean adottato) {
		this.adottato = adottato;
	}

	/**
	 * Metodo che verifica se il gatto è stato adottato o meno. 
	 * Restituisce una stringa
	 * @return gattino ha già trovato casa se è stato adottato
	 * */
	public String isAdottato() {
		if (getAdottato() != null && getAdottato()) {
			return "Il gattino ha già trovato casa";
		}

		return "Non è stato ancora adottato";
	}
	
	/**
	 * Metodo che verifica se il gatto è positivo o negativo alla fiv
	 * @return positivo se ha già avuto la fiv
	 * */
	
	public String getFivTesto() {
		if(this.fiv != null && this.fiv) {
			return "Positivo";
		}
		return "Negativo";
	}

	/**
	 * Metodo che verifica se il gatto è positivo o negativo alla felv
	 * @return positivo se ha già avuto la felv
	 * */
	public String getFelvTesto() {
		if(this.felv != null && this.felv) {
			return "Positivo";
		}
		return "Negativo";
	}
	
	
}
