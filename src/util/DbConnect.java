package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnect {
	private String url;
	private String username;
	private String password;
	private Connection database;

	public DbConnect() {
		this("localhost", "root", "1234");
	}

	/**
	 * 
	 * @param url      Location of the database. Assumes port is 3306
	 * @param username Name of the database user.
	 * @param password Password of the user.
	 */
	public DbConnect(String url, String username, String password) {
		this.url = "jdbc:mysql://" + url + ":3306/gym_management";
		this.username = username;
		this.password = password;
		database = null;
	}

	public boolean connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			database = DriverManager.getConnection(url, username, password);
			return true;
		} catch (SQLException e) {
			System.err.println("SQL error: " + e);
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
		return false;
	}

	public ResultSet query(String query) {
		if (database == null) {
			if (!connect()) {
				throw new Error("Error creating db connection");
			}
		}

		try {
			return database.createStatement().executeQuery(query);
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}
}
