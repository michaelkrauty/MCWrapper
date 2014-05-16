package me.michaelkrauty.MCWrapper.Commands;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;

public class ForceRestart {

	public ForceRestart(int serverid) {
		Server server;
		// if the server is in the server list, get it. else, make a new one.
		if (Main.wrapper.getServer(serverid) != null) {
			server = Main.wrapper.getServer(serverid);
		} else {
			server = new Server(serverid);
		}
		server.forceStop();
		while (server.isRunning()) {
			try {
				Thread.sleep(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		new Start(serverid);
	}
}
