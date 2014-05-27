package me.michaelkrauty.MCWrapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.net.SocketFactory;

import org.apache.log4j.Logger;

public class Server {

	private final static Logger log = Logger.getLogger(Main.class);

	private final int id;
	@SuppressWarnings("unused")
	private int ownerid;
	private String serverdir;
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
		try {
			ownerid = SQL.getServerOwner(serverid);
			serverdir = "servers/" + id;
			PID = -1;
			host = getDBHost();
			port = getDBPort();
			memory = getDBMemory();
			process = null;
			inputstream = null;
			outputstream = null;
			starttime = -1;

		} catch (NullPointerException ignored) {
		}
	}

	public void start() {
		log.info("Starting server " + id + "...");
		if (!isRunning()) {
			try {
				ProcessBuilder pb = new ProcessBuilder();
				pb.directory(new File(serverdir));
				pb.command("java", "-Xmx" + Integer.toString(memory) + "M",
						"-jar", "/home/mcwrapper/jar/test.jar", "--host", host,
						"--port", Integer.toString(port), "nogui");
				Process p = pb.start();
				process = p;
				java.lang.reflect.Field f = p.getClass().getDeclaredField("pid");
				f.setAccessible(true);
				PID = f.getInt(p);
				inputstream = p.getInputStream();
				outputstream = p.getOutputStream();
				starttime = System.currentTimeMillis();
			} catch (IOException e) {
				log.info(e.getMessage());
				log.info("Attempting to create the server directory & restart...");
				File sdir = new File(serverdir);
				sdir.mkdir();
				start();
			} catch (NullPointerException e) {
				log.info("Server " + id + " doesn't exist in SQL database.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			log.info("Server is already online!");
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
		boolean open = true;
		if (exists()) {
			Socket socket;
			try {
				socket = SocketFactory.getDefault().createSocket();
				try {
					socket.setSoTimeout(5000);
					socket.connect(new InetSocketAddress(host, port));
					socket.close();
				} catch (Exception e) {
					open = false;
				}
			} catch (Exception e) {
				log.error("Error creating socket");
				open = false;
			}
		} else {
			log.info("Server doesn't exist!");
			open = false;
		}
		return open;
	}

	public boolean isRunning() {
		if (process == null) {
			return false;
		}
		try {
			process.exitValue();
			return false;
		} catch (Exception e) {
			return true;
		}
	}

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
		return SQL.serverDataContainsServer(id);
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