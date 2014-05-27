package me.michaelkrauty.MCWrapper.Commands;

import me.michaelkrauty.MCWrapper.Main;
import org.apache.log4j.Logger;

public class PID {

	private final static Logger log = Logger.getLogger(Main.class);

	public PID() {
		log.info("Wrapper PID: " + Main.wrapper.getPID());
	}

}
