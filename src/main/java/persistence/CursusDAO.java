package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Cursus;

public class CursusDAO extends BaseDAO {

	public ArrayList<Cursus> GetallCursussen() {
		ArrayList<Cursus> results = new ArrayList<Cursus>();

		try (Connection con = super.getConnection()) {
			PreparedStatement stmt = con.prepareStatement("SELECT * from Cursussen");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String naam = rs.getString("naam");
				String beschrijving = rs.getString("beschrijving");

				Cursus newCursus = new Cursus(id, naam, beschrijving);
				results.add(newCursus);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public boolean addCursus(Cursus c) {
		System.out.println("DAO ADDING"+c.getId());
		try (Connection conn = super.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO CURSUSSEN(id,naam,beschrijving) VALUES(?,?,?)");
			stmt.setInt(1, c.getId());
			stmt.setString(2,c.getNaam());
			stmt.setString(3,c.getBeschrijving());
			stmt.executeQuery();

			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			 return false;
		}
		 return true;
	}
	
	public boolean deleteCursus(Cursus c) {
		boolean countryExists =true; // = findByCode(country.getCode()) != null;
		
		if (countryExists) {
			try (Connection con = super.getConnection()) {

				PreparedStatement stmt = con.prepareStatement("DELETE FROM CURSUSSEN WHERE ID = ?");
				stmt.setInt(1,c.getId());
				stmt.executeQuery();

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
