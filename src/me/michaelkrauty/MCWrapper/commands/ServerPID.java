package me.michaelkrauty.MCWrapper.commands;

import org.apache.log4j.Logger;

import me.michaelkrauty.MCWrapper.Main;

public class ServerPID {

	private final static Logger log = Logger.getLogger(Main.class);

	public ServerPID(int serverid) {
		log.info("Server " + serverid + "'s PID: "
				+ Main.wrapper.getServer(serverid).getPID());
	}
}