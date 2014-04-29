package me.michaelkrauty.MCWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Connection implements Runnable {
	private final Socket socket;
	private Thread t;

	Connection(Socket soc) {
		socket = soc;
	}

	@Override
	public void run() {
		System.out.println("run, lol");
	}

	public void start() {
		System.out.println("Starting client session");
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}
}

public class ConnectionHandler {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(3307);
			for (;;) {
				Socket clientSocket = null;
				clientSocket = serverSocket.accept();
				Connection connection = new Connection(clientSocket);
				connection.start();
			}
		} catch (IOException e) {
			System.err.println("Couldn't bind to port 3307!");
			System.err.println(e.getMessage());
		}
	}

}