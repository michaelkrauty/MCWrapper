package me.michaelkrauty.MCWrapper;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created on 5/26/2014.
 *
 * @author michaelkrauty
 */
public class ServerInstance implements Runnable {

	private final static Logger log = Logger.getLogger(Main.class);

	private Thread t;
	private final int serverid;

	public ServerInstance(int serverid) {
		this.serverid = serverid;
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public void run() {
		Server server = Main.wrapper.getServer(serverid);
		if (server != null) {
			;
		} else {
			server = null;
			server = new Server(serverid);
			Main.servers.add(server);
		}
		server.start();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				server.getInputStream()));
		String line;
		try {
			while ((line = in.readLine()) != null) {
				log.info("Server " + serverid + ": " + line);
			}
			in.close();
		} catch (IOException ignored) {
		}
	}
}
