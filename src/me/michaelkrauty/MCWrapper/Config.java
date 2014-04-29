package me.michaelkrauty.MCWrapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	private String host;
	private int port;
	private String user;
	private String pass;

	public Config() {
		Properties properties = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("mcwrapper.properties");

			properties.load(input);

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
