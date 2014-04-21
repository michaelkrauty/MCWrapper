package me.michaelkrauty.MCWrapper.commands;

import java.io.IOException;

import me.michaelkrauty.MCWrapper.Server;

public class Start {

	public Start(String serveridString) {
		int serverid = 0;
		boolean inputIsInt = true;
		try {
			serverid = Integer.parseInt(serveridString);
		} catch (NumberFormatException e) {
			inputIsInt = false;
		}

		if (inputIsInt && serverid > 0) {
			System.out.println("Starting server " + serverid);
			Server server = new Server(serverid);
			try {
				Process p = Runtime.getRuntime().exec(
						"cd /home/mcwrapper/servers/"
								+ Integer.toString(serverid)
								+ " && java -jar /home/mcwrapper/jar/test.jar");
				server.setProcess(p);
			} catch (IOException e) {
				System.out.println("Server directory or jar file not found!");
			}
		} else {
			System.out.println("Server ID must be an integer! > 0");
		}
	}
}
