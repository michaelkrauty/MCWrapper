package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;

public class Restart {

	public Restart(int serverid) {
		Server server;
		if (Main.wrapper.getServer(serverid) != null) {
			server = Main.wrapper.getServer(serverid);
		} else {
			server = new Server(serverid);
		}
		server.stop();
		while (server.isRunning()) {
			try {
				Thread.sleep(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		server.start();
	}
}
