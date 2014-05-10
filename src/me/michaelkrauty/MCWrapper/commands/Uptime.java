package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class Uptime {

	public Uptime() {
		System.out.println("Wrapper Uptime: "
				+ Math.round(Main.wrapper.getUptime() / 1000) + " seconds.");
	}

	public Uptime(int serverid) {
		if (Main.wrapper.getServer(serverid).isRunning()) {
			System.out.println("Server " + serverid + "'s uptime: "
					+ (Main.wrapper.getServer(serverid).getUptime() / 1000)
					+ " seconds.");
		} else {
			System.out.println("Server " + serverid + " isn't running!");
		}
	}
}
