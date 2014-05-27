package me.michaelkrauty.MCWrapper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SQL {

	private final static Logger log = Logger.getLogger(Main.class);

	private static Connection connection;

	private synchronized static void openConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://"
							+ Main.config.getDBHost() + ":" + Main.config.getDBPort()
							+ "/MPCP2", Main.config.getDBUser(),
					Main.config.getDBPass()
			);
		} catch (Exception e) {
			log.info("Couldn't connect to database! Reason: " + e.getMessage());
		}
	}

	private synchronized static void closeConnection() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized static boolean serverDataContainsServer(int serverid) {
		openConnection();
		try {
			PreparedStatement sql = connection
					.prepareStatement("SELECT * FROM `servers` WHERE id=?;");
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

	public synchronized static boolean userDataContainsId(int userid) {
		openConnection();
		try {
			PreparedStatement sql = connection
					.prepareStatement("SELECT * FROM `users` WHERE id=?;");
			sql.setInt(1, userid);
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

	public synchronized static boolean userDataContainsEmail(String email) {
		openConnection();
		try {
			PreparedStatement sql = connection
					.prepareStatement("SELECT * FROM `users` WHERE email=?;");
			sql.setString(1, email);
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

	public synchronized static int getServerOwner(int serverid) {
		try {
			if (serverDataContainsServer(serverid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `servers` WHERE id=?;");
				sql.setInt(1, serverid);
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getInt("owner");
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			closeConnection();
		}
	}

	public synchronized static String getServerHost(int serverid) {
		try {
			if (serverDataContainsServer(serverid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `servers` WHERE id=?;");
				sql.setInt(1, serverid);
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getString("host");
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

	public synchronized static int getServerPort(int serverid) {
		try {
			if (serverDataContainsServer(serverid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `servers` WHERE id=?;");
				sql.setInt(1, serverid);
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getInt("port");
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			closeConnection();
		}
	}

	public synchronized static int getServerMemory(int serverid) {
		try {
			if (serverDataContainsServer(serverid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `servers` WHERE id=?;");
				sql.setInt(1, serverid);
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getInt("memory");
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			closeConnection();
		}
	}

	public synchronized static int getServerJarId(int serverid) {
		try {
			if (serverDataContainsServer(serverid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `servers` WHERE id=?;");
				sql.setInt(1, serverid);
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getInt("jar");
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			closeConnection();
		}
	}

	public synchronized static String getServerJarPath(int serverid) {
		try {
			if (serverDataContainsServer(serverid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `jars` WHERE id=?;");
				sql.setInt(1, getServerJarId(serverid));
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getString("location");
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

	public synchronized static boolean getServerSuspended(int serverid) {
		try {
			if (serverDataContainsServer(serverid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `servers` WHERE id=?;");
				sql.setInt(1, serverid);
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getBoolean("suspended");
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeConnection();
		}
	}

	public synchronized static String getServerName(int serverid) {
		try {
			if (serverDataContainsServer(serverid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `servers` WHERE id=?;");
				sql.setInt(1, serverid);
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getString("name");
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

	public static boolean getServerCrashDetection(int serverid) {
		try {
			if (serverDataContainsServer(serverid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `servers` WHERE id=?;");
				sql.setInt(1, serverid);
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getBoolean("crash_detection");
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeConnection();
		}
	}

	public synchronized static ArrayList<String> getAllServers() {
		try {
			openConnection();
			PreparedStatement sql = connection
					.prepareStatement("SELECT `id` FROM `servers`;");
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

	public synchronized static int getUserIdByEmail(String email) {
		try {
			if (userDataContainsEmail(email)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `users` WHERE email=?;");
				sql.setString(1, email);
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getInt("id");
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			closeConnection();
		}
	}

	public synchronized static String getUserEmail(int userid) {
		try {
			if (userDataContainsId(userid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `users` WHERE id=?;");
				sql.setInt(1, userid);
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getString("email");
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

	public synchronized static String getUserUsername(int userid) {
		try {
			if (userDataContainsId(userid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `users` WHERE id=?;");
				sql.setInt(1, userid);
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getString("username");
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

	public synchronized static String getUserPassword(int userid) {
		try {
			if (userDataContainsId(userid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `users` WHERE id=?;");
				sql.setInt(1, userid);
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getString("password");
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

	public synchronized static String getUserDate_Registered(int userid) {
		try {
			if (userDataContainsId(userid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `users` WHERE id=?;");
				sql.setInt(1, userid);
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getString("date_registered");
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

	public synchronized static ArrayList<Integer> getUserServers(int userid) {
		ArrayList<Integer> servers = new ArrayList<Integer>();
		ArrayList<String> allServers = getAllServers();
		for (int i = 0; i < allServers.size(); i++) {
			if (getServerOwner(Integer.parseInt(allServers.get(i))) == userid) {
				servers.add(Integer.parseInt(allServers.get(i)));
			}
		}
		return servers;
	}

	public synchronized static String getServerMod(int serverid) {
		try {
			if (serverDataContainsServer(serverid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `jars` WHERE id=?;");
				sql.setInt(1, getServerJarId(serverid));
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getString("mod");
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

	public synchronized static String getServerStartupCommand(int serverid) {
		log.info("server " + serverid + " jar ID: " + getServerJarId(serverid));
		try {
			if (serverDataContainsServer(serverid)) {
				openConnection();
				PreparedStatement sql = connection
						.prepareStatement("SELECT * FROM `jars` WHERE id=?;");
				sql.setInt(1, getServerJarId(serverid));
				ResultSet result = sql.executeQuery();
				result.next();
				return result.getString("startup_args");
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
}