package it.unibo.paw.dao;

public interface PartitaDAO {
	
	// --- CRUD -------------
	public void create(PartitaDTO partita);

	public PartitaDTO read(int codicePartita);

	public boolean update(PartitaDTO partita);

	public boolean delete(int codicePartita);
	
	// ----------------------------------
	
	public boolean createTable();

	public boolean dropTable();
}
