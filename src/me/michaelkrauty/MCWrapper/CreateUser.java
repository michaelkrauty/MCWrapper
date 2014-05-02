package me.michaelkrauty.MCWrapper;

public class CreateUser {

	public CreateUser(int userid, String auth) {
		try {
			ProcessBuilder pb = new ProcessBuilder("sudo", "useradd", "-d",
					"/home/mcwrapper/servers/" + userid, "-s", "/bin/bash",
					"-G", "mcwrapper_users", "mcwrapper_" + userid);
			pb.start();
			System.out.println("User \"mcwrapper_" + userid + "\" Created.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}