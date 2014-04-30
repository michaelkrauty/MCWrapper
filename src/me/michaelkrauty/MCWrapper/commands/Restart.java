package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class Restart {

	public Restart(int serverid) {
		Main.wrapper.getServer(serverid).restart();
	}
}
