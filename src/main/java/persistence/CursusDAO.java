package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


}
