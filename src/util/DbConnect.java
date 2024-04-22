package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
	private String url;
	private String username;
	private String password;
	private Connection database;

	public DbConnect() {
		this("jdbc:mysql://localhost:3306/gym", "root", "1234");
	}

	public DbConnect(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
		database = null;
	}

	private boolean connect() {
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

	protected ResultSet query(String query) {
		if (database == null) {
			if (!connect()) {
				throw new Error("Error creating db connection");
			}
		}

		try (Statement stmt = database.createStatement()) {
			return stmt.executeQuery(query);
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}

}
