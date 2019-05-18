package it.brax.gestorecorsi.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class IscritticorsiConnect {
	private static Properties props = new Properties();
	private static String jdbcURL;
	private static String user;
	private static String pass;

	public static Connection getConnection() throws SQLException, FileNotFoundException, IOException {
		props.load(new FileInputStream("properties/dbconnection.properties"));
		jdbcURL = props.getProperty("jdbcURL");
		user = props.getProperty("user");
		pass = props.getProperty("pass");
		return DriverManager.getConnection(jdbcURL, user, pass);
	}

}
