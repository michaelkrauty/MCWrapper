package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;

public class Uptime {

	public Uptime() {
		System.out.println("Wrapper Uptime: " + Main.wrapper.getUptime());
	}

}
