package me.michaelkrauty.MCWrapper;

import me.michaelkrauty.MCWrapper.ClientConnection.ConnectionHandler;
import me.michaelkrauty.MCWrapper.Commands.Command;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static Main main;

	public static Boolean running = true;

	public final static Wrapper wrapper = new Wrapper();

	private static final Logger log = Logger.getLogger(Main.class);

	// store any server objects created in this list
	public static ArrayList<Server> servers = new ArrayList<Server>();

	public static int userid;
	public static String password;

	public static void main(String[] args) {
		if (args.length == 0) {
			log.info("Incorrect startup args.");
			System.exit(1);
		}
		for (int i = 0; i < args.length; i++) {
			try {
				if (args[i].equalsIgnoreCase("--userid") || args[i].equalsIgnoreCase("-u")) {
					userid = Integer.parseInt(args[i + 1]);
				}
				if (args[i].equalsIgnoreCase("--password") || args[i].equalsIgnoreCase("-p")) {
					password = args[i + 1];
				}
			} catch (Exception e) {
				log.error("Incorrect startup args!\nExample startup command: \"java -jar MCWrapper.jar --userid 1 --password 123\"");
				System.exit(1);
			}
		}
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