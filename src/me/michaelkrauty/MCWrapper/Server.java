package me.michaelkrauty.MCWrapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
		this.id = serverid;
		this.serverdir = "/home/mcwrapper/servers/" + this.id;
		this.exists = SQL.serverDataContainsServer(this.id);
		this.PID = -1;
		this.host = getDBHost();
		this.port = getDBPort();
		this.memory = getDBMemory();
		this.process = null;
		this.inputstream = null;
		this.outputstream = null;
		this.starttime = -1;
	}

	public void start() {
		if (!this.isOnline()) {
			System.out.println("Starting server " + this.id + "...");
			try {
				// ProcessBuilder pb = new ProcessBuilder("java", "-jar",
				// "/home/mcwrapper/jar/test.jar", "--host", this.host,
				// "--port", Integer.toString(this.port), "-Xmx"
				// + Integer.toString(this.memory) + "M");
				ProcessBuilder pb = new ProcessBuilder(
						"java -jar /home/mcwrapper/jar/test.jar --host '"
								+ this.host + "'");
				pb.directory(new File(this.serverdir));
				Process p = pb.start();
				this.setProcess(p);
				try {
					java.lang.reflect.Field f = p.getClass().getDeclaredField(
							"pid");
					f.setAccessible(true);
					this.PID = f.getInt(p);
				} catch (Throwable e) {
				}
				this.inputstream = p.getInputStream();
				this.outputstream = p.getOutputStream();
				this.starttime = System.currentTimeMillis();
			} catch (IOException e) {
				System.out.println("Server directory or jar file not found!");
				e.printStackTrace();
			}
		} else {
			System.out.println("Server is already online!");
		}
	}

	public int getId() {
		return this.id;
	}

	public void stop() {
		executeCommand("stop");
	}

	public void forceStop() {
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
		return this.PID;
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
		if (this.isOnline()) {
			stop();
		}
		while (this.isOnline()) {
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		start();
	}

	public void getUptime() {
		System.out.println("Wrapper Uptime: "
				+ TimeUnit.HOURS.toHours(this.starttime) + " hours "
				+ TimeUnit.MINUTES.toMinutes(this.starttime) + " minutes "
				+ TimeUnit.SECONDS.toSeconds(this.starttime) + " seconds.");
	}

	public boolean exists() {
		return this.exists;
	}

	public String getDBHost() {
		return SQL.getServerHost(this.id);
	}

	public int getDBPort() {
		return SQL.getServerPort(this.id);
	}

	public int getDBMemory() {
		return SQL.getServerMemory(this.id);
	}

	public int getDBJar() {
		return SQL.getServerJar(this.id);
	}

	public boolean getDBSuspended() {
		return SQL.getServerSuspended(this.id);
	}

	public String getDBName() {
		return SQL.getServerName(this.id);
	}

	public int getDBOwner() {
		return SQL.getServerOwner(this.id);
	}
}