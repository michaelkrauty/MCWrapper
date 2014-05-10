package me.michaelkrauty.MCWrapper.ClientConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

class ClientOutput {

	private PrintWriter out;

	public ClientOutput(Socket socket) {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);

		} catch (IOException e) {
			// Catches any errors
			System.err.println(socket.getRemoteSocketAddress().toString()
					+ " disconnected: " + e.getMessage());
		}
	}

	public void send(ArrayList<String> outputLines) {
		for (String line : outputLines) {
			System.out.println("Server: " + line);
			out.println(line);
		}
	}

	public void closeConnection() {
		out.close();
	}
}