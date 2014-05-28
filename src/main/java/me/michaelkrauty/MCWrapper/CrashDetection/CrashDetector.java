package me.michaelkrauty.MCWrapper.CrashDetection;

import me.michaelkrauty.MCWrapper.Commands.ForceRestart;
import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;
import me.michaelkrauty.MCWrapper.ServerInstance;
import org.apache.log4j.Logger;

/**
 * Created by michael on 5/27/14.
 */
public class CrashDetector implements Runnable {

	private static Logger log = Logger.getLogger(Main.class);

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
		int threshold1 = 10;
		int threshold2 = 20;
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
		while (server.isRunning()) {
			long currentTime = System.currentTimeMillis();
			if (!server.isOnline()) {
				if (strike <= 3) {
					strike++;
					log.info("Server " + server.getId() + " is not responding (strike " + strike + ")");
				} else {
					log.info("Server " + server.getId() + " is not responding (strike " + strike + "), forcing restart...");
					new ForceRestart(server.getId());
				}
			} else {
				strike = 0;
				log.info("Server " + server.getId() + " is online (strike " + strike + ")");
			}
			try {
				t.sleep(20000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}