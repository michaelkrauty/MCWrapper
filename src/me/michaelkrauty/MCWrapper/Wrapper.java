package me.michaelkrauty.MCWrapper;

import java.lang.management.ManagementFactory;

public class Wrapper {

	private final int PID;

	public Wrapper() {
		String pidfull = ManagementFactory.getRuntimeMXBean().getName();
		this.PID = Integer.parseInt(pidfull.replace(
				pidfull.substring(pidfull.lastIndexOf("@")), ""));
	}
	
	public void stopWrapper(){
		System.out.println("Stopping wrapper...");
		this.stopAllServers();
	}
	
	public void startServer(){
		System.out.println("");
	}

	public void stopServer(int serverid) {
		this.getServer(serverid).stop();
	}
	
	public void forceStopServer(int serverid) {
		this.getServer(serverid).forceStop();
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
		return getServer(Integer.parseInt(serverid)).executeCommand(cmd);
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
		System.out.println("Starting server " + serverid);
		Server server = new Server(serverid);
		Main.servers.add(server);
		server.start();
	}

	public void stopAllServers() {
		System.out.println("Stopping all servers...");
		for (int i = 0; i < Main.servers.size(); i++) {
			Main.servers.get(i).stop();
		}
	}

	public Server getServer(int serverid) {
		for (int i = 0; i < Main.servers.size(); i++) {
			if (Main.servers.get(i).getId() == serverid) {
				return Main.servers.get(i);
			}
		}
		return null;
	}
}