package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DBConnect;

public class LoginModel extends DBConnect {

	private Boolean admin;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	static String userid;

	/**
	 * @return the userid
	 */
	public static String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public static void setUserid(String userid) {
		userid = userid;
	}

	public Boolean getCredentials(String username, String password) {
		System.out.println("getCredentials");
		String query = "SELECT * FROM sb_users WHERE user_id = ? and password = ?;";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("in while true");
				// String user_type = rs.getString("user_type");
				// setAdmin(rs.getBoolean("admin"));
				LoginModel.userid = rs.getString("user_id");
				System.out.println("gettinh id" + rs.getString("user_id"));
				System.out.println("getUserId" + LoginModel.userid);
				return true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getRole(String username, String password) {
		System.out.println("in get role");
		String query = "SELECT * FROM sb_users WHERE user_id = ? and password = ?;";
		String user_type = "";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, username);
			stmt.setString(2, password);

			System.out.println("user found");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user_type = rs.getString("user_type");
				System.out.println("user found" + user_type);
				if (user_type.equalsIgnoreCase("B"))
					System.out.println("Buyer");
				else if (user_type.equalsIgnoreCase("P"))
					System.out.println("Vendor");
				else if (user_type.equalsIgnoreCase("A"))
					System.out.println("Admin");
				return user_type;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user_type;

	}

}// end class