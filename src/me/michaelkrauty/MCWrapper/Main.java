package me.michaelkrauty.MCWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import me.michaelkrauty.MCWrapper.commands.Command;

public class Main {

	public static Main main;

	public static Boolean running = true;

	public static Wrapper wrapper = new Wrapper();

	public static void main(String[] args) {
		System.out.println("Initiating wrapper...");
		System.out.println("Wrapper PID: " + wrapper.getPID());
		SQL.checkSqlTables();
		System.out.println("done.");
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
					String in = br.readLine();
					in.split(" ");
					new Command(in, args);
					/*
					 * if (!validCommand(in[0])) { System.out .println(
					 * "Unknown command! Use \"help\" for a list of valid commands."
					 * ); } if (in[0].equalsIgnoreCase("restart")) { // TODO:
					 * restart } if (in[0].equals("test")) {
					 * System.out.println("test :)"); } if
					 * (in[0].equals("help")) {
					 * System.out.println("test, stop, help, uptime, pid"); } if
					 * (in[0].equals("uptime")) { System.out.println("Uptime: "
					 * + Math.round(wrapper.getUptime() / 1000) + " seconds"); }
					 * if (in[0].equalsIgnoreCase("pid")) {
					 * System.out.println("Wrapper PID: " + wrapper.getPID()); }
					 * if (in[0].equalsIgnoreCase("start")) { boolean isInt =
					 * true; try { Integer.parseInt(in[1]); } catch
					 * (NumberFormatException e) { isInt = false; } if (isInt) {
					 * wrapper.startServer(Integer.parseInt(in[1])); } else {
					 * System.out.println("argument must be an int"); } } if
					 * (in[0].equalsIgnoreCase("severcommand")) {
					 * wrapper.issueCommand(in[1], in); for (int i = 0; i <
					 * in.length; i++) { System.out.println(in[i]); } }
					 */
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
}