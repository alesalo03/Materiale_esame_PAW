package it.unibo.paw.hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Concorso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int conId;
	private int codiceConcorso;
	private String classeConcorso;
	private String  descrizione;
	
	private Set<Candidato> candidati = new HashSet<Candidato>();
	private Set<Commissario> commissari = new HashSet<Commissario>();
	
	public Concorso() {}

	public int getConId() {
		return conId;
	}

	public void setConId(int conId) {
		this.conId = conId;
	}

	public int getCodiceConcorso() {
		return codiceConcorso;
	}

	public void setCodiceConcorso(int codiceConcorso) {
		this.codiceConcorso = codiceConcorso;
	}

	public String getClasseConcorso() {
		return classeConcorso;
	}

	public void setClasseConcorso(String classeConcorso) {
		this.classeConcorso = classeConcorso;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<Candidato> getCandidati() {
		return candidati;
	}

	public void setCandidati(Set<Candidato> candidati) {
		this.candidati = candidati;
	}

	public Set<Commissario> getCommissari() {
		return commissari;
	}

	public void setCommissari(Set<Commissario> commissari) {
		this.commissari = commissari;
	}

	@Override
	public int hashCode() {
		return Objects.hash(candidati, classeConcorso, codiceConcorso, conId, descrizione);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Concorso other = (Concorso) obj;
		return Objects.equals(candidati, other.candidati) && Objects.equals(classeConcorso, other.classeConcorso)
				&& codiceConcorso == other.codiceConcorso && conId == other.conId
				&& Objects.equals(descrizione, other.descrizione);
	}

	@Override
	public String toString() {
		return "Concorso [conId=" + conId + ", codiceConcorso=" + codiceConcorso + ", classeConcorso=" + classeConcorso
				+ ", descrizione=" + descrizione + ", candidati=" + candidati + "]";
	}
	
	
	
}
