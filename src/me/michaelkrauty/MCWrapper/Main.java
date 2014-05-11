package me.michaelkrauty.MCWrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import me.michaelkrauty.MCWrapper.ClientConnection.ConnectionHandler;
import me.michaelkrauty.MCWrapper.commands.Command;

import org.apache.log4j.Logger;

public class Main {

	public static Main main;

	public static Boolean running = true;

	public final static Wrapper wrapper = new Wrapper();

	public final static Config config = new Config();

	private final static Logger log = Logger.getLogger(Main.class);

	// store any server objects created in this list
	public static ArrayList<Server> servers = new ArrayList<Server>();

	public static void main(String[] args) {
		log.info("Initiating wrapper...");
		log.info("Wrapper PID: " + wrapper.getPID());
		new ConnectionHandler().start();
		cycleLogs();
		log.info("done.");
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
		log.info("Loop stopped.");
		System.exit(0);
	}

	private static void cycleLogs() {
		File latestLog = new File("logs/latest.log");
		if (latestLog.exists()) {
			try {
				Date date = new Date(System.currentTimeMillis());
				SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yy");
				String datestr = ft.format(date);
				boolean test = true;
				int logNumber = 0;
				while (test) {
					if (new File("logs/" + datestr + ".zip").exists()) {
						logNumber = 1;
					}
					if (new File("logs/" + datestr + "-" + logNumber + ".zip")
							.exists()) {
						logNumber++;
					}
				}
				String filename = "";
				if (logNumber > 0) {
					filename = "logs/" + datestr + "-" + logNumber + ".zip";
				} else {
					filename = "logs/" + datestr + ".zip";
				}
				ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
						filename));
				out.putNextEntry(new ZipEntry("logs/latest.log"));
				out.write(1);
				out.closeEntry();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}