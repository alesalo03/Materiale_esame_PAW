package it.unibo.paw.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unibo.paw.dao.IdBroker;
import it.unibo.paw.dao.RistoranteDAO;
import it.unibo.paw.dao.RistoranteDTO;

public class Db2RistoranteDAO implements RistoranteDAO {

	private static final String TABLE = "ristoranti";

	private static final String ID = "id";
	private static final String NOMERISTORANTE = "nome";
	private static final String INDIRIZZO = "indirizzo";
	private static final String RATING = "rating";

	// == STATEMENT SQL ====================================================================

	private static final String insert = "INSERT " +
			"INTO " + TABLE + " ( " +
			ID + ", " + NOMERISTORANTE + ", " + INDIRIZZO + ", " + RATING + " " +
			") " +
			"VALUES (?,?,?,?) ";

	private static final String read_by_id = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";

	private static final String read_by_name = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + NOMERISTORANTE + " = ? ";

	private static final String read_all = "SELECT * " +
			"FROM " + TABLE + " ";

	private static final String find_resturant_by_city = "SELECT * " +
			"FROM " + TABLE + " "
			+ "WHERE " + INDIRIZZO + " LIKE ?";

	private static final String find_resturant_over_rate = read_all +
			"WHERE " + RATING + " >= ? ";

	private static final String delete = "DELETE " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";

	private static final String update = "UPDATE " + TABLE + " " +
			"SET " +
			NOMERISTORANTE + " = ?, " +
			INDIRIZZO + " = ?, " +
			RATING + " = ? " +
			"WHERE " + ID + " = ? ";

	// -------------------------------------------------------------------------------------

	private static final String create = "CREATE " +
			"TABLE " + TABLE + " ( " +
			ID + " INT NOT NULL PRIMARY KEY, " +
			NOMERISTORANTE + " VARCHAR(50) NOT NULL UNIQUE, " +
			INDIRIZZO + " VARCHAR(50), " +
			RATING + " INT " +
			") ";

	private static final String drop = "DROP " +
			"TABLE " + TABLE + " ";

	@Override
	public void create(RistoranteDTO ristorante) {
		if (ristorante == null) {
			System.out.println("create(): failed to insert a null entry");
			return;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			IdBroker broker = new Db2IdBroker();
			ristorante.setId(broker.newId());
			
			PreparedStatement prep_stmt = conn.prepareStatement(Db2RistoranteDAO.insert);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, ristorante.getId());
			prep_stmt.setString(2, ristorante.getNomeRistorante());
			prep_stmt.setString(3, ristorante.getIndirizzo());
			prep_stmt.setInt(4, ristorante.getRating());
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
	public RistoranteDTO read(int id) {
		RistoranteDTO result = null;
		if (id < 0) {
			System.out.println("read(): cannot read an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(read_by_id);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, id);
			ResultSet rs = prep_stmt.executeQuery();
			if (rs.next()) {
				RistoranteDTO entry = new Db2RistoranteDTOProxy();
				entry.setId(rs.getInt(ID));
				entry.setIndirizzo(rs.getString(INDIRIZZO));
				entry.setRating(rs.getInt(RATING));
				entry.setNomeRistorante(rs.getString(NOMERISTORANTE));
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
	public boolean update(RistoranteDTO r) {
		boolean result = false;
		if (r == null) {
			System.out.println("update(): failed to update a null entry");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(update);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, r.getId());
			prep_stmt.setString(2, r.getNomeRistorante());
			prep_stmt.setString(3, r.getIndirizzo());
			prep_stmt.setInt(4, r.getRating());
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

	@Override
	public List<RistoranteDTO> getResturantByCity(String citta) {
		List<RistoranteDTO> result = null;
		if (citta == null || citta.isEmpty()) {
			System.out.println("getResturantByCity(): cannot read an entry with an invalid city");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(find_resturant_by_city);
			prep_stmt.setString(1, "%"+citta+"%");
			ResultSet rs = prep_stmt.executeQuery();

			result = new ArrayList<RistoranteDTO>();
			while (rs.next()) {
				RistoranteDTO entry = new Db2RistoranteDTOProxy();
				entry.setId(rs.getInt(ID));
				entry.setIndirizzo(rs.getString(INDIRIZZO));
				entry.setRating(rs.getInt(RATING));
				entry.setNomeRistorante(rs.getString(NOMERISTORANTE));
				result.add(entry);
			}
			rs.close();
			prep_stmt.close();
		} catch (Exception e) {
			System.out.println("getResturantByCity(): failed to read entry: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public List<RistoranteDTO> getRatedResturant(int stars) {
		List<RistoranteDTO> result = null;
		if (stars < 1 || stars > 5) {
			System.out.println("getResturantByCity(): cannot read an entry with an invalid input parameter");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(find_resturant_over_rate);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, stars);
			ResultSet rs = prep_stmt.executeQuery();
			result = new ArrayList<RistoranteDTO>();
			while (rs.next()) {
				RistoranteDTO entry = new Db2RistoranteDTOProxy();
				entry.setId(rs.getInt(ID));
				entry.setIndirizzo(rs.getString(INDIRIZZO));
				entry.setRating(rs.getInt(RATING));
				entry.setNomeRistorante(rs.getString(NOMERISTORANTE));
				result.add(entry);
			}
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("getRatedResturant(): failed to read entry: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

}
