package it.unibo.paw.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import it.unibo.paw.dao.IdBroker;
import it.unibo.paw.dao.GiocatoreDAO;
import it.unibo.paw.dao.GiocatoreDTO;

public class Db2GiocatoreDAO implements GiocatoreDAO {

	private static final String TABLE = "giocatori";

	private static final String ID = "id";
	private static final String CODICEFISCALE = "codiceFiscale";
	private static final String NOME = "nome";
	private static final String COGNOME = "cognome";
	private static final String ETA = "eta";

	private static final String insert = "INSERT " +
			"INTO " + TABLE + " ( " +
			ID + ", " + CODICEFISCALE + ", " + NOME + ", " + COGNOME + ", " + ETA + " " +
			") " +
			"VALUES (?,?,?,?,?) ";

	private static final String read = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";

	private static final String delete = "DELETE " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";

	private static final String update = "UPDATE " + TABLE + " " +
			"SET " +
			CODICEFISCALE + " = ?, " +
			NOME + " = ?, " +
			COGNOME + " = ?, " +
			ETA + " = ? " +
			"WHERE " + ID + " = ? ";
	// -------------------------------------------------------------------------------------

	// CREATE entrytable ( id INT NOT NULL PRIMARY KEY, ... );
	private static final String create = "CREATE " +
			"TABLE " + TABLE + " ( " +
			ID + " INT NOT NULL PRIMARY KEY, " +
			CODICEFISCALE + " VARCHAR(50) NOT NULL UNIQUE," +
			NOME + " VARCHAR(50) NOT NULL," +
			COGNOME + " VARCHAR(50) NOT NULL," +
			ETA + " INT NOT NULL" +
			") ";
	private static final String drop = "DROP " +
			"TABLE " + TABLE + " ";

	// === METODI DAO =========================================================================
	@Override
	public void create(GiocatoreDTO giocatore) {
		Connection conn = Db2DAOFactory.createConnection();
		
		if (giocatore == null) {
			System.out.println("create(): cannot insert a null entry");
			return;
		}
		try {
			IdBroker broker = new Db2IdBroker();
			giocatore.setId(broker.newId());
			
			PreparedStatement prep_stmt = conn.prepareStatement(insert);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, giocatore.getId());
			prep_stmt.setString(2, giocatore.getCodiceFiscale());
			prep_stmt.setString(3, giocatore.getNome());
			prep_stmt.setString(4, giocatore.getCognome());
			prep_stmt.setInt(5, giocatore.getEta());
			prep_stmt.executeUpdate();
			prep_stmt.close();
		} catch (Exception e) {
			System.out.println("create(): failed to insert entry: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}
	}

	@Override
	public GiocatoreDTO read(int id) {
		GiocatoreDTO result = null;
		if (id < 0) {
			System.out.println("read(): cannot read an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(read);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, id);
			ResultSet rs = prep_stmt.executeQuery();
			if (rs.next()) {
				GiocatoreDTO entry = new GiocatoreDTO();
				entry.setId(rs.getInt(ID));
				entry.setNome(rs.getString(NOME));
				entry.setCognome(rs.getString(COGNOME));
				entry.setCodiceFiscale(rs.getString(CODICEFISCALE));
				entry.setEta(rs.getInt(ETA));
				result = entry;
			}
			rs.close();
			prep_stmt.close();
		} catch (Exception e) {
			System.out.println("read(): failed to read entry: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public boolean update(GiocatoreDTO giocatore) {
		boolean result = false;
		if (giocatore == null) {
			System.out.println("update(): failed to update a null entry");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(update);
			prep_stmt.clearParameters();
			prep_stmt.setString(1, giocatore.getCodiceFiscale());
			prep_stmt.setString(2, giocatore.getNome());
			prep_stmt.setString(3, giocatore.getCognome());
			prep_stmt.setInt(4, giocatore.getEta());
			prep_stmt.setInt(5, giocatore.getId());
			prep_stmt.executeUpdate();
			result = true;
			prep_stmt.close();
		} catch (Exception e) {
			System.out.println("insert(): failed to update entry: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		if (id < 0) {
			System.out.println("delete(): cannot delete an entry with an invalid id ");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(delete);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, id);
			prep_stmt.executeUpdate();
			result = true;
			prep_stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public boolean createTable() {
		boolean result = false;
		Connection conn = Db2DAOFactory.createConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(create);
			result = true;
			stmt.close();
		} catch (Exception e) {
			System.out.println("createTable(): failed to create table '" + TABLE + "': " + e.getMessage());
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public boolean dropTable() {
		boolean result = false;
		Connection conn = Db2DAOFactory.createConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(drop);
			result = true;
			stmt.close();
		} catch (Exception e) {
			System.out.println("dropTable(): failed to drop table '" + TABLE + "': " + e.getMessage());
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

}
