package persistence;

import java.sql.*;

public class UserDAO extends BaseDAO {
	public String findRoleForUsernameAndPassword(String username) {
		String role = null;
		String query = "SELECT role FROM accounts WHERE username = ?";

		try (Connection con = super.getConnection()) {

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			//pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				role = rs.getString("role");

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return role;
	}
}