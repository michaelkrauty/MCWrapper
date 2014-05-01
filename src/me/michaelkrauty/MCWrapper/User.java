package me.michaelkrauty.MCWrapper;

import java.util.ArrayList;

public class User {

	private static boolean exists = false;
	private static int userid;
	private static String username;
	private static String email;
	private static String password;

	public User(int id) {
		userid = id;
		if (SQL.userDataContainsId(id)) {
			exists = true;
		}
		email = SQL.getUserEmail(id);
		username = SQL.getUserUsername(id);
		password = SQL.getUserPassword(id);
	}

	public boolean exists() {
		return exists;
	}

	public int getUserId() {
		return userid;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public ArrayList<Integer> getServers() {
		return SQL.getUserServers(userid);
	}
}
