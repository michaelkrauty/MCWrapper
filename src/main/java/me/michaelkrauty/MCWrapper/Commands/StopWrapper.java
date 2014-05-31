package me.michaelkrauty.MCWrapper.Commands;

import me.michaelkrauty.MCWrapper.Main;
import org.apache.log4j.Logger;

class StopWrapper {

	private final static Logger log = Logger.getLogger(Main.class);

	public StopWrapper() {
		log.info("Stopping...");
		Main.wrapper.stopWrapper();
		Main.running = false;
	}
}
