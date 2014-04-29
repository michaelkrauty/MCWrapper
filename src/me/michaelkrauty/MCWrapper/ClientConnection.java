package me.michaelkrauty.MCWrapper;

import java.net.Socket;

public class ClientConnection implements Runnable {
	@SuppressWarnings("unused")
	private final Socket socket;
	private Thread t;

	ClientConnection(Socket soc) {
		socket = soc;
	}

	@Override
	public void run() {
		System.out.println("Client session started, now do stuff with it <3");
	}

	public void start() {
		System.out.println("Starting client session");
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}
}