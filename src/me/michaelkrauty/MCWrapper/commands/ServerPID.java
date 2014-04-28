package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;

public class ServerPID {

	public ServerPID(int serverid) {
		Server server = Main.wrapper.getServer(serverid);
		System.out.println("Server " + server.getId() + "'s PID: " + server.getPID());
	}
}
