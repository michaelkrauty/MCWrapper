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

		if (inputIsInt) {
			System.out.println("Starting server " + serverid);
			Server server = new Server(serverid);
			try {
				Process p = Runtime
						.getRuntime()
						.exec("cd /home/mcwrapper/servers/"
								+ serverid
								+ " && java -jar /home/mcwrapper/jar/test.jar -Xmx1G");
				server.setProcess(p);
			} catch (IOException e) {
				System.out
						.println("Either server directory or jar file not found!");
			}
		} else {
			System.out.println("Server ID must be an integer!");
		}
	}
}
