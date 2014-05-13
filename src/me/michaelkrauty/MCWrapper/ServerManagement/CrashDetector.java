package me.michaelkrauty.MCWrapper.ServerManagement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import me.michaelkrauty.MCWrapper.Server;
import me.michaelkrauty.MCWrapper.commands.Start;

public class CrashDetector implements Runnable {

	private static Thread t;
	private static Server server;

	public CrashDetector(Server server, boolean enabled) {
		CrashDetector.server = server;
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	@SuppressWarnings("unused")
	@Override
	public void run() {
		float lastResponse = 0;
		OutputStream serverOutputStream = server.getOutputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				server.getInputStream()));
		try {
			while (server.crashDetectionEnabled()) {
				if (br.ready()) {
					lastResponse = System.currentTimeMillis();
				}
				if (lastResponse > (System.currentTimeMillis() + 60000)) {
					server.executeCommand("list");
					while (lastResponse < System.currentTimeMillis() + 90000) {
						Thread.sleep(0);
					}
					if (lastResponse > System.currentTimeMillis() + 90000) {
						server.forceStop();
						while (server.isRunning()) {
							Thread.sleep(0);
						}
						new Start(server.getId());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
