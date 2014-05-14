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
			while ((server.getLastResponse() < System.currentTimeMillis() - 60000)
					&& server.crashDetectionEnabled()) {
				Thread.sleep(0);
			}
			log.info("No response in 60 seconds from server " + server.getId()
					+ ", running \"list\" command");
			server.executeCommand("list");
			boolean derp = true;
			while (derp) {
				if (server.getLastResponse() < System.currentTimeMillis() - 90000) {
					new ForceRestart(server.getId());
				} else if (server.getLastResponse() > System
						.currentTimeMillis() - 60000) {
					derp = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
