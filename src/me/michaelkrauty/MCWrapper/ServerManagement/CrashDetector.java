package me.michaelkrauty.MCWrapper.ServerManagement;

import me.michaelkrauty.MCWrapper.Server;

public class CrashDetector implements Runnable {

	private static Thread t;
	private static Server server;
	@SuppressWarnings("unused")
	private static Process p;

	public CrashDetector(Server server) {
		CrashDetector.server = server;
		p = server.getProcess();
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	@Override
	public void run() {
		while (server.isRunning() && server.crashDetectionEnabled()) {
			// TODO: if server doesn't output any lines within 60 seconds,
			// execute command "list". if no response within 30 seconds, restart
			// the server.
		}
	}
}
