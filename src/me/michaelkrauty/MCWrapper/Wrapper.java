package me.michaelkrauty.MCWrapper;

import java.lang.management.ManagementFactory;

public class Wrapper {

	private int PID;

	public Wrapper() {
		String pidfull = ManagementFactory.getRuntimeMXBean().getName();
		this.PID = Integer.parseInt(pidfull.replace(
				pidfull.substring(pidfull.lastIndexOf("@")), ""));
	}

	public void stop() {
		System.out.println("Wrapper shutting down!");
		this.stopAllServers();
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

	public long getServerUptime(int serverid) {
		return 0;
		// TODO
	}

	public String[] getOnlinePlayers(int serverid) {
		Server server = new Server(serverid);
		return server.getOnlinePlayers();
	}

	public String[] getOnlineServers() {
		// TODO
		String[] test = null;
		return test;
	}

	public void startServer(int serverid) {
		Server server = new Server(serverid);
		server.start();
	}

	public void startAllServers() {
		// TODO
	}

	public void stopAllServers() {
		// TODO
	}
}