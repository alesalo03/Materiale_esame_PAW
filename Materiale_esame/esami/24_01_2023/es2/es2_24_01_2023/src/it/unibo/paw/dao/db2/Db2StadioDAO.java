package it.unibo.paw.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import it.unibo.paw.dao.IdBroker;
import it.unibo.paw.dao.PartitaDTO;
import it.unibo.paw.dao.StadioDAO;
import it.unibo.paw.dao.StadioDTO;

public class Db2StadioDAO implements StadioDAO {

	private static final String TABLE = "stadio";
	private static final String TABLEPARTITA = "partita";

	private static final String CODICE = "Codice";
	private static final String NOME = "Nome";
	private static final String CITTA = "Citta";
	
	private static final String CODICEPARTITA = "CodicePartita";
	private static final String CATEGORIA = "Categoria";
	private static final String GIRONE = "Girone";
	private static final String NOMESQUADRACASA = "NomeSquadraCasa";
	private static final String NOMESQUADRAOSPITE = "NomeSquadraOspite";
	private static final String DATA = "data";
	private static final String CODICESTADIO = "CodiceStadio";

	// INSERT INTO table ( id, name, description, ...) VALUES ( ?,?, ... );
	private static final String insert = "INSERT " +
			"INTO " + TABLE + " ( " +
			CODICE + ", " + NOME + ", " + CITTA + " " +
			") " +
			"VALUES (?,?,?) ";

	// SELECT * FROM table WHERE Codice = ?;
	private static final String read_by_id = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + CODICE + " = ? ";
	

	private static final String partite_from_stadio = 
		    "SELECT p.Categoria, COUNT(*) AS numero_partite " +
		    "FROM Partita p JOIN " + TABLE + " s ON p.CodiceStadio = s.Codice " +
		    "WHERE s." + NOME + " = ? GROUP BY p.Categoria";

	// DELETE FROM table WHERE codice = ?;
	private static final String delete = "DELETE " +
			"FROM " + TABLE + " " +
			"WHERE " + CODICE + " = ? ";

	// UPDATE table SET xxxcolumn = ?, ... WHERE codice = ?;
	private static final String update = "UPDATE " + TABLE + " " +
			"SET " +
			NOME + " = ?, " +
			CITTA + " = ? " +
			"WHERE " + CODICE + " = ? ";
	
	private static final String insertPartita = "INSERT " +
			"INTO " + TABLEPARTITA + " ( " +
			CODICEPARTITA + ", " + CATEGORIA + ", " + GIRONE + ", " + NOMESQUADRACASA + ", " + NOMESQUADRAOSPITE + ", " + CODICESTADIO + ", " + DATA + " " +
			") " +
			"VALUES (?,?,?,?,?,?,?) ";

	// -------------------------------------------------------------------------------------

	// CREATE entrytable ( code INT NOT NULL PRIMARY KEY, ... );
	private static final String create = "CREATE " +
			"TABLE " + TABLE + " ( " +
			CODICE + " INT NOT NULL PRIMARY KEY, " +
			NOME + " VARCHAR(100) NOT NULL UNIQUE, " +
			CITTA + " VARCHAR(100) NOT NULL " +
			") ";

	private static final String drop = "DROP " +
			"TABLE " + TABLE + " ";

	@Override
	public void create(StadioDTO stadio) {
		if (stadio == null) {
			System.out.println("create(): failed to insert a null entry");
			return;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			IdBroker broker = new Db2IdBroker();
			stadio.setCodice(broker.newId());
			

			PreparedStatement prep_stmt = conn.prepareStatement(Db2StadioDAO.insert);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, stadio.getCodice());
			prep_stmt.setString(2, stadio.getNome());
			prep_stmt.setString(3, stadio.getCitta());
			prep_stmt.executeUpdate();
			prep_stmt.close();
			
			prep_stmt = conn.prepareStatement(Db2StadioDAO.insertPartita);
			for (PartitaDTO p : stadio.getPartite()) {
				prep_stmt.clearParameters();
				prep_stmt.setInt(1, p.getCodicePartita());
				prep_stmt.setString(2, p.getCategoria());
				prep_stmt.setString(3, p.getGirone());
				prep_stmt.setString(4, p.getNomeSquadraCasa());
				prep_stmt.setString(5, p.getNomeSquadraOspite());
				prep_stmt.setInt(6, stadio.getCodice());
				prep_stmt.setString(7, p.getData());
				prep_stmt.executeUpdate();
			}
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
	public StadioDTO read(int codice) {
		StadioDTO result = null;
		if (codice < 0) {
			System.out.println("read(): cannot read an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(read_by_id);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, codice);
			ResultSet rs = prep_stmt.executeQuery();
			if (rs.next()) {
				StadioDTO entry = new StadioDTO(); //nel caso di eager loading non c'è bisogno del proxy
				entry.setCodice(rs.getInt(CODICE));
				entry.setNome(rs.getString(NOME));
				entry.setCitta(rs.getString(CITTA));
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
	public  Map<String, Integer> getPartiteFromStadio(String nome) {
		 Map<String, Integer> result = null;
		if (nome.length() == 0 || nome.isBlank()) {
			System.out.println("read(): cannot read an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			result = new HashMap<>();
			PreparedStatement prep_stmt = conn.prepareStatement(partite_from_stadio);
			prep_stmt.clearParameters();
			prep_stmt.setString(1, nome);
			ResultSet rs = prep_stmt.executeQuery();
			
			while (rs.next()) {
				String categoria = rs.getString("Categoria");
				Integer numero_partite = rs.getInt("numero_partite");
				result.put(categoria, numero_partite);
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
	public boolean update(StadioDTO stadio) {
		boolean result = false;
		if (stadio == null) {
			System.out.println("update(): failed to update a null entry");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(update);
			prep_stmt.clearParameters();
			prep_stmt.setString(1, stadio.getNome());
			prep_stmt.setString(2, stadio.getCitta());
			prep_stmt.setInt(3, stadio.getCodice());
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
	public boolean delete(int codice) {
		boolean result = false;
		if (codice < 0) {
			System.out.println("delete(): cannot delete an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(delete);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, codice);
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
