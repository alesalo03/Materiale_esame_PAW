package it.unibo.paw.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import it.unibo.paw.dao.IdBroker;
import it.unibo.paw.dao.SquadraDAO;
import it.unibo.paw.dao.SquadraDTO;

public class Db2SquadraDAO implements SquadraDAO {

	private static final String TABLE = "squadre";

	private static final String ID = "id";
	private static final String NOME = "nome";
	private static final String TORNEO = "torneo";
	private static final String ALLENATORE = "allenatore";

	// == STATEMENT SQL ====================================================================

	private static final String insert = "INSERT " +
			"INTO " + TABLE + " ( " +
			ID + ", " + NOME + ", " + TORNEO + ", " + ALLENATORE + " " +
			") " +
			"VALUES (?,?,?,?) ";

	private static final String read = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";

	private static final String delete = "DELETE " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";

	private static final String update = "UPDATE " + TABLE + " " +
			"SET " +
			NOME + " = ?, " +
			TORNEO + " = ?, " +
			ALLENATORE + " = ? " +
			"WHERE " + ID + " = ? ";

	// -------------------------------------------------------------------------------------

	// CREATE entrytable ( code INT NOT NULL PRIMARY KEY, ... );
	private static final String create = "CREATE " +
			"TABLE " + TABLE + " ( " +
			ID + " INT NOT NULL PRIMARY KEY, " +
			NOME + " VARCHAR(50) NOT NULL UNIQUE, " +
			TORNEO + " VARCHAR(50) NOT NULL, " +
			ALLENATORE + " VARCHAR(50) NOT NULL, " +
			"UNIQUE (" + NOME + ", " + TORNEO + ")" +
			") ";

	private static final String drop = "DROP " +
			"TABLE " + TABLE + " ";

	@Override
	public void create(SquadraDTO squadra) {
		if (squadra == null) {
			System.out.println("create(): failed to insert a null entry");
			return;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			IdBroker broker = new Db2IdBroker();
			squadra.setId(broker.newId());
			
			PreparedStatement prep_stmt = conn.prepareStatement(Db2SquadraDAO.insert);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, squadra.getId());
			prep_stmt.setString(2, squadra.getNome());
			prep_stmt.setString(3, squadra.getTorneo());
			prep_stmt.setString(4, squadra.getAllenatore());
			prep_stmt.executeUpdate();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("create(): failed to insert entry: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}

	}

	@Override
	public SquadraDTO read(int id) {
		SquadraDTO result = null;
		if (id < 0) {
			System.out.println("read(): cannot read an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(Db2SquadraDAO.read);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, id);
			ResultSet rs = prep_stmt.executeQuery();
			if (rs.next()) {
				SquadraDTO entry = new SquadraDTO();
				entry.setId(rs.getInt(ID));
				entry.setNome(rs.getString(NOME));
				entry.setTorneo(rs.getString(TORNEO));
				entry.setAllenatore(rs.getString(ALLENATORE));
				result = entry;
			}
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("read(): failed to read entry: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public boolean update(SquadraDTO s) {
		boolean result = false;
		if (s == null) {
			System.out.println("update(): failed to update a null entry");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(update);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, s.getId());
			prep_stmt.setString(2, s.getNome());
			prep_stmt.setString(3, s.getTorneo());
			prep_stmt.setString(4, s.getAllenatore());
			prep_stmt.executeUpdate();
			result = true;
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("update(): failed to update entry: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		if (id < 0) {
			System.out.println("delete(): cannot delete an entry with a negative id");
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
		}
		catch (Exception e) {
			System.out.println("delete(): failed to delete entry: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public boolean createTable() {
		boolean result = false;
		// n.d.
		Connection conn = Db2DAOFactory.createConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(create);
			result = true;
			stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
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
		}
		catch (Exception e) {
			System.out.println("dropTable(): failed to drop table '" + TABLE + "': " + e.getMessage());
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

}
