package it.unibo.paw.dao;

import java.util.List;
import java.io.*;

public class DAOTest {

	static PrintWriter pw = null;

	public static void main(String[] args) {
		DAOFactory daoFactoryInstance = DAOFactory.getDAOFactory();
		
		RistorantePiattoMappingDAO mappingDAO = daoFactoryInstance.getRistorantePiattoMappingDAO();
		mappingDAO.dropTable();
		
		// Ristoranti
		
		RistoranteDAO ristoranteDAO = daoFactoryInstance.getRistoranteDAO();
		ristoranteDAO.dropTable();
		ristoranteDAO.createTable();

		RistoranteDTO r1 = new RistoranteDTO();
		r1.setNomeRistorante("E' cucina");
		r1.setIndirizzo("Via G. Leopardi, 24, Bologna");
		r1.setRating(5);
		ristoranteDAO.create(r1);

		RistoranteDTO r2 = new RistoranteDTO();
		r2.setNomeRistorante("Camst");
		r2.setIndirizzo("Viale Risorgimento, 2, Bologna");
		r2.setRating(2);
		ristoranteDAO.create(r2);
		System.out.println("Insert new ristorante with id: "+r2.getId());

		RistoranteDTO r3 = new RistoranteDTO();
		r3.setNomeRistorante("Ca' Pelletti");
		r3.setIndirizzo("Viale del Brodo, 60, Modena");
		r3.setRating(4);
		ristoranteDAO.create(r3);
		System.out.println("Insert new ristorante with id: "+r3.getId());

		for (RistoranteDTO br : ristoranteDAO.getResturantByCity("Bologna")) {
			System.out.println(br);
		}
		System.out.println();

		// Piatti

		PiattoDAO piattoDAO = daoFactoryInstance.getPiattoDAO();
		piattoDAO.dropTable();
		piattoDAO.createTable();

		PiattoDTO p1 = new PiattoDTO();
		p1.setNomePiatto("Lasagne commestibili");
		p1.setTipo("primo");
		piattoDAO.create(p1);
		System.out.println("Insert new piatto with id: "+p1.getId());

		PiattoDTO p2 = new PiattoDTO();
		p2.setNomePiatto("Passatelli Gourmet");
		p2.setTipo("primo");
		piattoDAO.create(p2);
		System.out.println("Insert new piatto with id: "+p2.getId());

		PiattoDTO p3 = new PiattoDTO();
		p3.setNomePiatto("Seppie e Piselli");
		p3.setTipo("secondo");
		piattoDAO.create(p3);
		System.out.println("Insert new piatto with id: "+p3.getId());

		PiattoDTO p4 = new PiattoDTO();
		p4.setNomePiatto("Tortellini in Brodo");
		p4.setTipo("primo");
		piattoDAO.create(p4);
		System.out.println("Insert new piatto with id: "+p4.getId());

		PiattoDTO p = piattoDAO.findByName("Seppie e Piselli");
		System.out.println(p);
		System.out.println();

		// RistorantePiattoMapping

		mappingDAO.createTable();

		mappingDAO.create(r1.getId(), p1.getId());

		mappingDAO.create(r1.getId(), p2.getId());

		mappingDAO.create(r3.getId(), p1.getId());

		mappingDAO.create(r2.getId(), p3.getId());

		mappingDAO.create(r3.getId(), p3.getId());

		mappingDAO.create(r3.getId(), p4.getId());

		mappingDAO.create(r1.getId(), p4.getId());

		try {
			pw = new PrintWriter(new FileWriter("ristorante.txt"));

			// Primi piatti dei ristoranti di Bologna
			String outPut = ListPrimiPiattiDeiRistorantiDiBologna(ristoranteDAO, mappingDAO);
			pw.println(outPut);
			System.out.println(outPut);
			System.out.println();
			// Numero di ristoranti con almeno 4 stelle che preparano Seppie e Piselli
			outPut = CountRatedResturantsWithSeppieEPiselli(ristoranteDAO, mappingDAO);
			pw.append("\n" + outPut);
			System.out.println(outPut);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();
		System.out.println();
		
		System.out.println("TEST ELIMINAZIONE");
		if(ristoranteDAO.delete(r2.getId()))
			System.out.println("Eliminazione con successo");
	}

}
