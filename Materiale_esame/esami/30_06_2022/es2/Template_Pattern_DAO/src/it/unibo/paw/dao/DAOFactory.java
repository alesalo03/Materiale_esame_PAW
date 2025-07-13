package it.unibo.paw.dao;


import it.unibo.paw.dao.db2.Db2DAOFactory;

public abstract class DAOFactory {
	
	public static DAOFactory getDAOFactory() {
		return new Db2DAOFactory();
	}

	public abstract RistoranteDAO getRistoranteDAO();

	public abstract PiattoDAO getPiattoDAO();
	
	public abstract RistorantePiattoMappingDAO getRistorantePiattoMappingDAO();
	
}
