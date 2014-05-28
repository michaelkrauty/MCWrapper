package me.michaelkrauty.MCWrapper.ClientConnection;

import me.michaelkrauty.MCWrapper.Main;
import org.apache.log4j.Logger;

import java.net.Socket;

/*
 * Client Connection class...
 */
class ClientConnection {

	private final static Logger log = Logger.getLogger(Main.class);

	// Constructor
	ClientConnection(Socket socket) {
		log.info("Connection from "
				+ socket.getRemoteSocketAddress().toString());
		new ClientInput(socket).start();
	}
}