package me.michaelkrauty.MCWrapper;

public class ServerProtocol {

	private final int LOGIN = 0;
	@SuppressWarnings("unused")
	private final int POSTLOGIN = 1;

	private boolean logged = false;

	@SuppressWarnings("unused")
	private int state = LOGIN;

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
			} else {
				if (input.length == 3) {
					if (input[0].equalsIgnoreCase("login")
							&& checkLogin(input[1], input[2])) {
						logged = true;
						return "Logged in.";
					} else {
						return "Login failed!";
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