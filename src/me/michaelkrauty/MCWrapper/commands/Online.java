package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class Online {

	public Online(int serverid) {
		if (Main.wrapper.getServer(serverid).isRunning()
				&& Main.wrapper.getServer(serverid).isOnline()) {
			System.out.println("Server is online and running.");
		}
		if (Main.wrapper.getServer(serverid).isRunning()) {
			System.out.println("Server is running.");
		}
		if (Main.wrapper.getServer(serverid).isOnline()) {
			System.out.println("Server is online.");
		} else {
			System.out.println("Server is offline.");
		}
	}

	public Online() {
		if (Main.servers.size() > 0) {
			String online = "";
			String running = "";
			for (int i = 0; i < Main.servers.size(); i++) {
				if (Main.servers.get(i).isOnline()) {
					if (i == Main.servers.size()) {
						online = online + Main.servers.get(i).getId() + ".";
					} else {
						online = online + Main.servers.get(i).getId() + ", ";
					}
				}
				if (Main.servers.get(i).isRunning()) {
					if (i == Main.servers.size()) {
						running = running + Main.servers.get(i).getId() + ".";
					} else {
						running = running + Main.servers.get(i).getId() + ", ";
					}
				}
			}
			System.out.println("Running servers: " + running);
			System.out.println("Online servers: " + online);
		} else {
			System.out.println("No online servers!");
		}
	}

}
