package me.michaelkrauty.MCWrapper.ClientConnection;

import me.michaelkrauty.MCWrapper.Commands.*;
import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.SQL;
import org.apache.log4j.Logger;

import java.net.Socket;
import java.util.ArrayList;

@SuppressWarnings("unused")
class ServerProtocol {

	private final static Logger log = Logger.getLogger(Main.class);

	private boolean logged = false;

	private int userid;
	private String email;
	private String username;
	private String pass;
	private String date_registered;

	private final ClientOutput clientOutput;

	public ServerProtocol(Socket soc) {
		clientOutput = new ClientOutput(soc);
	}

	public void processInput(String theInput) {

		ArrayList<String> returnLines = new ArrayList<String>();

		if (theInput != null) {
			String[] input = theInput.split(" ");
			if (logged) {
				if (input.length == 1) {
					if (input[0].equalsIgnoreCase("disconnect")) {
						returnLines.add("disconnect");
					}
					if (input[0].equalsIgnoreCase("logout")) {
						logged = false;
						returnLines.add("Logged out.");
					}
					if (input[0].equalsIgnoreCase("help")) {
						returnLines
								.add("Commands: start, stop, forcestop|kill, command, disconnect");
						returnLines
								.add("For more detail, use \"help <command>\"");
					}
				}
				if (input.length == 2) {
					if (input[0].equalsIgnoreCase("start")) {
						int serverid = Integer.parseInt(input[1]);
						if (SQL.getServerOwner(serverid) == userid) {
							new Start(serverid);
							returnLines.add("Starting server " + serverid);
						}
					}
					if (input[0].equalsIgnoreCase("stop")) {
						int serverid = Integer.parseInt(input[1]);
						if (SQL.getServerOwner(serverid) == userid) {
							new Stop(serverid);
							returnLines.add("Stopping server " + serverid);
						}
					}
					if (input[0].equalsIgnoreCase("restart")) {
						int serverid = Integer.parseInt(input[1]);
						if (SQL.getServerOwner(serverid) == userid) {
							new Restart(serverid);
							returnLines.add("Restarting server " + serverid);
						}
					}
					if (input[0].equalsIgnoreCase("forcestop")
							|| input[0].equalsIgnoreCase("kill")) {
						int serverid = Integer.parseInt(input[1]);
						if (SQL.getServerOwner(serverid) == userid) {
							new ForceStop(serverid);
							returnLines.add("Foce stopped server " + serverid);
						}
					}
					if (input[0].equalsIgnoreCase("reload")) {
						int serverid = Integer.parseInt(input[1]);
						if (SQL.getServerOwner(serverid) == userid) {
							new Reload(serverid);
							returnLines.add("Reloaded server " + serverid);
						}
					}
					if (input[0].equalsIgnoreCase("command")) {
						int serverid = Integer.parseInt(input[1]);
						if (SQL.getServerOwner(serverid) == userid) {
							new ServerCommand(input);
							returnLines.add("Command sent to server "
									+ serverid);
						}
					}
				}
			} else {
				if (input.length == 3 && input[0].equalsIgnoreCase("login")) {
					if (checkLogin(input[1], input[2])) {
						userid = SQL.getUserIdByEmail(input[1]);
						email = SQL.getUserEmail(userid);
						pass = SQL.getUserPassword(userid);
						date_registered = SQL.getUserDate_Registered(userid);
						logged = true;
						returnLines.add("Logged in.");
					} else {
						returnLines.add("Login failed!");
					}
				} else {
					returnLines.add("Usage: \"login <email> <password>\"");
				}
			}
		} else {
			returnLines.add("Incorrect Input.");
		}
		clientOutput.send(returnLines);
	}

	private boolean checkLogin(String email, String password) {
		try {
			return SQL.getUserPassword(SQL.getUserIdByEmail(email)).equals(
					password);
		} catch (NullPointerException e) {
			return false;
		}
	}

	public void closeConnections() {
		clientOutput.closeConnection();
	}
}