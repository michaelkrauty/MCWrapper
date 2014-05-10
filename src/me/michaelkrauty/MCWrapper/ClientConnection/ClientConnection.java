package me.michaelkrauty.MCWrapper.ClientConnection;

import java.net.Socket;

/*
 * Client Connection class...
 */
public class ClientConnection {

	// Constructor
	ClientConnection(Socket socket) {
		System.out.println("Connection from "
				+ socket.getRemoteSocketAddress().toString());
		new ClientInput(socket).start();
	}
}