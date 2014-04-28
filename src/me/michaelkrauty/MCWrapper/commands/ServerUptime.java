package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class ServerUptime {

	public ServerUptime(int serverid) {
		Main.wrapper.getServer(serverid).getUptime();
	}
}
