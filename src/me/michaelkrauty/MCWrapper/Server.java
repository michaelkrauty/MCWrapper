package me.michaelkrauty.MCWrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import javax.net.SocketFactory;

public class Server {

	private boolean exists = true;
	private final int id;
	private int PID;
	private String host;
	private int port;
	private String serverdir;
	private Process process;
	private InputStream inputstream;
	private OutputStream outputstream;
	private boolean running;

	public Server(int id) {
		this.serverdir = "/home/mcwrapper/servers/" + id;
		File serverdir = new File(this.serverdir);
		if (!serverdir.isDirectory()) {
			this.exists = false;
		}
		this.id = id;

		// TODO: TEMP!
		this.host = "dominationvps.com";
		this.port = 56434;

		this.inputstream = null;
		this.outputstream = null;
	}

	public boolean exists() {
		return this.exists;
	}

	@SuppressWarnings("unused")
	private ArrayList<String> getDBServer() {
		return SQL.getServer(this.id);
	}

	public void start() {
		System.out.println("Starting server " + this.id + "...");
		try {
			ProcessBuilder pb = new ProcessBuilder("java", "-jar",
					"/home/mcwrapper/jar/test.jar");
			pb.directory(new File(this.serverdir));
			Process p = pb.start();
			this.setProcess(p);
			File pidfile = new File("/home/mcwrapper/pid/" + this.id + "."
					+ this.PID);
			pidfile.createNewFile();
			this.inputstream = p.getInputStream();
			this.outputstream = p.getOutputStream();
		} catch (IOException e) {
			System.out.println("Server directory or jar file not found!");
			e.printStackTrace();
		}
		consoleLoop();
	}

	private void consoleLoop() {
		String line = null;
		BufferedReader input = new BufferedReader(new InputStreamReader(
				this.inputstream));
		while (running) {
			try {
				if ((line = input.readLine()) != null) {
					Main.console.add("Server" + this.id + ": " + line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int getId() {
		return this.id;
	}

	public void stop() {
		this.running = false;
		this.executeCommand("stop");
	}

	public void forceStop() {
		this.running = false;
		this.process.destroy();
	}

	public InputStream getInputStream() {
		return this.inputstream;
	}

	public OutputStream getOutputStream() {
		return this.outputstream;
	}

	private void setProcess(Process p) {
		this.process = p;
	}

	public Process getProcess() {
		return this.process;
	}

	public int getPID() {
		if (this.exists) {
			return this.PID;
		}
		return 0;
	}

	public String getHost() {
		if (this.exists) {
			return this.host;
		}
		return null;
	}

	public int getPort() {
		if (this.exists) {
			return this.port;
		}
		return 0;
	}

	public String getServerDir() {
		if (this.exists) {
			return this.serverdir;
		}
		return null;
	}

	public boolean executeCommand(String command) {
		if (this.exists) {
			PrintWriter out = new PrintWriter(this.process.getOutputStream(),
					true);
			out.println(command);
			return true;
		}
		return false;
	}

	public boolean isOnline() {
		boolean open = true;
		if (this.exists) {
			Socket socket;
			try {
				socket = SocketFactory.getDefault().createSocket();
				try {
					socket.setSoTimeout(5000);
					socket.connect(new InetSocketAddress(this.getHost(), this
							.getPort()));
					socket.close();
				} catch (Exception e) {
					open = false;
					System.err.println(e);
				}
			} catch (Exception e) {
				e.printStackTrace();
				open = false;
			}
		} else {
			open = false;
		}
		return open;
	}

	public boolean isRunning() {
		final File folder = new File("/home/mcwrapper/pid");
		ArrayList<String> test = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
			test.add(fileEntry.getName());
		}
		if (test.contains(this.id)) {
			return true;
		}
		return false;

	}

	public String[] getOnlinePlayers() {
		// TODO
		return null;
	}

	public void restart() {

	}

	public void getUptime() {
		// TODO
	}
}