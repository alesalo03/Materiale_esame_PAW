package it.unibo.paw.dao;


import it.unibo.paw.dao.db2.Db2DAOFactory;

public abstract class DAOFactory {

	public static final int DB2 = 0;
	public static final int HSQLDB = 1;
	public static final int MYSQL = 2;
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch ( whichFactory ) {
		case DB2:
			return new Db2DAOFactory();
		default:
			return null;
		}
	}
	
	
	public abstract StadioDAO getStadioDAO();

	public abstract PartitaDAO getPartitaDAO();
	
}
