package me.michaelkrauty.MCWrapper;

/*
 * Client Connection class...
 */

//Imports

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

/*
 * Config class...
 */
public class Config {

	private final static Logger log = Logger.getLogger(Main.class);

	// Create Variables
	private String host;
	private int port;
	private String user;
	private String pass;

	// Constructor
	public Config() {
		// Creates a java properties file
		Properties properties = new Properties();
		// Creates an inputstream object
		InputStream input = null;

		File propertiesFile = new File("mcwrapper.properties");

		if (!propertiesFile.exists()) {
			createProperties(properties);
		}

		try {
			// Reads in mcwrapper.properties
			input = new FileInputStream("mcwrapper.properties");
			// Loads mcwrapper.properties
			properties.load(input);

			// Constructor variables set.
			host = properties.getProperty("db_host");
			port = Integer.parseInt(properties.getProperty("db_port"));
			user = properties.getProperty("db_user");
			pass = properties.getProperty("db_password");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void createProperties(Properties properties) {
		File propertiesFile = new File("mcwrapper.properties");
		try {
			propertiesFile.createNewFile();
			properties.setProperty("db_host", "localhost");
			properties.setProperty("db_port", "3306");
			properties.setProperty("db_user", "root");
			properties.setProperty("db_password", "1234");
			properties
					.store(new FileOutputStream("mcwrapper.properties"), null);
		} catch (IOException e) {
			log.error("Couldn't create mcwrapper.properties!");
			log.error(e.getMessage());
		}
	}

	public String getDBHost() {
		return host;
	}

	public int getDBPort() {
		return port;
	}

	public String getDBUser() {
		return user;
	}

	public String getDBPass() {
		return pass;
	}
}
