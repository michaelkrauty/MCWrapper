package me.michaelkrauty.MCWrapper;

import java.sql.*;
import java.util.ArrayList;

public class SQL {

	private static Connection connection;

	private synchronized static void openConnection() {
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mcwrapper", "mcwrapper",
					"mcwrapper");
		} catch (Exception e) {
			System.out.println("Couldn't connect to database! Reason: "
					+ e.getMessage());
		}
	}

	private synchronized static void closeConnection() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized static void checkSqlTables() {
		try {
			openConnection();
			PreparedStatement sql = connection
					.prepareStatement("CREATE TABLE `servers`(serverid varchar(255) KEY, name varchar(255));");
			sql.executeUpdate();
			System.out.println("Created SQL table `servers`");
			sql.close();
			closeConnection();
		} catch (Exception e1) {
		}
	}

	/**
	 * @param serverid
	 * @return boolean
	 */
	private synchronized static boolean serverDataContainsServer(String serverid) {
		openConnection();
		try {
			PreparedStatement sql = connection
					.prepareStatement("SELECT * FROM `servers` WHERE serverid=?;");
			sql.setString(1, serverid);
			ResultSet resultSet = sql.executeQuery();
			boolean containsServer = resultSet.next();

			sql.close();
			resultSet.close();
			closeConnection();

			return containsServer;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param serverid
	 * @return server
	 */
	public synchronized static ArrayList<String> getServer(String serverid) {
		try {
			if (serverDataContainsServer(serverid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `servers` WHERE serverid=?;");
				sql.setString(1, serverid);
				ResultSet result = sql.executeQuery();
				result.next();
				ArrayList<String> info = new ArrayList<String>();
				info.add(result.getString("serverid"));
				info.add(result.getString("etc"));
				info.add(result.getString("etc"));
				return info;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection();
		}
	}

	/**
	 * @return servers
	 */
	public synchronized static ArrayList<String> getAllServers() {
		try {
			openConnection();
			PreparedStatement sql = connection
					.prepareStatement("SELECT `serverid` FROM `servers`;");
			ResultSet result = sql.executeQuery();
			result.last();
			int items = result.getRow();
			result.first();
			ArrayList<String> ids = new ArrayList<String>();
			for (int i = 0; i < items; i++) {
				ids.add(result.getString(1));
				result.next();
			}
			return ids;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return null;
	}
}