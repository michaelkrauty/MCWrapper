package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class ForceStop {

	public ForceStop(int serverid) {
		Main.wrapper.getServer(serverid).forceStop();
	}
}
