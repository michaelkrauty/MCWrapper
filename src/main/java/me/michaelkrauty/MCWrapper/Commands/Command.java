package me.michaelkrauty.MCWrapper.Commands;

import me.michaelkrauty.MCWrapper.Main;
import org.apache.log4j.Logger;

public class Command implements Runnable {

	private final static Logger log = Logger.getLogger(Main.class);

	private Thread t;

	private static String command;

	public static final String[] commands = new String[]{
			"help",
			"pid",
			"restart",
			"forcerestart",
			"reload",
			"servercommand",
			"sc",
			"start",
			"stop",
			"stopwrapper",
			"test",
			"uptime",
			"forcestop",
			"kill",
			"online",
			"stopall",
			"forcestopall",
			"killall",
			"backup"
	};

	// create a new thread to run the command
	public Command(String cmd) {
		command = cmd;
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	// is the command valid?
	boolean checkValidCommand(String cmdIn) {
		for (String cmd : commands) {
			if (cmd.equalsIgnoreCase(cmdIn)) {
				return true;
			}
		}
		return false;
	}

	// run the thread
	@Override
	public void run() {
		String[] cmd = command.split(" ");
		String cmdLabel = cmd[0];
		if (this.checkValidCommand(cmdLabel)) {

			try {
				// no params
				if (cmdLabel.equalsIgnoreCase("help") && cmd.length == 1) {
					new Help();
				}
				if (cmdLabel.equalsIgnoreCase("pid") && cmd.length == 1) {
					new PID();
				}
				if (cmdLabel.equalsIgnoreCase("stopwrapper") && cmd.length == 1) {
					new StopWrapper();
				}
				if (cmdLabel.equalsIgnoreCase("test") && cmd.length == 1) {
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
				if ((cmdLabel.equalsIgnoreCase("servercommand")
						&& cmd.length > 1) || (cmdLabel.equalsIgnoreCase("sc") && cmd.length > 1)) {
					new ServerCommand(cmd);
				}
				int serverid = Integer.parseInt(cmd[1]);
				if (cmdLabel.equalsIgnoreCase("start") && cmd.length == 2) {
					new Start(serverid);
				}
				if (cmdLabel.equalsIgnoreCase("stop") && cmd.length == 2) {
					new Stop(serverid);
				}
				if ((cmdLabel.equalsIgnoreCase("forcestop") || cmdLabel
						.equalsIgnoreCase("kill")) && cmd.length == 2) {
					new ForceStop(serverid);
				}
				if (cmdLabel.equalsIgnoreCase("restart") && cmd.length == 2) {
					new Restart(serverid);
				}
				if (cmdLabel.equalsIgnoreCase("forcerestart")
						&& cmd.length == 2) {
					new ForceRestart(serverid);
				}
				if (cmdLabel.equalsIgnoreCase("reload") && cmd.length == 2) {
					new Reload(serverid);
				}
				if (cmdLabel.equalsIgnoreCase("online") && cmd.length == 2) {
					new Online(serverid);
				}
				if (cmdLabel.equalsIgnoreCase("uptime") && cmd.length == 2) {
					new Uptime(serverid);
				}
				if (cmdLabel.equalsIgnoreCase("backup") && cmd.length == 2) {
					new Backup(serverid, cmd);
				}
			} catch (Exception ignored) {
			}
		} else {
			log.info("Unknown command! Use \"help\" for a list of valid commands.");
		}
	}
}
