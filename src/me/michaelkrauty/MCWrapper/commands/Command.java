package me.michaelkrauty.MCWrapper.commands;

import java.util.ArrayList;

public class Command implements Runnable {

	private Thread t;

	private static String command;

	public static ArrayList<String> commands = new ArrayList<String>();

	public Command(String cmd) {
		command = cmd;
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public boolean checkValidCommand(String command) {
		return commands.contains(command.toLowerCase());
	}

	private void commands() {
		if (commands.isEmpty()) {
			/** @commands */
			commands.add("help");
			commands.add("pid");
			// commands.add("restart");
			commands.add("servercommand");
			commands.add("start");
			commands.add("stop");
			commands.add("stopwrapper");
			commands.add("test");
			commands.add("uptime");
			commands.add("forcestop");
			commands.add("kill");
			commands.add("online");
			commands.add("serveruptime");
			commands.add("serverpid");
			commands.add("stopall");
			commands.add("forcestopall");
			commands.add("killall");
		}
	}

	@Override
	public void run() {
		commands();
		String[] cmd = command.split(" ");
		String cmdLabel = cmd[0];
		if (this.checkValidCommand(cmdLabel)) {

			try {
				// no params
				if (cmdLabel.equals("help") && cmd.length == 1) {
					new Help();
				}
				if (cmdLabel.equals("pid") && cmd.length == 1) {
					new PID();
				}
				if (cmdLabel.equals("stopwrapper") && cmd.length == 1) {
					new StopWrapper();
				}
				if (cmdLabel.equals("test") && cmd.length == 1) {
					new Test();
				}
				if (cmdLabel.equalsIgnoreCase("uptime") && cmd.length == 1) {
					new Uptime();
				}
				if (cmdLabel.equalsIgnoreCase("online") && cmd.length == 1) {
					new Online();
				}
				if (cmdLabel.equalsIgnoreCase("stopall") && cmd.length == 1) {
					new StopAll();
				}
				if ((cmdLabel.equalsIgnoreCase("forcestopall") || cmdLabel
						.equalsIgnoreCase("killall")) && cmd.length == 1) {
					new KillAll();
				}

				// params
				if (cmdLabel.equals("servercommand") && cmd.length > 1) {
					new ServerCommand(cmd);
				}
				int serverid = Integer.parseInt(cmd[1]);
				if (cmdLabel.equals("start") && cmd.length == 2) {
					new Start(serverid);
				}
				if (cmdLabel.equals("stop") && cmd.length == 2) {
					new Stop(serverid);
				}
				if ((cmdLabel.equalsIgnoreCase("forcestop") || cmdLabel
						.equalsIgnoreCase("kill")) && cmd.length == 2) {
					new ForceStop(serverid);
				}
				if (cmdLabel.equals("restart") && cmd.length == 2) {
					new Restart(serverid);
				}
				if (cmdLabel.equalsIgnoreCase("online") && cmd.length == 2) {
					new Online(serverid);
				}
				if (cmdLabel.equalsIgnoreCase("uptime") && cmd.length == 2) {
					new Uptime(serverid);
				}
				if (cmdLabel.equalsIgnoreCase("serverpid") && cmd.length == 2) {
					new ServerPID(serverid);
				}

			} catch (Exception ignored) {
			}
		} else {
			System.out
					.println("Unknown command! Use \"help\" for a list of valid commands.");
		}
	}
}
