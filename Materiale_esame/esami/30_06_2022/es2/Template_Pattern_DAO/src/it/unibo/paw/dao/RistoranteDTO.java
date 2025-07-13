package it.unibo.paw.dao;

import java.io.Serializable;
import java.util.List;

public class RistoranteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String nomeRistorante;
	private String indirizzo;
	private int rating;
	private List<PiattoDTO> piatti;

	private boolean alreadyLoaded;

	public RistoranteDTO() {
		alreadyLoaded = false;
	}

	//---------GETTERS E SETTERS-------------------
	djdj
	
	
	public boolean isAlreadyLoaded() {
		return this.alreadyLoaded;
	}

	public void isAlreadyLoaded(boolean loaded) {
		this.alreadyLoaded = loaded;
	}
	
	//-----------EQUALS E TOSTRING-----------
	hdjdjk

}
