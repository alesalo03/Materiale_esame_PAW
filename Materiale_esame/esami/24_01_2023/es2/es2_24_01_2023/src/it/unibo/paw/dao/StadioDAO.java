package it.unibo.paw.dao;

import java.util.List;
import java.util.Map;

public interface StadioDAO {
	
	// --- CRUD -------------
		public void create(StadioDTO stadio);

		public StadioDTO read(int codice);

		public boolean update(StadioDTO stadio);

		public boolean delete(int codice);
		// ----------------------------------
		public Map<String, Integer> getPartiteFromStadio(String nome);
	
		// ----------------------------------	
		public boolean createTable();

		public boolean dropTable();
	
}
