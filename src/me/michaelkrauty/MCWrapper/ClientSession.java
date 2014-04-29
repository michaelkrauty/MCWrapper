package me.michaelkrauty.MCWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSession {

	public ClientSession(Socket socket) {
		PrintWriter out;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
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
			e.printStackTrace();
		}
	}

	public boolean checkLogin(String email, String password) {
		// TODO
		return true;
	}
}