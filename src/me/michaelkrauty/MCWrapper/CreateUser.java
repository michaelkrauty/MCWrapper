package me.michaelkrauty.MCWrapper;

public class CreateUser {

	public CreateUser(int userid, String auth) {
		try {
			ProcessBuilder pb = new ProcessBuilder("sudo", "-s",
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

<<<<<<< HEAD
}
=======
// lol
>>>>>>> 494fd401965dc94503e0fba29e522a68c4408090
