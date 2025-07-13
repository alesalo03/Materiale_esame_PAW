package it.unibo.paw.dao;

import java.io.Serializable;
import java.util.Objects;

public class PartitaDTO implements Serializable{

	private int codicePartita;
	private String categoria;
	private String girone;
	private String nomeSquadraCasa;
	private String nomeSquadraOspite;
	private String data;

	private static final long serialVersionUID = 1L;
	

	public PartitaDTO() {

	}

	

	public int getCodicePartita() {
		return codicePartita;
	}



	public void setCodicePartita(int codicePartita) {
		this.codicePartita = codicePartita;
	}



	public String getCategoria() {
		return categoria;
	}



	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	public String getGirone() {
		return girone;
	}



	public void setGirone(String girone) {
		this.girone = girone;
	}



	public String getNomeSquadraCasa() {
		return nomeSquadraCasa;
	}



	public void setNomeSquadraCasa(String nomeSquadraCasa) {
		this.nomeSquadraCasa = nomeSquadraCasa;
	}



	public String getNomeSquadraOspite() {
		return nomeSquadraOspite;
	}



	public void setNomeSquadraOspite(String nomeSquadraOspite) {
		this.nomeSquadraOspite = nomeSquadraOspite;
	}



	public String getData() {
		return data;
	}



	public void setData(String data) {
		this.data = data;
	}



	@Override
	public int hashCode() {
		return Objects.hash(codicePartita, categoria, data, girone, nomeSquadraCasa, nomeSquadraOspite);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartitaDTO other = (PartitaDTO) obj;
		return codicePartita == other.codicePartita && Objects.equals(categoria, other.categoria)
				&& Objects.equals(data, other.data) && Objects.equals(girone, other.girone)
				&& Objects.equals(nomeSquadraCasa, other.nomeSquadraCasa)
				&& Objects.equals(nomeSquadraOspite, other.nomeSquadraOspite);
	}



	@Override
	public String toString() {
		return "PartitaDTO [CodicePartita=" + codicePartita + ", categoria=" + categoria + ", girone=" + girone
				+ ", nomeSquadraCasa=" + nomeSquadraCasa + ", nomeSquadraOspite=" + nomeSquadraOspite + ", data=" + data
				+ "]";
	}




}
