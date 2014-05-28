package me.michaelkrauty.MCWrapper.ClientConnection;

import me.michaelkrauty.MCWrapper.Main;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ClientInput implements Runnable {

	private final static Logger log = Logger.getLogger(Main.class);

	private Thread t;
	private final Socket socket;

	public ClientInput(Socket soc) {
		socket = soc;
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String inputLine;

			ServerProtocol sp = new ServerProtocol(socket);

			while ((inputLine = in.readLine()) != null) {
				log.info(socket.getRemoteSocketAddress().toString() + ": "
						+ inputLine);
				sp.processInput(inputLine);
				if (inputLine.equalsIgnoreCase("disconnect")) {
					break;
				}
			}
			in.close();
			sp.closeConnections();

		} catch (IOException e) {
			log.error(socket.getRemoteSocketAddress().toString()
					+ " disconnected: " + e.getMessage());
		}
	}

}
