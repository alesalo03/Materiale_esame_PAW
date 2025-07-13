package it.unibo.paw.hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Candidato implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int canId;
	private String matricola;
	private String nome;
	private String cognome;

	private Set<Concorso> concorsi = new HashSet<Concorso>();
	

	public Candidato() {}

	public int getCanId() {
		return canId;
	}

	public void setCanId(int canId) {
		this.canId = canId;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
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

	public Set<Concorso> getConcorsi() {
		return concorsi;
	}

	public void setConcorsi(Set<Concorso> concorsi) {
		this.concorsi = concorsi;
	}

	@Override
	public int hashCode() {
		return Objects.hash(canId, cognome, concorsi, matricola, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidato other = (Candidato) obj;
		return canId == other.canId && Objects.equals(cognome, other.cognome)
				&& Objects.equals(concorsi, other.concorsi) && matricola == other.matricola
				&& Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Candidato [canId=" + canId + ", matricola=" + matricola + ", nome=" + nome + ", cognome=" + cognome
				+ ", concorsi=" + concorsi + "]";
	}




}
