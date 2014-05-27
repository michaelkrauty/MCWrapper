package me.michaelkrauty.MCWrapper.Commands;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start implements Runnable {

	private final static Logger log = Logger.getLogger(Main.class);

	private Thread t;

	private final int serverid;

	public Start(int id) {
		serverid = id;
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public void run() {
		Server server = Main.wrapper.getServer(serverid);
		if (server != null) {
			;
		} else {
			server = null;
			server = new Server(serverid);
			Main.servers.add(server);
		}
		server.start();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				server.getInputStream()));
		String line;
		try {
			while ((line = in.readLine()) != null) {
				log.info("Server " + serverid + ": " + line);
			}
			in.close();
		} catch (IOException ignored) {
		}
	}
}
