package me.michaelkrauty.MCWrapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CreateUser {

	public CreateUser(int userid, String auth) {
		try {
			System.out.println("test1");
			Process p = Runtime.getRuntime().exec("pwd");
			System.out.println("test2");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			System.out.println("test3");
			String line;
			System.out.println("test4");
			while ((line = in.readLine()) != null) {
				System.out.println("test5");
				System.out.println(line);
			}
			System.out.println("test6");
		} catch (Exception e) {
			System.out.println("test7");
			e.printStackTrace();
		}
	}
}

/*
 * ProcessBuilder pb = new ProcessBuilder("sudo", "useradd", "-d",
 * "/home/mcwrapper/servers/" + userid, "-s", "/bin/bash", "-G",
 * "mcwrapper_users", "mcwrapper_" + userid); Process p = pb.start();
 * BufferedReader in = new BufferedReader(new InputStreamReader(
 * p.getInputStream())); String line; while ((line = in.readLine()) != null) {
 * System.out.println(line); }
 */