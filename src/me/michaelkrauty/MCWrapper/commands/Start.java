package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Server;

public class Start {

	public Start(int serverid) {
		Server server = new Server(serverid);
		server.start();
	}
}
