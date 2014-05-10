package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

import org.apache.log4j.Logger;

public class Help {

	private final static Logger log = Logger.getLogger(Main.class);

	public Help() {
		String commands = "";
		for (int i = 0; i < Command.commands.size(); i++) {
			if (i == Command.commands.size()) {
				commands = commands + Command.commands.get(i) + ".";
			} else {
				commands = commands + Command.commands.get(i) + ", ";
			}
		}
		log.info("Commands: " + commands);
	}
}
