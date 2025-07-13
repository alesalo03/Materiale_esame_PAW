package it.unibo.paw.dao;

import java.util.List;

public interface SquadraDAO {
	
	// --- CRUD -------------
		public void create(SquadraDTO squadra);

		public SquadraDTO read(int id);

		public boolean update(SquadraDTO squadra);

		public boolean delete(int id);
		// ----------------------------------
		//public List<SquadraDTO> getResturantByCity(String citta);
		
		//public List<SquadraDTO> getRatedResturant(int stars);
		// ----------------------------------	
		public boolean createTable();

		public boolean dropTable();
	
}
