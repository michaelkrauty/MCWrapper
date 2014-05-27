package me.michaelkrauty.MCWrapper.Commands;

import me.michaelkrauty.MCWrapper.Main;
import org.apache.log4j.Logger;

public class Help {

	private final static Logger log = Logger.getLogger(Main.class);

	public Help() {
		String commands = "";
		for (String cmd : Command.commands) {
			commands = commands + cmd + ", ";
		}
		log.info("Commands: " + commands);
	}
}
