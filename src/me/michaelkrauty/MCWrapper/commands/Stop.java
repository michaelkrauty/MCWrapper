package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;

public class Stop {

	public Stop(int serverid) {
		Server server = Main.wrapper.getServer(serverid);
		server.stop();
	}
}
