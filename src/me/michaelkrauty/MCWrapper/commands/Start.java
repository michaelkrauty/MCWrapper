package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;

public class Start {

	public Start(int serverid) {
		Server server = new Server(serverid);
		Main.servers.add(server);
		server.start();
	}
}
