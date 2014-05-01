package me.michaelkrauty.MCWrapper;

import java.io.File;
import java.io.IOException;

public class CreateUser {

	public CreateUser(int userid) {
		File userdir = new File("/home/mcwrapper/servers/" + userid);
		if (!userdir.exists()) {
			userdir.mkdir();
		}
		try {
			ProcessBuilder pb = new ProcessBuilder("useradd", "-d",
					"/home/mcwrapper/servers/" + userid, "-s", "/usr/bin/rssh",
					"-G", "rsshusers", "mcwrapper_" + userid);
			pb.start();
			System.out.println("Created user \"mcwrapper_" + userid + "\".");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
