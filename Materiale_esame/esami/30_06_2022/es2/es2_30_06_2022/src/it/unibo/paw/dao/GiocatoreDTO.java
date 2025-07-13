package it.unibo.paw.dao;

import java.io.Serializable;
import java.util.Objects;

public class GiocatoreDTO implements Serializable{

	private int id;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private int eta;

	private static final long serialVersionUID = 1L;
	

	public GiocatoreDTO() {

	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale, cognome, eta, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GiocatoreDTO other = (GiocatoreDTO) obj;
		return Objects.equals(codiceFiscale, other.codiceFiscale) && Objects.equals(cognome, other.cognome)
				&& eta == other.eta && id == other.id && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "GiocatoreDTO [codiceFiscale=" + codiceFiscale + ", nome=" + nome + ", cognome=" + cognome + ", eta="
				+ eta + "]";
	}
	
	
}
