package me.michaelkrauty.MCWrapper.Commands;

import me.michaelkrauty.MCWrapper.Main;

import org.apache.log4j.Logger;

public class Uptime {

	private final static Logger log = Logger.getLogger(Main.class);

	public Uptime() {
		log.info("Wrapper Uptime: "
				+ Math.round(Main.wrapper.getUptime() / 1000) + " seconds.");
	}

	public Uptime(int serverid) {
		if (Main.wrapper.getServer(serverid).isRunning()) {
			log.info("Server " + serverid + "'s uptime: "
					+ (Main.wrapper.getServer(serverid).getUptime() / 1000)
					+ " seconds.");
		} else {
			log.info("Server " + serverid + " isn't running!");
		}
	}
}
