package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;

public class Restart {

	public Restart(int serverid) {
		Server server = Main.wrapper.getServer(serverid);
		server.restart();
	}
}
