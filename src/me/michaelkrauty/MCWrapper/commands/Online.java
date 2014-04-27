package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class Online {

	public Online(int serverid) {
		if (Main.wrapper.getServer(serverid).isRunning()
				&& Main.wrapper.getServer(serverid).isOnline()) {
			System.out.println("Server is online and running.");
		} else if (Main.wrapper.getServer(serverid).isRunning()) {
			System.out.println("Server is running.");
		} else if (Main.wrapper.getServer(serverid).isOnline()) {
			System.out.println("Server is online.");
		} else {
			System.out.println("Server is offline.");
		}
	}

}
