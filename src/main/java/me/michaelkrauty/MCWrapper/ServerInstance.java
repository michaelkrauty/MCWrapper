package me.michaelkrauty.MCWrapper;

import me.michaelkrauty.MCWrapper.CrashDetection.CrashDetector;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created on 5/26/2014.
 *
 * @author michaelkrauty
 */
public class ServerInstance implements Runnable {

	private final static Logger log = Logger.getLogger(Main.class);

	private Thread t;
	private final int serverid;
	private static Server server;
	private static InputStream serverIn;
	private static long lastResponse;

	public ServerInstance(int serverid) {
		this.serverid = serverid;
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public void run() {
		Server server = Main.wrapper.getServer(serverid);
		if (server == null) {
			server = new Server(serverid);
			Main.servers.add(server);
		}
		this.server = server;
		server.start();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				server.getInputStream()));
		new CrashDetector(this);
		String line;
		try {
			while ((line = in.readLine()) != null) {
				log.info("Server " + serverid + ": " + line);
				lastResponse = System.currentTimeMillis();
			}
			in.close();
		} catch (IOException ignored) {
		}
	}

	public Server getServer() {
		return server;
	}

	public long getLastResponse() {
		return lastResponse;
	}
}
