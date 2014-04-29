package me.michaelkrauty.MCWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler {

	public ConnectionHandler() {

		int portNumber = 3307;

		while (Main.running) {
			try {
				@SuppressWarnings("resource")
				ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				new ClientSession(clientSocket);
			} catch (IOException e) {
				System.out
						.println("Exception caught when trying to listen on port "
								+ portNumber + " or listening for a connection");
				System.out.println(e.getMessage());
			}
		}
	}

}