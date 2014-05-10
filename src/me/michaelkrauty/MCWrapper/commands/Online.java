package me.michaelkrauty.MCWrapper.commands;

import org.apache.log4j.Logger;

import me.michaelkrauty.MCWrapper.Main;

public class Online {

	private final static Logger log = Logger.getLogger(Main.class);

	public Online(int serverid) {
		if (Main.wrapper.getServer(serverid).isRunning()
				&& Main.wrapper.getServer(serverid).isOnline()) {
			log.info("Server is online and running.");
		}
		if (Main.wrapper.getServer(serverid).isRunning()) {
			log.info("Server is running.");
		}
		if (Main.wrapper.getServer(serverid).isOnline()) {
			log.info("Server is online.");
		} else {
			log.info("Server is offline.");
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
			log.info("Running servers: " + running);
			log.info("Online servers: " + online);
		} else {
			log.info("No online servers!");
		}
	}

}
