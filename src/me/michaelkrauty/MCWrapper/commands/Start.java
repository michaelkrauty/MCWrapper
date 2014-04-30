package me.michaelkrauty.MCWrapper.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;

public class Start implements Runnable {

	private int serverid;

	public Start(int id) {
		serverid = id;
	}

	@Override
	public void run() {
		boolean contains = false;
		for (int i = 0; i < Main.servers.size(); i++) {
			if (Main.servers.get(i).getId() == serverid) {
				contains = true;
			}
		}
		if (contains) {
			Main.wrapper.getServer(serverid).start();
		} else {
			Server server = new Server(serverid);
			Main.servers.add(server);
			server.start();
			@SuppressWarnings("unused")
			PrintWriter out = new PrintWriter(server.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					server.getInputStream()));
			String line;
			try {
				while ((line = in.readLine()) != null) {
					System.out.println("Server " + serverid + ": " + line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
