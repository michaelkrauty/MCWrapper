package me.michaelkrauty.MCWrapper;

public class CreateUser {

	public CreateUser(int userid, String auth) {
		try {
			ProcessBuilder pb = new ProcessBuilder("sudo", "-S",
					"useradd -d /home/mcwrapper/servers/" + userid
							+ " -s /bin/bash -G mcwrapper_users mcwrapper_"
							+ userid);
			pb.start();
			System.out.println("Created user \"mcwrapper_" + userid + "\".");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

// HURRR