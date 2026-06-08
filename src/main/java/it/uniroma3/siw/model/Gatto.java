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

	public void setAdottato(Boolean adottato) {
		this.adottato = adottato;
	}

}
