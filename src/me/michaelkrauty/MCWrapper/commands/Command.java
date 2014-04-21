package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class Command extends Main {

	public Command(String command, String[] args) {
		if (this.checkValidCommand(command)) {
			if (command.equalsIgnoreCase("stop")) {
				new StopWrapper();
			}
		} else {
			System.out
					.println("Unknown command! Use \"help\" for a list of valid commands.");
		}
	}

	private boolean checkValidCommand(String command) {
		if (command.equalsIgnoreCase("stop")) {
			return true;
		}
		return false;
	}

}
