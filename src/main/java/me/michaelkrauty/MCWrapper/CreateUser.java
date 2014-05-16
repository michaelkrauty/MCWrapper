package me.michaelkrauty.MCWrapper;

import org.apache.log4j.Logger;

public class CreateUser {

	private final static Logger log = Logger.getLogger(Main.class);

	public CreateUser(int userid, String auth) {
		try {
			new ProcessBuilder("sudo", "useradd", "-d",
					"/home/mcwrapper/servers/" + userid, "-s", "/bin/bash",
					"-G", "mcwrapper_users", "mcwrapper_" + userid).start();
			log.info("User \"mcwrapper_" + userid + "\" Created.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}