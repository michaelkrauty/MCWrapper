package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class ServerPID {

	public ServerPID(int serverid) {
		System.out.println("Server " + serverid + "'s PID: "
				+ Main.wrapper.getServer(serverid).getPID());
	}
}