package me.michaelkrauty.MCWrapper;

public class ServerProtocol {

	private int state = 0;

	public String processInput(String theInput) {
		String theOutput = null;

		if (state == 0) {
			theOutput = "test1";
			state = 1;
		}
		if (state == 1) {
			theOutput = "test2";
			state = 2;
		}
		if (state == 2) {
			theOutput = "test3";
			state = 0;
		}
		return theOutput;
	}

	@SuppressWarnings("unused")
	private boolean checkLogin(String email, String password) {
		return true;
	}
}