package it.unibo.paw.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.paw.dao.GiocatoreDTO;
import it.unibo.paw.dao.SquadraDTO;
import it.unibo.paw.dao.SquadraGiocatoreMappingDAO;

public class Db2SquadraGiocatoreMappingDAO implements SquadraGiocatoreMappingDAO {
	private static final String TABLE = "squadra_giocatore";
	// -------------------------------------------------------------------------------------
	private static final String ID_S = "idSquadra";
	private static final String ID_G = "idGiocatore";
	// == STATEMENT SQL ====================================================================
	private static final String insert = "INSERT " +
			"INTO " + TABLE + " ( " +
			ID_S + ", " + ID_G + " " +
			") " +
			"VALUES (?,?) ";

	private static final String delete = "DELETE " +
			"FROM " + TABLE + " " +
			"WHERE " + ID_S + " = ? " +
			"AND " + ID_G + " = ? ";

	// -------------------------------------------------------------------------------------
	private static final String create = "CREATE " +
			"TABLE " + TABLE + " ( " +
			ID_S + " INT NOT NULL, " +
			ID_G + " INT NOT NULL, " +
			"PRIMARY KEY (" + ID_S + "," + ID_G + " ), " +
			"FOREIGN KEY (" + ID_S + ") REFERENCES Squadre(id) ON DELETE CASCADE, " +
			"FOREIGN KEY (" + ID_G + ") REFERENCES Giocatori(id) ON DELETE CASCADE " +
			") ";

	private static final String drop = "DROP " +
			"TABLE " + TABLE + " ";
	
	// -------------------------------------------------------------------------------------
	private static final String allenatoriPerGiocatori = 
		    "SELECT g.*, s.allenatore " +
		    "FROM giocatori g " +
		    "JOIN squadra_giocatore sg ON g.id = sg.idGiocatore " +
		    "JOIN squadre s ON sg.idSquadra = s.id";

	// -------------------------------------------------------------------------------------
	
	@Override
	public void create(int ids, int idg) {
		Connection conn = Db2DAOFactory.createConnection();
		if (ids < 0 || idg < 0) {
			System.out.println("create(): cannot insert an entry with an invalid id ");
			return;
		}
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(insert);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, ids);
			prep_stmt.setInt(2, idg);
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
	public boolean delete(int ids, int idg) {
		boolean result = false;
		if (ids < 0 || idg < 0) {
			System.out.println("delete(): cannot delete an entry with an invalid id ");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(delete);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, ids);
			prep_stmt.setInt(2, idg);
			prep_stmt.executeUpdate();
			result = true;
			prep_stmt.close();
		} catch (Exception e) {
			System.out.println(
					"delete(): failed to delete entry with ids = " + ids + " and idg = " + idg + ": " + e.getMessage());
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
	
	@Override
	public List<String[]> allenatoriPerGiocatori() {
		Connection conn = Db2DAOFactory.createConnection();
		
		List<String[]> result = new ArrayList<>();
		
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(allenatoriPerGiocatori);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				String g = "";
				String allenatoreName = "";
				g = (rs.getString("nome"));
				g += " " + (rs.getString("cognome"));
				allenatoreName = rs.getString("allenatore");
				String[] s = new String[2];
				s[0] = g;
				s[1] = allenatoreName;
				result.add(s);
			}
			rs.close();
			prep_stmt.close();
		} catch (Exception e) {
			System.out.println("(allenatoriPerGiocatori): failed to execute query: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}


}
