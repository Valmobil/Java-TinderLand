package DAO;

import java.sql.*;

/**
 * @author others
 * @author Alex Ignatenko
 *
 */

public class ConnectionToDB
{
	private static final String DB_URL = "jdbc:postgresql://35.202.233.180:5432/tinderland";
	private static final String USERNAME = "postgres";
	private static final String USER_PASS = "40872";

	/**
	 *
	 * @return Connection as result of...
	 */
	protected static Connection getConnection(){

		Connection connection = null;

		try
		{
			connection = DriverManager.getConnection(DB_URL, USERNAME, USER_PASS);
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}

		return connection;
	}
}
