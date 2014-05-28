package me.michaelkrauty.MCWrapper.Commands;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;
import org.apache.log4j.Logger;

public class ServerCommand {

	private final static Logger log = Logger.getLogger(Main.class);

	public ServerCommand(String[] cmd) {
		try {
			int serverid = Integer.parseInt(cmd[1]);
			String out = "";
			for (int i = 2; i < cmd.length; i++) {
				if (i == cmd.length - 2) {
					out = out + cmd[i];
				} else {
					out = out + cmd[i] + " ";
				}
			}
			Server server = Main.wrapper.getServer(serverid);
			server.executeCommand(out);
			log.info("Command sent to server " + server.getId() + ": " + out);
		} catch (Exception e) {
			log.info("Server ID must be an int > 0!");
		}
	}

}
