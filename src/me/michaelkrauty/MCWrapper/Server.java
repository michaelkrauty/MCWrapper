package me.michaelkrauty.MCWrapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.net.SocketFactory;

public class Server {

	private final int id;
	private String serverdir;
	private boolean exists;
	private int PID;
	private String host;
	private int port;
	private int memory;
	private Process process;
	private InputStream inputstream;
	private OutputStream outputstream;
	private long starttime;

	public Server(int serverid) {
		id = serverid;
		serverdir = "/home/mcwrapper/servers/" + id;
		exists = SQL.serverDataContainsServer(id);
		PID = -1;
		host = getDBHost();
		port = getDBPort();
		memory = getDBMemory();
		process = null;
		inputstream = null;
		outputstream = null;
		starttime = -1;
	}

	public void start() {
		System.out.println("Starting server " + id + "...");
		if (!isRunning()) {
			try {
				ProcessBuilder pb = new ProcessBuilder();
				pb.directory(new File(serverdir));
				pb.command("java", "-Xmx" + Integer.toString(memory) + "M",
						"-jar", "/home/mcwrapper/jar/test.jar", "--host", host,
						"--port", Integer.toString(port), "nogui");
				Process p = pb.start();
				process = p;
				try {
					java.lang.reflect.Field f = p.getClass().getDeclaredField(
							"pid");
					f.setAccessible(true);
					PID = f.getInt(p);
				} catch (Throwable e) {
					System.err.println("Error getting PID");
				}
				inputstream = p.getInputStream();
				outputstream = p.getOutputStream();
				starttime = System.currentTimeMillis();
			} catch (IOException e) {
				System.out.println("Server directory or jar file not found!");
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Server is already online!");
		}
	}

	public void stop() {
		executeCommand("stop");
	}

	public void forceStop() {
		process.destroy();
	}

	public boolean executeCommand(String command) {
		if (isRunning()) {
			PrintWriter out = new PrintWriter(process.getOutputStream(), true);
			out.println(command);
			return true;
		}
		return false;
	}

	public boolean isOnline() {
		System.out.println("Trying connection: " + host + ":" + port);
		boolean open = true;
		if (exists) {
			Socket socket;
			try {
				socket = SocketFactory.getDefault().createSocket();
				try {
					socket.setSoTimeout(5000);
					socket.connect(new InetSocketAddress(host, port));
					socket.close();
					System.out.println("Server is online");
				} catch (Exception e) {
					System.out.println("Server is offline");
					open = false;
				}
			} catch (Exception e) {
				System.err.println("Error creating socket");
				open = false;
			}
		} else {
			System.out.println("Server doesn't exist!");
			open = false;
		}
		return open;
	}

	public boolean isRunning() {
		try {
			process.exitValue();
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public void restart() {
		stop();
		while (isRunning()) {
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		start();
	}

	// get
	public int getId() {
		return id;
	}

	public String[] getOnlinePlayers() {
		// TODO
		return null;
	}

	public InputStream getInputStream() {
		return inputstream;
	}

	public OutputStream getOutputStream() {
		return outputstream;
	}

	public Process getProcess() {
		return process;
	}

	public int getPID() {
		return PID;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getServerDir() {
		return serverdir;
	}

	public int getUptime() {
		return (int) (System.currentTimeMillis() - starttime);
	}

	public boolean exists() {
		return exists;
	}

	public String getDBHost() {
		return SQL.getServerHost(id);
	}

	public int getDBPort() {
		return SQL.getServerPort(id);
	}

	public int getDBMemory() {
		return SQL.getServerMemory(id);
	}

	public int getDBJar() {
		return SQL.getServerJar(id);
	}

	public boolean getDBSuspended() {
		return SQL.getServerSuspended(id);
	}

	public String getDBName() {
		return SQL.getServerName(id);
	}

	public int getDBOwner() {
		return SQL.getServerOwner(id);
	}
}