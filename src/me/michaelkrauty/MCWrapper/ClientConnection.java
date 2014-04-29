package me.michaelkrauty.MCWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection implements Runnable {
	private final Socket socket;
	private Thread t;

	ClientConnection(Socket soc) {
		socket = soc;
	}

	@Override
	public void run() {
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			String inputLine, outputLine;

			// Initiate conversation with client
			ServerProtocol sp = new ServerProtocol();
			outputLine = sp.processInput(null);
			out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {
				System.out.println("Client: " + inputLine);
				outputLine = sp.processInput(inputLine);
				out.println(outputLine);
				System.out.println("Server: " + outputLine);
				if (outputLine.equals("Bye."))
					break;
			}

		} catch (IOException e) {
			System.err.println("Couldn't create input/output stream(s)!");
			System.err.println(e.getMessage());
		}
	}

	public void start() {
		System.out.println("Starting client session");
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}
}