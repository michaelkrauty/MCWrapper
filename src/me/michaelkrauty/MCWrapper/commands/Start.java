package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Server;

public class Start {

	int serverid;

	public Start(String serveridString) {
		boolean inputIsInt = true;
		try {
			serverid = Integer.parseInt(serveridString);
		} catch (NumberFormatException e) {
			inputIsInt = false;
		}

		if (inputIsInt && serverid > 0) {
			Server server = new Server(serverid);
			server.start();
		} else {
			System.out.println("Server ID must be an integer! > 0");
		}
	}
}
