package it.unibo.paw.dao;

import java.util.List;
import java.util.Map;

public interface SquadraGiocatoreMappingDAO {
	
		// --- CRUD -------------
		public void create(int ids, int idg);

		public boolean delete(int idSquadra, int idGiocatore);		
		// ----------------------------------
		public List<String[]> allenatoriPerGiocatori();
		// ----------------------------------		
		public boolean createTable();

		public boolean dropTable();
}
