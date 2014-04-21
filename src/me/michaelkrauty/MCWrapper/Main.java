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
					new Command(in);
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