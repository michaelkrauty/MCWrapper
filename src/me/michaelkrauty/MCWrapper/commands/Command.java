package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class Command extends Main {

	public Command(String command, String[] args) {
		if (this.checkValidCommand(command)) {
			if (command.equalsIgnoreCase("stop")) {
				new StopWrapper();
			}
		}
	}

	private boolean checkValidCommand(String command) {
		// TODO
		return true;
	}

}
