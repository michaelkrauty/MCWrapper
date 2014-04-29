package me.michaelkrauty.MCWrapper;

public class ServerProtocol {

	private int state = 0;

	public String processInput(String theInput) {

		if (state == 0) {
			state = 1;
			return "test1";
		}
		if (state == 1) {
			state = 2;
			return "test2";
		}
		if (state == 2) {
			state = 0;
			return "test3";
		}
		return "u wot m8";
	}

	@SuppressWarnings("unused")
	private boolean checkLogin(String email, String password) {
		return true;
	}
}