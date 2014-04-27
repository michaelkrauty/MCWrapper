package me.michaelkrauty.MCWrapper.commands;

import java.util.ArrayList;

import me.michaelkrauty.MCWrapper.Main;

public class Command extends Main {

	public Command(String command) {
		String[] cmd = command.split(" ");
		String cmdLabel = cmd[0];
		if (this.checkValidCommand(cmdLabel)) {
			if (cmdLabel.equals("help")) {
				new Help();
			}
			if (cmdLabel.equals("pid")) {
				new PID();
			}
			if (cmdLabel.equals("stopwrapper")) {
				new StopWrapper();
			}
			if (cmdLabel.equals("test")) {
				new Test();
			}
			if (cmdLabel.equalsIgnoreCase("uptime")) {
				new Uptime();
			}
			try {
				if (cmdLabel.equals("restart")) {
					new Restart(cmd[1]);
				}
				if (cmdLabel.equals("servercommand")) {
					new ServerCommand(cmd);
				}
				if (cmdLabel.equals("start")) {
					new Start(cmd[1]);
				}
				if (cmdLabel.equals("stop")) {
					new Stop(cmd[1]);
				}
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Incorrect usage! Use \"help\" for a list of valid commands.");
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
