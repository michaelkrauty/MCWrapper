package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class KillAll {

	public KillAll() {
		for (int i = 0; i < Main.servers.size(); i++) {
			Main.servers.get(i).forceStop();
		}
	}
}
