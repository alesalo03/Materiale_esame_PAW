package it.unibo.paw.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SquadraDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String nome;
	private String torneo;
	private String allenatore;

	public SquadraDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTorneo() {
		return torneo;
	}

	public void setTorneo(String torneo) {
		this.torneo = torneo;
	}

	public String getAllenatore() {
		return allenatore;
	}

	public void setAllenatore(String allenatore) {
		this.allenatore = allenatore;
	}

	@Override
	public int hashCode() {
		return Objects.hash(allenatore, id, nome, torneo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SquadraDTO other = (SquadraDTO) obj;
		return Objects.equals(allenatore, other.allenatore) && id == other.id && Objects.equals(nome, other.nome)
				&& Objects.equals(torneo, other.torneo);
	}

	@Override
	public String toString() {
		return "SquadraDTO [nome=" + nome + ", torneo=" + torneo + ", allenatore=" + allenatore + "]";
	}

	

}
