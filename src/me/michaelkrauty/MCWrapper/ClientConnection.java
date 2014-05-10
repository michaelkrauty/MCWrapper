package me.michaelkrauty.MCWrapper;

// Imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/*
 * Client Connection class...
 */
public class ClientConnection implements Runnable {

	// Create Socket Object
	private final Socket socket;
	// Create Thread Object
	private Thread t;

	// Constructor
	ClientConnection(Socket soc) {
		socket = soc;
	}

	// Start Method
	public void start() {
		// Print Connection Info
		System.out.println("Connection from "
				+ socket.getRemoteSocketAddress().toString());
		// If thread is not running then run in thread (Should always be null)
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	// Run Method
	@Override
	public void run() {
		try {
			// TODO
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			// TODO
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			// Create empty strings
			String inputLine;
			ArrayList<String> outputLine;
			// TODO
			ServerProtocol sp = new ServerProtocol();

			out.println("Hello! :)");

			// While client is still connected print their commands
			while ((inputLine = in.readLine()) != null) {
				System.out.println(socket.getRemoteSocketAddress().toString()
						+ ": " + inputLine);
				// Process the input, return a string array of output lines
				outputLine = sp.processInput(inputLine);

				// Print output lines
				for (String line : outputLine) {
					out.println(line);
					System.out.println("Server: " + line);
				}

				// Breaks out of the while when disconnect line is sent to
				// client
				if (outputLine.equals("disconnect")) {
					break;
				}
			}
			// Tears down the connection.
			in.close();
			out.close();

		} catch (IOException e) {
			// Catches any errors
			System.err.println(socket.getRemoteSocketAddress().toString()
					+ " disconnected: " + e.getMessage());
		}
	}

}