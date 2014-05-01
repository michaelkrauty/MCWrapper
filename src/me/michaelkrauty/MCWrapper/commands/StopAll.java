package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class StopAll {

	public StopAll() {
		for (String serverid : Main.wrapper.getOnlineServers()) {
			Main.wrapper.getServer(Integer.parseInt(serverid)).stop();
		}
	}
}
