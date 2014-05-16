package me.michaelkrauty.MCWrapper.ClientConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import me.michaelkrauty.MCWrapper.Main;

import org.apache.log4j.Logger;

public class ClientOutput {

	private final static Logger log = Logger.getLogger(Main.class);

	private PrintWriter out;

	public ClientOutput(Socket socket) {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);

		} catch (IOException e) {
			// Catches any errors
			log.error(socket.getRemoteSocketAddress().toString()
					+ " disconnected: " + e.getMessage());
		}
	}

	public void send(ArrayList<String> outputLines) {
		for (String line : outputLines) {
			log.info("Server: " + line);
			out.println(line);
		}
	}

	public void closeConnection() {
		out.close();
	}
}