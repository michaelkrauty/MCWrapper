package me.michaelkrauty.MCWrapper.Commands;

import me.michaelkrauty.MCWrapper.Main;

public class KillAll {

	public KillAll() {
		for (int i = 0; i < Main.servers.size(); i++) {
			Main.servers.get(i).forceStop();
		}
	}
}
