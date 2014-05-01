package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class StopAll {

	public StopAll() {
		for (int i = 0; i < Main.wrapper.getOnlineServers().length; i++) {
			Main.wrapper.getServer(i).stop();
		}
	}
}
