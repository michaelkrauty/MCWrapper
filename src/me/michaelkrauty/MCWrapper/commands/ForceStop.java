package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;

public class ForceStop {

	public ForceStop(int serverid) {
		Server server = Main.wrapper.getServer(serverid);
		server.forceStop();
	}
}
