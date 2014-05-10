package me.michaelkrauty.MCWrapper.commands;

import org.apache.log4j.Logger;

import me.michaelkrauty.MCWrapper.Main;

public class PID {

	private final static Logger log = Logger.getLogger(Main.class);

	public PID() {
		log.info("Wrapper PID: " + Main.wrapper.getPID());
	}

}
