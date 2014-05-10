package me.michaelkrauty.MCWrapper;

import me.michaelkrauty.MCWrapper.commands.Command;
import me.michaelkrauty.MCWrapper.commands.ForceStop;
import me.michaelkrauty.MCWrapper.commands.Start;
import me.michaelkrauty.MCWrapper.commands.Stop;

@SuppressWarnings("unused")
public class ServerProtocol {

	private boolean logged = false;

	private int userid;
	private String email;
	private String username;
	private String pass;
	private String date_registered;

	public String[] processInput(String theInput) {

		if (theInput != null) {
			String[] input = theInput.split(" ");
			if (logged) {
				if (input.length == 1) {
					if (input[0].equalsIgnoreCase("disconnect")) {
						return new String[] { "disconnect" };
					}
					if (input[0].equalsIgnoreCase("logout")) {
						logged = false;
						return new String[] { "Logged out." };
					}
					if (input[0].equalsIgnoreCase("help")) {
						return new String[] {
								"Commands: start, stop, forcestop, command",
								"For more detail, use \"help <command>\"" };
					}
				}
				if (input.length == 2) {
					if (input[0].equalsIgnoreCase("start")) {
						int serverid = Integer.parseInt(input[1]);
						if (SQL.getServerOwner(serverid) == userid) {
							new Start(serverid);
							return new String[] { "Starting server "
									+ SQL.getServerName(serverid) };
						}
					}
					if (input[0].equalsIgnoreCase("stop")) {
						int serverid = Integer.parseInt(input[1]);
						if (SQL.getServerOwner(serverid) == userid) {
							new Stop(serverid);
							return new String[] { "Stopping server "
									+ SQL.getServerName(serverid) };
						}
					}
					if (input[0].equalsIgnoreCase("forcestop")
							|| input[0].equalsIgnoreCase("kill")) {
						int serverid = Integer.parseInt(input[1]);
						if (SQL.getServerOwner(serverid) == userid) {
							new ForceStop(serverid);
							return new String[] { "Force stopped server "
									+ SQL.getServerName(serverid) };
						}
					}
				}
				if (input[0].equalsIgnoreCase("command") && input.length > 2) {
					int serverid = Integer.parseInt(input[1]);
					if (SQL.getServerOwner(serverid) == userid) {
						new Command(theInput);
						return new String[] { "Issued command to server "
								+ serverid + "." };
					}
				}
			} else {
				if (input.length == 3) {
					if (input[0].equalsIgnoreCase("login")) {
						if (checkLogin(input[1], input[2])) {
							userid = SQL.getUserIdByEmail(input[1]);
							email = SQL.getUserEmail(userid);
							pass = SQL.getUserPassword(userid);
							date_registered = SQL
									.getUserDate_Registered(userid);
							logged = true;
							return new String[] { "Logged in." };
						} else {
							return new String[] { "Login failed!" };
						}
					}
				} else {
					return new String[] { "Usage: \"login <username> <password>\"" };
				}
			}
		}
		return new String[] { "Incorrect Input." };
	}

	private boolean checkLogin(String email, String password) {
		try {
			return SQL.getUserPassword(SQL.getUserIdByEmail(email)).equals(
					password);
		} catch (NullPointerException e) {
			return false;
		}
	}
}