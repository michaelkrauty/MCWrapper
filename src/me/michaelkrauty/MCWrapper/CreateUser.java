<<<<<<< HEAD
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
=======
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
<<<<<<< HEAD
=======
>>>>>>> 05ae332d3af196f4b5a7a4ffa2bcff0f3f3c739e
>>>>>>> 766996ea270c848e90755cc041c1d7c1784c6223
}