package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class ServerPID {

	public ServerPID(int serverid) {
		int pid = Main.wrapper.getServer(serverid).getPID();
		System.out.println("Server " + serverid + "'s PID: " + pid);
	}
}
