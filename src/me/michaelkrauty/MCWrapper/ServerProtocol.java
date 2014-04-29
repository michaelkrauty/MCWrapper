package me.michaelkrauty.MCWrapper;

public class ServerProtocol {
	private static final int LOGIN = 0;
	private static final int CHALLENGED = 1;
	private static final int POSTLOGIN = 2;

	private int state = LOGIN;

	public String processInput(String theInput) {
		String theOutput = null;

		if (state == LOGIN) {
			theOutput = "LOGIN";
			state = CHALLENGED;
		}
		if (state == CHALLENGED) {
			theOutput = "CHALLENGED";
			state = POSTLOGIN;
		}
		if (state == POSTLOGIN) {
			theOutput = "POSTLOGIN";
			state = LOGIN;
		}
		return theOutput;
	}

	@SuppressWarnings("unused")
	private boolean checkLogin(String email, String password) {
		return true;
	}
}