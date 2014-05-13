package me.michaelkrauty.MCWrapper.ServerManagement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;

public class ServerLastResponse implements Runnable {

	private final static Logger log = Logger.getLogger(Main.class);

	private static Thread t;
	private static Server server;

	float lastResponse = 0;

	public ServerLastResponse(Server svr) {
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
		BufferedReader br = new BufferedReader(new InputStreamReader(
				server.getInputStream()));
		try {
			while (br.ready()) {
				lastResponse = System.currentTimeMillis();
				log.info("last response: " + lastResponse + " (Server "
						+ server.getId() + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public float getLastResponse() {
		return lastResponse;
	}
}
