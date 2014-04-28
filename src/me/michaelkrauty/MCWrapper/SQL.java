package me.michaelkrauty.MCWrapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SQL {

	private static Connection connection;

	private synchronized static void openConnection() {
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://dominationvps.com:3306/mcwrapper",
					"mcwrapper", "mcwrapper");
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
		System.out.println("Checking SQL tables...");
		try {
			openConnection();
			PreparedStatement sql = connection
					.prepareStatement("CREATE TABLE `servers`(serverid int(255) KEY, host varchar(255), port int(255), memory int(255), jar int(255), suspended boolean, name varchar(255));");
			sql.executeUpdate();
			System.out.println("Created SQL table `servers`");
			sql.close();
			closeConnection();
		} catch (Exception e1) {
		}
		System.out.println("SQL tables checked.");
	}

	public synchronized static boolean serverDataContainsServer(int serverid) {
		openConnection();
		try {
			PreparedStatement sql = connection
					.prepareStatement("SELECT * FROM `servers` WHERE serverid=?;");
			sql.setInt(1, serverid);
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

	public synchronized static ArrayList<String> getServer(int serverid) {
		try {
			if (serverDataContainsServer(serverid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `servers` WHERE serverid=?;");
				sql.setInt(1, serverid);
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

	public synchronized static boolean createServer(int serverid, String name,
			int memory, int ownerid) {
		// TODO
		return false;
	}

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