package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;

public class Start {

	public Start(int serverid) {
		Server server = Main.wrapper.getServer(serverid);
		server.start();
	}
}
