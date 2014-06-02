package me.michaelkrauty.MCWrapper.ServerUtil;

import me.michaelkrauty.MCWrapper.Commands.ForceRestart;
import me.michaelkrauty.MCWrapper.Commands.ServerCommand;
import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;
import me.michaelkrauty.MCWrapper.ServerInstance;
import org.apache.log4j.Logger;

/**
 * Created by michael on 5/27/14.
 */
public class CrashDetector implements Runnable {

	private static final Logger log = Logger.getLogger(Main.class);

	private static Thread t;
	private final ServerInstance serverInstance;

	public CrashDetector(ServerInstance serverInstance) {
		this.serverInstance = serverInstance;
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public void run() {
		int check = 10;
		int strike = 0;
		while (serverInstance.getServer() == null) {
			try {
				t.sleep(1000);
				log.info("sleeping...");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Server server = serverInstance.getServer();
		long lastCycle = 0;
		while (server.isRunning()) {
			if (lastCycle == serverInstance.getLastResponse()) {
				if (strike > 2) {
					log.info("Server " + server.getId() + " is not responding (strike 3), forcing restart...");
					new ForceRestart(server.getId());
				}
				if (strike == 2) {
					strike++;
					log.info("No new output from server " + server.getId() + " (strike 2), running \"list\" command...");
					new ServerCommand(new String[] {"servercommand", Integer.toString(server.getId()), "list"});
				}
				if (strike == 1) {
					strike++;
				}
				if (strike == 0) {
					strike++;
				}
			} else {
				strike = 0;
				lastCycle = serverInstance.getLastResponse();
			}
			try {
				t.sleep(check * 1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}