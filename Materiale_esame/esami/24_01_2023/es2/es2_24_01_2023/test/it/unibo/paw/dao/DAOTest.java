package it.unibo.paw.dao;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class DAOTest {

	static PrintWriter pw = null;

	public static final int DAO = DAOFactory.DB2;

	public static void main(String[] args) {
		DAOFactory daoFactoryInstance = DAOFactory.getDAOFactory(DAO);

		// Partita

		PartitaDAO partitaDAO = daoFactoryInstance.getPartitaDAO();
		partitaDAO.dropTable();
		partitaDAO.createTable();

		PartitaDTO p1 = new PartitaDTO();
		p1.setCategoria("A");
		p1.setGirone("A");
		p1.setNomeSquadraCasa("Squadra1");
		p1.setNomeSquadraOspite("Squadra2");
		p1.setData("12/2/2022");

		PartitaDTO p2 = new PartitaDTO();
		p2.setCategoria("A");
		p2.setGirone("B");
		p2.setNomeSquadraCasa("Squadra3");
		p2.setNomeSquadraOspite("Squadra4");
		p2.setData("12/2/2022");
		
		PartitaDTO p3 = new PartitaDTO();
		p3.setCategoria("B");
		p3.setGirone("A");
		p3.setNomeSquadraCasa("Squadra5");
		p3.setNomeSquadraOspite("Squadra6");
		p3.setData("12/2/2022");
		
		PartitaDTO p4 = new PartitaDTO();
		p4.setCategoria("A");
		p4.setGirone("A");
		p4.setNomeSquadraCasa("Squadra7");
		p4.setNomeSquadraOspite("Squadra8");
		p4.setData("12/2/2022");
		
		PartitaDTO p5 = new PartitaDTO();
		p5.setCategoria("C");
		p5.setGirone("A");
		p5.setNomeSquadraCasa("Squadra9");
		p5.setNomeSquadraOspite("Squadra10");
		p5.setData("12/2/2022");
		
		// Stadio
		
		StadioDAO stadioDAO = daoFactoryInstance.getStadioDAO();
		stadioDAO.dropTable();
		stadioDAO.createTable();

		StadioDTO s1 = new StadioDTO();
		s1.setNome("Stadio1");
		s1.setCitta("Bologna");
		List<PartitaDTO> partiteStadio1 = new ArrayList<>();
		partiteStadio1.add(p1);
		partiteStadio1.add(p2);
		partiteStadio1.add(p3);
		s1.setPartite(partiteStadio1);
		stadioDAO.create(s1);
		
		System.out.println(s1.toString());

		StadioDTO s2 = new StadioDTO();
		s2.setNome("Stadio2");
		s2.setCitta("Roma");
		List<PartitaDTO> partiteStadio2 = new ArrayList<>();
		partiteStadio2.add(p4);
		partiteStadio2.add(p5);
		s2.setPartite(partiteStadio2);
		stadioDAO.create(s2);

		try {
			pw = new PrintWriter(new FileWriter("C:/Users/Alessandro/Documents/UNI/PAW/Materiale_esame_PAW/Materiale_esame/esami/24_01_2023/es2/Partite.txt"));

			// Primi piatti dei ristoranti di Bologna
			String outPut = "RISULTATI:\n" + stadioDAO.getPartiteFromStadio(s1.getNome()).toString();
			pw.println(outPut);
			System.out.println(outPut);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
