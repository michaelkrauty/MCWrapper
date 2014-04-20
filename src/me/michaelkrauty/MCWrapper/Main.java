package me.michaelkrauty.MCWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		System.out.println("Initiating wrapper...");
		Wrapper wrapper = new Wrapper();
		System.out.println("Wrapper PID: " + wrapper.getPID());
		SQL.checkSqlTables();
		System.out.println("done.");
		Boolean running = true;
		// Config config = new Config();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// Unused
		// double startTime = System.currentTimeMillis();
		// String uptime = Math
		// .round((System.currentTimeMillis() - startTime) / 1000)
		// + " seconds";

		// tick tock goes the clock
		while (running) {
			double loopStartTime = System.currentTimeMillis();
			try {
				if (br.ready()) {
					String[] in = br.readLine().split(" ");
					if (!validCommand(in[0])) {
						System.out
								.println("Unknown command! Use \"help\" for a list of valid commands.");
					}
					if (in[0].equals("stop")) {
						System.out.println("stopping all minecraft servers...");
						wrapper.stopAllServers();
						System.out.println("Bye!");
						running = false;
					}
					if (in[0].equalsIgnoreCase("restart")) {
						// TODO: restart
					}
					if (in[0].equals("test")) {
						System.out.println("test :)");
					}
					if (in[0].equals("help")) {
						System.out.println("test, stop, help, uptime, pid");
					}
					if (in[0].equals("uptime")) {
						System.out.println("Uptime: "
								+ Math.round(wrapper.getUptime() / 1000)
								+ " seconds");
					}
					if (in[0].equalsIgnoreCase("pid")) {
						System.out.println("Wrapper PID: " + wrapper.getPID());
					}
					if (in[0].equalsIgnoreCase("start")) {
						wrapper.startServer(Integer.parseInt(in[1]));
					}
					if (in[0].equalsIgnoreCase("stop")) {

					}
					if (in[0].equalsIgnoreCase("severcommand")) {
						wrapper.issueCommand(in[1], in);
						for (int i = 0; i < in.length; i++) {
							System.out.println(in[i]);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			while ((System.currentTimeMillis() - loopStartTime) <= 200) {
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static boolean validCommand(String in) {
		ArrayList<String> valid = new ArrayList<String>();
		valid.add("stop");
		valid.add("test");
		valid.add("help");
		valid.add("uptime");
		valid.add("pid");
		valid.add("servercommand");
		valid.add("restart");
		if (valid.contains(in)) {
			return true;
		}
		return false;
	}
}