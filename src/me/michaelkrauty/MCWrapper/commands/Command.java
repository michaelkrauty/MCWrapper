package me.michaelkrauty.MCWrapper.commands;

import java.util.ArrayList;

import me.michaelkrauty.MCWrapper.Main;

public class Command extends Main {

	public Command(String command) {
		if (this.checkValidCommand(command)) {
			if (command.equals("help")) {
				new Help();
			}
			if (command.equals("pid")) {
				new PID();
			}
			if (command.equals("restart")) {
				new Restart();
			}
			if (command.equals("servercommand")) {
				new ServerCommand();
			}
			if (command.equals("start")) {
				new Start();
			}
			if (command.equals("stop")) {
				new Stop();
			}
			if (command.equals("stopwrapper")) {
				new StopWrapper();
			}
			if (command.equals("test")) {
				new Test();
			}
			if (command.equalsIgnoreCase("uptime")) {
				new Uptime();
			}
		} else {
			System.out
					.println("Unknown command! Use \"help\" for a list of valid commands.");
		}
	}

	private boolean checkValidCommand(String command) {
		ArrayList<String> commands = new ArrayList<String>();

		/** commands */
		commands.add("help");
		commands.add("pid");
		commands.add("restart");
		commands.add("servercommand");
		commands.add("start");
		commands.add("stop");
		commands.add("stopwrapper");
		commands.add("test");
		commands.add("uptime");

		if (commands.contains(command.toLowerCase())) {
			return true;
		}
		return false;
	}
}
