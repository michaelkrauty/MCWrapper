package me.michaelkrauty.MCWrapper;

public class ServerProtocol {

	private final int LOGIN = 0;
	private final int POSTLOGIN = 1;

	private boolean logged = false;

	private int state = LOGIN;

	public String processInput(String theInput) {

		if (state == LOGIN) {
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
		if (state == POSTLOGIN && logged) {
			return "*postlogin*";
		}
		return "u wot m8";
	}

	private boolean checkLogin(String email, String password) {
		return email.equalsIgnoreCase("testemail")
				&& password.equalsIgnoreCase("testpass");
	}
}