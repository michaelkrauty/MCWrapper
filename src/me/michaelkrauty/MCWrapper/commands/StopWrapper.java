package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.*;

public class StopWrapper {

	public StopWrapper() {
		System.out.println("Stopping...");
		Main.wrapper.stop();
		Main.running = false;
	}

}
