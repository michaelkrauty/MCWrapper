package me.michaelkrauty.MCWrapper.commands;

import java.util.concurrent.TimeUnit;

import me.michaelkrauty.MCWrapper.Main;

public class Uptime {

	public Uptime() {
		System.out.println("Wrapper Uptime: "
				+ TimeUnit.SECONDS.toSeconds(Main.wrapper.getUptime())
				+ " seconds.");
	}

}
