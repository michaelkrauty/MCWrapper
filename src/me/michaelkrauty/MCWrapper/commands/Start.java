package me.michaelkrauty.MCWrapper.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;

public class Start implements Runnable {

	private Thread t;

	private int serverid;

	public Start(int id) {
		serverid = id;
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	@Override
	public void run() {
		Server server;
		server = Main.wrapper.getServer(serverid);
		if (server != null) {
			;
		} else {
			server = null;
			server = new Server(serverid);
			Main.servers.add(server);
		}
		server.start();
		PrintWriter out = new PrintWriter(server.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				server.getInputStream()));
		String line;
		try {
			while ((line = in.readLine()) != null) {
				System.out.println("Server " + serverid + ": " + line);
			}
			in.close();
			out.close();
		} catch (IOException ignored) {
		}
	}
}
