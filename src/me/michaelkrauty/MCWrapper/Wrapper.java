package me.michaelkrauty.MCWrapper;

import java.lang.management.ManagementFactory;

public class Wrapper {

	private int PID;

	public Wrapper() {
		String pidfull = ManagementFactory.getRuntimeMXBean().getName();
		this.PID = Integer.parseInt(pidfull.replace(
				pidfull.substring(pidfull.lastIndexOf("@")), ""));
	}

	public int getPID() {
		return this.PID;
	}

	public long getUptime() {
		return ManagementFactory.getRuntimeMXBean().getUptime();
	}

	public boolean issueCommand(String serverid, String[] command) {
		String cmd = "";
		for (int i = 0; i < command.length; i++) {
			if (i - 1 == command.length) {
				cmd = cmd + command[i];
			} else {
				cmd = cmd + command[i] + " ";
			}
		}
		Server server = new Server(Integer.parseInt(serverid));
		return server.executeCommand(cmd);
	}

	public boolean checkOnlineState(int serverid) {
		Server server = new Server(serverid);
		if (server.exists()) {
			return server.isOnline();
		}
		return false;
	}

	public String[] getOnlineServers() {
		// TODO
		String[] test = null;
		return test;
	}

	public void startServer(int serverid) {
		// TODO
	}

	public void startAllServers() {
		// TODO
	}

	public void stopAllServers() {
		// TODO
	}
}