package me.michaelkrauty.MCWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.sun.istack.internal.logging.Logger;

import me.michaelkrauty.MCWrapper.ClientConnection.ConnectionHandler;
import me.michaelkrauty.MCWrapper.commands.Command;

public class Main {

	public static Main main;

	public static Boolean running = true;

	public final static Wrapper wrapper = new Wrapper();

	public final static Config config = new Config();

	private static final Logger log = Logger.getLogger(Main.class);

	// store any server objects created in this list
	public static ArrayList<Server> servers = new ArrayList<Server>();

	public static void main(String[] args) {
		System.out.println("Initiating wrapper...");
		System.out.println("Wrapper PID: " + wrapper.getPID());
		new ConnectionHandler().start();
		log.info("test");
		System.out.println("done.");

		// start main loop
		mainLoop();
	}

	private static void mainLoop() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// tick tock goes the clock
		// double loopStartTime = System.currentTimeMillis();
		try {
			while (running) {
				if (br.ready()) {
					new Command(br.readLine());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// while ((System.currentTimeMillis() - loopStartTime) <= 200) {
		// try {
		// Thread.sleep(0);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		System.out.println("Loop stopped.");
		System.exit(0);
	}
}