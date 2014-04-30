package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class Stop {

	public Stop(int serverid) {
		Main.wrapper.getServer(serverid).stop();
	}
}
