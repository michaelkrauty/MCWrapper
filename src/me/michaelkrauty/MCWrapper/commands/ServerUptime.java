package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class ServerUptime {

	public ServerUptime(int serverid) {
		System.out.println("Server " + serverid + "'s uptime: "
				+ (Main.wrapper.getServer(serverid).getUptime() / 1000)
				+ " seconds.");
	}
}
