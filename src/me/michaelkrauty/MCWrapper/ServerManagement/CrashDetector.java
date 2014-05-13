package me.michaelkrauty.MCWrapper.ServerManagement;

import org.apache.log4j.Logger;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;
import me.michaelkrauty.MCWrapper.commands.ForceRestart;

public class CrashDetector implements Runnable {

	private final static Logger log = Logger.getLogger(Main.class);

	private static Thread t;
	private static Server server;

	public CrashDetector(Server svr, boolean enabled) {
		server = svr;
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	@Override
	public void run() {
		try {
			while (server.crashDetectionEnabled()) {
				float lastResponse = server.getLastResponse();
				if (lastResponse < (System.currentTimeMillis() - 60000)) {
					log.info("No response in 60 seconds from server "
							+ server.getId() + ", running \"list\" command");
					server.executeCommand("list");
					while (lastResponse < System.currentTimeMillis() - 90000) {
						Thread.sleep(0);
					}
					if (lastResponse < System.currentTimeMillis() - 90000) {
						log.info("Force restarting server " + server.getId());
						new ForceRestart(server.getId());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
