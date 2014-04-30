package me.michaelkrauty.MCWrapper;

@SuppressWarnings("unused")
public class ServerProtocol {

	private boolean logged = false;

	private int userid;
	private String email;
	private String username;
	private String pass;
	private String date_registered;

	public String processInput(String theInput) {

		if (theInput != null) {
			String[] input = theInput.split(" ");
			if (logged) {
				if (input.length == 1) {
					if (input[0].equalsIgnoreCase("disconnect")) {
						return "disconnect";
					}
					if (input[0].equalsIgnoreCase("help")) {
						return "help is fun, I suppose";
					}
				}
				if (input.length == 2) {
					if (input[0].equalsIgnoreCase("start")) {
						int serverid = Integer.parseInt(input[1]);
						if (SQL.getServerOwner(serverid) == userid) {
							Main.wrapper.startServer(serverid);
							return "Starting server "
									+ SQL.getServerName(serverid);
						}
					}
					if (input[0].equalsIgnoreCase("stop")) {
						int serverid = Integer.parseInt(input[1]);
						if (SQL.getServerOwner(serverid) == userid) {
							Main.wrapper.stopServer(serverid);
							return "Stopping server "
									+ SQL.getServerName(serverid);
						}
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
							return "Logged in.";
						} else {
							return "Login failed!";
						}
					}
				}
			}
		}
		return "Incorrect Input.";
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