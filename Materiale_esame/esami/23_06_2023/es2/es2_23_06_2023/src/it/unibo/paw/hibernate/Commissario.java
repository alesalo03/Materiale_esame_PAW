package it.unibo.paw.hibernate;

import java.io.Serializable;
import java.util.Objects;

public class Commissario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int comId;
	private String matricola;
	private String nome;
	private String cognome;

	private Concorso concorso;

	public Commissario() {
	}

	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
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

	public Concorso getConcorso() {
		return concorso;
	}

	public void setConcorso(Concorso concorso) {
		this.concorso = concorso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, comId, concorso, matricola, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commissario other = (Commissario) obj;
		return Objects.equals(cognome, other.cognome) && comId == other.comId
				&& Objects.equals(concorso, other.concorso) && matricola == other.matricola
				&& Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Commissario [comId=" + comId + ", matricola=" + matricola + ", nome=" + nome + ", cognome=" + cognome
				+ ", concorso=" + concorso + "]";
	}

	

}
