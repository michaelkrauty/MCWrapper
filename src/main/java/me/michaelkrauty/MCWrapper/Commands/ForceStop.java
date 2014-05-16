package me.michaelkrauty.MCWrapper.Commands;

import me.michaelkrauty.MCWrapper.Main;

public class ForceStop {

	public ForceStop(int serverid) {
		Main.wrapper.getServer(serverid).forceStop();
	}
}
