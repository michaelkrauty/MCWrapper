package me.michaelkrauty.MCWrapper;

import me.michaelkrauty.MCWrapper.ClientConnection.ConnectionHandler;
import me.michaelkrauty.MCWrapper.Commands.Command;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

	public static Main main;

	public static Boolean running = true;

	public final static Wrapper wrapper = new Wrapper();

	public final static Config config = new Config();

	private static Logger log = Logger.getLogger(Main.class);

	// store any server objects created in this list
	public static ArrayList<Server> servers = new ArrayList<Server>();

	public static void main(String[] args) {
		checkFiles();
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

				// attempting to reduce unnecessary CPU load
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

	private static void checkFiles() {
		cycleLogs();
		checkDirs();
	}

	private static void cycleLogs() {
		File latestLog = new File("logs/latest.log");
		if (latestLog.exists()) {
			try {
				String date = new SimpleDateFormat("MM-dd-yy").format(new Date(
						System.currentTimeMillis()));
				int logNumber = 0;
				while (true) {
					if (new File("logs/" + date + "-" + logNumber + ".zip")
							.exists()) {
						logNumber++;
					} else {
						break;
					}
				}
				String filename = "logs/" + date + "-" + logNumber;
				byte[] buffer = new byte[1024];
				ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
						filename + ".zip"));
				zos.putNextEntry(new ZipEntry("logs/" + filename + ".log"));
				FileInputStream in = new FileInputStream("logs/latest.log");
				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				in.close();
				zos.closeEntry();
				zos.close();
				latestLog.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void checkDirs() {
		File serverdir = new File("servers");
		if (!serverdir.exists()) {
			serverdir.mkdir();
		}
	}
}