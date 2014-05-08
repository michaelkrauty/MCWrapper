package me.michaelkrauty.MCWrapper;

public class CreateUser {

	public CreateUser(int userid, String auth) {
		try {
			new ProcessBuilder("sudo", "useradd", "-d",
					"/home/mcwrapper/servers/" + userid, "-s", "/bin/bash",
					"-G", "mcwrapper_users", "mcwrapper_" + userid).start();
			System.out.println("User \"mcwrapper_" + userid + "\" Created.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
package me.michaelkrauty.MCWrapper;

public class CreateUser {

	public CreateUser(int userid, String auth) {
		try {
			new ProcessBuilder("sudo", "useradd", "-d",
					"/home/mcwrapper/servers/" + userid, "-s", "/bin/bash",
					"-G", "mcwrapper_users", "mcwrapper_" + userid).start();
			System.out.println("User \"mcwrapper_" + userid + "\" Created.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}