package me.michaelkrauty.MCWrapper;

import me.michaelkrauty.MCWrapper.ClientConnection.ConnectionHandler;
import me.michaelkrauty.MCWrapper.Commands.Command;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

public class Main {

	public static Main main;

	public static Boolean running = true;

	public final static Wrapper wrapper = new Wrapper();

	public final static Config config = new Config();

	private static final Logger log = Logger.getLogger(Main.class);

	// store any server objects created in this list
	public static ArrayList<Server> servers = new ArrayList<Server>();

	public static void main(String[] args) {
		checkDirs();
        boolean sqlres = SQL.checkTables();
        if (!sqlres) {
            System.exit(1);
        }
		log.info("Wrapper PID: " + wrapper.getPID());
		new ConnectionHandler().start();
		// start main loop
		mainLoop();
	}

	private static void mainLoop() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// tick tock goes the clock
		// double loopStartTime = System.currentTimeMillis();
		try {
			log.info("Ready...");
			while (running) {
				if (br.ready()) {
					new Command(br.readLine());
				}

				// Pause 1 second at the end of the loop to prevent unnecessary CPU usage
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("Loop stopped.");
		System.exit(0);
	}

	private static void checkDirs() {
		File serverdir = new File("servers");
		if (!serverdir.exists()) {
			serverdir.mkdir();
		}
	}
}