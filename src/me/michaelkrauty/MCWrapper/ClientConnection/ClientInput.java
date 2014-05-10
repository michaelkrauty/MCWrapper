package me.michaelkrauty.MCWrapper.ClientConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientInput implements Runnable {

	private final Socket socket;

	public ClientInput(Socket soc) {
		socket = soc;
	}

	public void start() {

	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String inputLine;

			ServerProtocol sp = new ServerProtocol(socket);

			while ((inputLine = in.readLine()) != null) {
				System.out.println(socket.getRemoteSocketAddress().toString()
						+ ": " + inputLine);
				sp.processInput(inputLine);
				if (inputLine.equalsIgnoreCase("disconnect")) {
					break;
				}
			}
			in.close();
			sp.closeConnections();

		} catch (IOException e) {
			System.err.println(socket.getRemoteSocketAddress().toString()
					+ " disconnected: " + e.getMessage());
		}
	}

}
