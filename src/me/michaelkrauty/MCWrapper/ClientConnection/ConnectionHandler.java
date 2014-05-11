package me.michaelkrauty.MCWrapper.ClientConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import me.michaelkrauty.MCWrapper.Main;

import org.apache.log4j.Logger;

public class ConnectionHandler implements Runnable {

	private final static Logger log = Logger.getLogger(Main.class);
	private static ServerSocket serverSocket;
	private Thread t;

	public ConnectionHandler() {
		try {
			serverSocket = new ServerSocket(3307);
			log.info("Server socket created.");
		} catch (IOException e) {
			log.error("Couldn't bind to port 3307!");
			log.error(e.getMessage());
		}
	}

	@Override
	public void run() {
		while (Main.running) {
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				log.error("Couldn't accept client connection!");
				log.error(e.getMessage());
			}
			new ClientConnection(clientSocket);
		}
	}

	public void start() {
		log.info("Starting Connection Handler");
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}
}