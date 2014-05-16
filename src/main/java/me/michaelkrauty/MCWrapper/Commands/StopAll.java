package me.michaelkrauty.MCWrapper.Commands;

import me.michaelkrauty.MCWrapper.Main;

public class StopAll {

	public StopAll() {
		for (int i = 0; i < Main.servers.size(); i++) {
			Main.servers.get(i).stop();
		}
	}
}
