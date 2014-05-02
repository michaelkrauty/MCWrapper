package me.michaelkrauty.MCWrapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CreateUser {

	public CreateUser(int userid, String auth) {
		try {
			ProcessBuilder pb = new ProcessBuilder("sudo", "useradd", "-d",
					"/home/mcwrapper/servers/" + userid, "-s", "/bin/bash",
					"-G", "mcwrapper_users", "mcwrapper_" + userid);
			Process p = pb.start();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}