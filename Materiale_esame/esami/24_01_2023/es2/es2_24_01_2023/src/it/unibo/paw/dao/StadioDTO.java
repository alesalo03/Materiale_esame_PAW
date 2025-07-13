package it.unibo.paw.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class StadioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int codice;
	private String nome;
	private String citta;
	private List<PartitaDTO> partite;

	private boolean alreadyLoaded;

	public StadioDTO() {
		alreadyLoaded = false;
	}


	public int getCodice() {
		return codice;
	}



	public void setCodice(int codice) {
		this.codice = codice;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCitta() {
		return citta;
	}



	public void setCitta(String citta) {
		this.citta = citta;
	}



	public List<PartitaDTO> getPartite() {
		return partite;
	}



	public void setPartite(List<PartitaDTO> partite) {
		this.partite = partite;
	}


	public boolean isAlreadyLoaded() {
		return this.alreadyLoaded;
	}

	public void isAlreadyLoaded(boolean loaded) {
		this.alreadyLoaded = loaded;
	}


	@Override
	public int hashCode() {
		return Objects.hash(citta, codice, nome);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StadioDTO other = (StadioDTO) obj;
		return Objects.equals(citta, other.citta) && codice == other.codice && Objects.equals(nome, other.nome);
	}


	@Override
	public String toString() {
		return "StadioDTO [codice=" + codice + ", nome=" + nome + ", citta=" + citta + "]";
	}
	
	
}
