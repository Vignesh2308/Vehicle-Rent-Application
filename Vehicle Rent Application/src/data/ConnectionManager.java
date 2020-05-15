package data;

import java.sql.*;

public final class ConnectionManager {

	private static final String DB_DRIVER = "org.hsqldb.jdbc.JDBCDriver";
	private static final String DB_CONNECTION = "jdbc:hsqldb:file:database/testDB";
	private static final String DB_USER = "SA";
	private static final String DB_PASSWORD = "";

	private static Connection conn;

	public static Connection getConnection() throws SQLException {

		if (conn != null && !(conn.isClosed())) {
			return conn;
		} else {
			try {
				Class.forName(DB_DRIVER);
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
			try {
				conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
				return conn;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return conn;
		}

	}

}
