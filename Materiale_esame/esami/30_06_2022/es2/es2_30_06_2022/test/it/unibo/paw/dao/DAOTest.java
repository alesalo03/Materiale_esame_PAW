package it.unibo.paw.dao;

import java.util.List;
import java.io.*;

public class DAOTest {

	static PrintWriter pw = null;

	public static final int DAO = DAOFactory.DB2;

	public static void main(String[] args) {
		DAOFactory daoFactoryInstance = DAOFactory.getDAOFactory(DAO);
		
		SquadraGiocatoreMappingDAO mappingDAO = daoFactoryInstance.getSquadraGiocatoreMappingDAO();
		mappingDAO.dropTable();
		
		// Squadre
		
		SquadraDAO squadraDAO = daoFactoryInstance.getSquadraDAO();
		squadraDAO.dropTable();
		squadraDAO.createTable();

		SquadraDTO s1 = new SquadraDTO();
		s1.setNome("Squadra Blu");
		s1.setTorneo("Torneo 1");
		s1.setAllenatore("Allenatore 1");
		System.out.println(s1.toString());
		squadraDAO.create(s1);

		SquadraDTO s2 = new SquadraDTO();
		s2.setNome("Squadra Rossa");
		s2.setTorneo("Torneo 2");
		s2.setAllenatore("Allenatore 2");
		System.out.println(s2.toString());
		squadraDAO.create(s2);

		// Giocatori

		GiocatoreDAO giocatoreDAO = daoFactoryInstance.getGiocatoreDAO();
		giocatoreDAO.dropTable();
		giocatoreDAO.createTable();

		GiocatoreDTO g1 = new GiocatoreDTO();
		g1.setCodiceFiscale("cscbhjdnkc");
		g1.setNome("Alessandro");
		g1.setCognome("Salomoni");
		g1.setEta(22);
		giocatoreDAO.create(g1);

		GiocatoreDTO g2 = new GiocatoreDTO();
		g2.setCodiceFiscale("cschjgngdnkc");
		g2.setNome("Angelo");
		g2.setCognome("Jin");
		g2.setEta(22);
		giocatoreDAO.create(g2);

		// SquadraGiocatoreMapping

		mappingDAO.createTable();

		mappingDAO.create(s1.getId(), g1.getId());

		mappingDAO.create(s1.getId(), g2.getId());

		mappingDAO.create(s2.getId(), g1.getId());

		try {
			pw = new PrintWriter(new FileWriter("C:/Users/Alessandro/Documents/UNI/PAW/Materiale_esame/esami/30_06_2022/es2/Pallacanestro.txt"));

			String outPut = "RISULATI:\n";
			for (String[] s : mappingDAO.allenatoriPerGiocatori()) {
				outPut += s[0] + " " + s[1] + "\n";
			}
			pw.println(outPut);
			System.out.println(outPut);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
