package me.michaelkrauty.MCWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
	private static ServerSocket serverSocket;
	private Thread t;

	public ConnectionHandler() {
		try {
			serverSocket = new ServerSocket(3307);
			System.out.println("Server socket created.");
		} catch (IOException e) {
			System.err.println("Couldn't bind to port 3307!");
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void run() {
		while (Main.running) {
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.err.println("Couldn't accept client connection!");
				System.err.println(e.getMessage());
			}
			new ClientConnection(clientSocket).start();
		}
	}

	public void start() {
		System.out.println("Starting Connection Handler");
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}
}