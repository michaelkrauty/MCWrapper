package me.michaelkrauty.MCWrapper;

public class ServerProtocol {

	private final int LOGIN = 0;
	private final int POSTLOGIN = 1;

	private boolean logged = false;

	private int state = LOGIN;

	public String processInput(String theInput) {

		if (state == LOGIN && !logged) {
			if (theInput != null) {
				String[] input = theInput.split(",");
				if (input.length == 2) {
					if (checkLogin(input[0], input[1])) {
						logged = true;
						state = POSTLOGIN;
						return "Logged in.";
					} else {
						return "Login failed!";
					}
				}
			}
		}
		if (state == POSTLOGIN && logged) {
			if (theInput != null) {
				String[] input = theInput.split(",");
				if (input.length == 1) {
					if (input[0].equalsIgnoreCase("disconnect")) {
						return "disconnect";
					}
				}
			}
		}
		return "Incorrect Input.";
	}

	private boolean checkLogin(String email, String password) {
		return SQL.getUserPassword(SQL.getUserIdByEmail(email))
				.equals(password);
	}
}