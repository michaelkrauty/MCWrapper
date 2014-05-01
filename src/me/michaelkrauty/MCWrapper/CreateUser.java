package me.michaelkrauty.MCWrapper;

import java.io.File;

public class CreateUser {

	public CreateUser(int userid, String auth) {
		File userdir = new File("/home/mcwrapper/servers/" + userid);
		if (!userdir.exists()) {
			userdir.mkdir();
		}
		try {
			ProcessBuilder pb = new ProcessBuilder("sudo", "-S", "useradd "
					+ " -d " + " /home/mcwrapper/servers/ " + userid + "-s"
					+ "/bin/bash" + "-G" + "mcwrapper_users" + "mcwrapper_"
					+ userid);
			pb.start();
			System.out.println("Created user \"mcwrapper_" + userid + "\".");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
