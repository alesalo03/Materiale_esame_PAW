package it.unibo.paw.hibernate;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import it.unibo.paw.hibernate.util.HibernateUtil;

public class HibernateTest {
	private static final String FIRSTQUERY_STR = "select con.classeConcorso, count(*) "
			+ "from  " + Commissario.class.getSimpleName() + " com "
			+ "join com.concorso con "
			+ "join com.concorso.candidati can " 
			+ "where com.matricola = :matricolaCommissario "
			+ "group by con.classeConcorso";

	private static final String SECONDQUERY_STR = "select can.nome, can.cognome "
			+ "from " + Candidato.class.getSimpleName() + " can "
			+ "join can.concorsi cc "
			+ "group by can.nome, can.cognome "
			+ "having count(distinct cc.conId) > 1";

	public static void main(String[] argv) {

		Session session = null;
		Transaction tx = null;

		try {
			HibernateUtil.dropAndCreateTables();
			
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			Concorso concorso1 = new Concorso();
			concorso1.setConId(1);
			concorso1.setCodiceConcorso(1);
			concorso1.setClasseConcorso("Classe1");
			concorso1.setDescrizione("Descrizione concorso 1");
			concorso1.setCommissari(new HashSet<Commissario>());
			session.persist(concorso1);
			
			Concorso concorso2 = new Concorso();
			concorso2.setConId(2);
			concorso2.setCodiceConcorso(2);
			concorso2.setClasseConcorso("Classe2");
			concorso2.setDescrizione("Descrizione concorso 2");
			concorso2.setCommissari(new HashSet<Commissario>());
			session.persist(concorso2);

			Commissario commissario1 = new Commissario();
			commissario1.setComId(1);
			commissario1.setMatricola("XYZ00");
			commissario1.setNome("Franco");
			commissario1.setCognome("Rossi");
			commissario1.setConcorso(concorso1);
			session.persist(commissario1);

			Commissario commissario2 = new Commissario();
			commissario2.setComId(2);
			commissario2.setMatricola("XYZ01");
			commissario2.setNome("Nicola");
			commissario2.setCognome("Villa");
			commissario2.setConcorso(concorso2);
			session.persist(commissario2);

			Set<Candidato> candidati1 = new HashSet<Candidato>();
			Set<Candidato> candidati2 = new HashSet<Candidato>();
			Set<Concorso> concorsi = new HashSet<Concorso>();

			Candidato candidato1 = new Candidato();
			candidato1.setCanId(1);
			candidato1.setMatricola("XYZ02");
			candidato1.setNome("Samuele");
			candidato1.setCognome("Capuano");
			session.persist(candidato1);

			candidati1.add(candidato1);
			candidati2.add(candidato1);
			
			concorso1.setCandidati(candidati1);
			session.saveOrUpdate(concorso1);

			Candidato candidato2 = new Candidato();
			candidato2.setCanId(2);
			candidato2.setMatricola("XYZ03");
			candidato2.setNome("Alessandro");
			candidato2.setCognome("Gallerani");
			session.persist(candidato2);

			candidati2.add(candidato2);

			concorso2.setCandidati(candidati2);
			session.saveOrUpdate(concorso2);

			tx.commit();
			session.close();

			session = HibernateUtil.getSessionFactory().openSession();

			String firstQueryResult = "";
			String secondQueryResult = "";

			//Approccio DB-Oriented
			Query firstQuery = session.createQuery(FIRSTQUERY_STR);
			firstQuery.setString("matricolaCommissario", "XYZ00");
			List<Object[]> firstQueryRecords = firstQuery.list();
			for (Object[] o : firstQueryRecords) {
				firstQueryResult += o[0] + " " + o[1] + "\n";
			}

			Query secondQuery = session.createQuery(SECONDQUERY_STR);
			List<Object[]> secondQueryRecords = secondQuery.list();
			for (Object[] arr : secondQueryRecords) {
				secondQueryResult += arr[0] + " " + arr[1] + "\n";
			}

			PrintWriter pw = new PrintWriter(new FileWriter("C:/Users/Alessandro/Documents/UNI/PAW/Materiale_esame/esami/23_06_2023/es2/Concorso.txt"));
			pw.println("First Query:\n" + firstQueryResult);
			pw.append("Second Query:\n" + secondQueryResult);
			pw.close();
		} catch (Exception e1) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			e1.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
