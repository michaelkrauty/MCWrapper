package me.michaelkrauty.MCWrapper.commands;

import java.io.File;
import java.io.IOException;

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
			System.out.println("Starting server " + serverid);
			Server server = new Server(serverid);
			try {
				ProcessBuilder pb = new ProcessBuilder("C:/Program Files/Java/jre7/bin/java.exe", "-jar", "C:/Users/Michael/Desktop/MCWrapper/jar/test.jar");
				pb.directory(new File("C:/Users/Michael/Desktop/MCWrapper/servers/" + serverid));
				Process p = pb.start();
				server.setProcess(p);
			} catch (IOException e) {
				System.out.println("Server directory or jar file not found!");
				e.printStackTrace();
			}
		} else {
			System.out.println("Server ID must be an integer! > 0");
		}
	}
}
