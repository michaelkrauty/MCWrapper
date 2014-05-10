package me.michaelkrauty.MCWrapper.commands;

import org.apache.log4j.Logger;

import me.michaelkrauty.MCWrapper.*;

public class StopWrapper {

	private final static Logger log = Logger.getLogger(Main.class);

	public StopWrapper() {
		log.info("Stopping...");
		Main.wrapper.stopWrapper();
		Main.running = false;
	}
}
