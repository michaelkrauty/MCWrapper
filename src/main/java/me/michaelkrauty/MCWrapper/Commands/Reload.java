package me.michaelkrauty.MCWrapper.Commands;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;
import org.apache.log4j.Logger;

/**
 * Created by Michael on 5/17/2014.
 */
public class Reload {

	private final static Logger log = Logger.getLogger(Main.class);

	public Reload(int serverid) {
		Server server = Main.wrapper.getServer(serverid);
		server.executeCommand("reload");
		log.info("Server " + serverid + " reloaded.");
	}
}
