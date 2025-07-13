package it.unibo.paw.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import it.unibo.paw.dao.IdBroker;
import it.unibo.paw.dao.PartitaDAO;
import it.unibo.paw.dao.PartitaDTO;

public class Db2PartitaDAO implements PartitaDAO {

	private static final String TABLE = "partita";

	// -------------------------------------------------------------------------------------

	private static final String CODICEPARTITA = "CodicePartita";
	private static final String CATEGORIA = "Categoria";
	private static final String GIRONE = "Girone";
	private static final String NOMESQUADRACASA = "NomeSquadraCasa";
	private static final String NOMESQUADRAOSPITE = "NomeSquadraOspite";
	private static final String DATA = "data";

	// == STATEMENT SQL ====================================================================

	// INSERT INTO table ( name,description, ...) VALUES ( ?,?, ... );
	private static final String insert = "INSERT " +
			"INTO " + TABLE + " ( " +
			CODICEPARTITA + ", " + CATEGORIA + ", " + GIRONE + ", " + NOMESQUADRACASA + ", " + NOMESQUADRAOSPITE + ", " + DATA + " " +
			") " +
			"VALUES (?,?,?,?,?,?) ";

	// SELECT * FROM table WHERE codicePartita = ?;
	private static final String read = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + CODICEPARTITA + " = ? ";

	// DELETE FROM table WHERE codicePartita = ?;
	private static final String delete = "DELETE " +
			"FROM " + TABLE + " " +
			"WHERE " + CODICEPARTITA + " = ? ";

	// UPDATE table SET xxxcolumn = ?, ... WHERE idcolumn = ?;
	private static final String update = "UPDATE " + TABLE + " " +
			"SET " +
			CATEGORIA + " = ?, " +
			GIRONE + " = ?, " +
			NOMESQUADRACASA + " = ?, " +
			NOMESQUADRAOSPITE + " = ?, " +
			DATA + " = ? " +
			"WHERE " + CODICEPARTITA + " = ? ";
	// -------------------------------------------------------------------------------------

	// CREATE entrytable ( id INT NOT NULL PRIMARY KEY, ... );
	private static final String create = "CREATE " +
			"TABLE " + TABLE + " ( " +
			CODICEPARTITA + " INT NOT NULL PRIMARY KEY, " +
			CATEGORIA + " VARCHAR(50) NOT NULL, " +
			GIRONE + " VARCHAR(10) NOT NULL, " +
			NOMESQUADRACASA + " VARCHAR(50) NOT NULL, " +
			NOMESQUADRAOSPITE + " VARCHAR(50) NOT NULL, " +
			DATA + " VARCHAR(20) NOT NULL " +
			") ";
	private static final String drop = "DROP " +
			"TABLE " + TABLE + " ";

	// === METODI DAO =========================================================================
	@Override
	public void create(PartitaDTO partita) {
		Connection conn = Db2DAOFactory.createConnection();
		
		if (partita == null) {
			System.out.println("create(): cannot insert a null entry");
			return;
		}

		try {
			IdBroker broker = new Db2IdBroker();
			partita.setCodicePartita(broker.newId());
			
			PreparedStatement prep_stmt = conn.prepareStatement(insert);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, partita.getCodicePartita());
			prep_stmt.setString(2, partita.getCategoria());
			prep_stmt.setString(3, partita.getGirone());
			prep_stmt.setString(4, partita.getNomeSquadraCasa());
			prep_stmt.setString(5, partita.getNomeSquadraOspite());
			prep_stmt.setString(6, partita.getData());
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
	public PartitaDTO read(int codicePartita) {
		PartitaDTO result = null;
		if (codicePartita < 0) {
			System.out.println("read(): cannot read an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(read);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, codicePartita);
			ResultSet rs = prep_stmt.executeQuery();
			if (rs.next()) {
				PartitaDTO entry = new PartitaDTO();
				entry.setCodicePartita(rs.getInt(CODICEPARTITA));
				entry.setCategoria(rs.getString(CATEGORIA));
				entry.setGirone(rs.getString(GIRONE));
				entry.setNomeSquadraCasa(rs.getString(NOMESQUADRACASA));
				entry.setNomeSquadraOspite(rs.getString(NOMESQUADRAOSPITE));
				entry.setData(rs.getString(DATA));
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
	public boolean update(PartitaDTO partita) {
		boolean result = false;
		if (partita == null) {
			System.out.println("update(): failed to update a null entry");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(update);
			prep_stmt.clearParameters();
			prep_stmt.setString(1, partita.getCategoria());
			prep_stmt.setString(2, partita.getGirone());
			prep_stmt.setString(3, partita.getNomeSquadraCasa());
			prep_stmt.setString(4, partita.getNomeSquadraOspite());
			prep_stmt.setString(5, partita.getData());
			prep_stmt.setInt(6, partita.getCodicePartita());
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
	public boolean delete(int codicePartita) {
		boolean result = false;
		if (codicePartita < 0) {
			System.out.println("delete(): cannot delete an entry with an invalid id ");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(delete);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, codicePartita);
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
