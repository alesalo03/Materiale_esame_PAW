package it.unibo.paw.hibernate.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = initHibernateUtil();

	private static final String CREATE_CANDIDATI = "CREATE TABLE candidati(" +
			"	canId INT NOT NULL PRIMARY KEY," +
			"	matricola varchar(10) NOT NULL UNIQUE," +
			"	nome varchar(50) NOT NULL," +
			"	cognome varchar(50) NOT NULL" +
			")";
	private static final String CREATE_CONCORSI = "CREATE TABLE concorsi(" +
			"	conId INT NOT NULL PRIMARY KEY," +
			"	codiceConcorso int NOT NULL UNIQUE," +
			"	classeConcorso varchar(255) NOT NULL, " +
			"	descrizione varchar(255) NOT NULL" +
			")";

	private static final String CREATE_CONCORSO_CANDIDATI = "CREATE TABLE concorsi_candidati(" +
			"	canId INT NOT NULL," +
			"	conId INT NOT NULL," +
			"	PRIMARY KEY(canId, conId)," +
			"	FOREIGN KEY (canId) REFERENCES candidati(canId)," +
			"	FOREIGN KEY (conId) REFERENCES concorsi(conId)" +
			")";
	private static final String CREATE_COMMISSARI = "CREATE TABLE commissari(" +
			"	comID INT NOT NULL PRIMARY KEY," +
			"	matricola varchar(10) NOT NULL UNIQUE," +
			"	nome varchar(50) NOT NULL," +
			"	cognome varchar(50) NOT NULL," +
			"	conId INT NOT NULL," +
			"	FOREIGN KEY (conId) REFERENCES concorsi(conId)" +
			")";

	private static SessionFactory initHibernateUtil() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (HibernateException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

	public static void dropAndCreateTables() {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
					
			try {
				session.createSQLQuery("DROP TABLE commissari").executeUpdate();
				session.createSQLQuery("DROP TABLE concorsi_candidati").executeUpdate();
				session.createSQLQuery("DROP TABLE concorsi").executeUpdate();
				session.createSQLQuery("DROP TABLE candidati").executeUpdate();
			} catch (HibernateException e) {
				System.out.println("dropTable(): failed to drop tables " + e.getMessage());
			}
			session.createSQLQuery(CREATE_CANDIDATI).executeUpdate();
			session.createSQLQuery(CREATE_CONCORSI).executeUpdate();
			session.createSQLQuery(CREATE_CONCORSO_CANDIDATI).executeUpdate();
			session.createSQLQuery(CREATE_COMMISSARI).executeUpdate();
			
			tx.commit();
		} finally {
			session.close();
		}
	}
}
