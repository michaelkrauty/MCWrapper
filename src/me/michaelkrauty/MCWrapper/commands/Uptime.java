package me.michaelkrauty.MCWrapper.commands;

import java.util.concurrent.TimeUnit;

import me.michaelkrauty.MCWrapper.Main;

public class Uptime {

	public Uptime() {
		System.out.println("Wrapper Uptime: "
				+ TimeUnit.HOURS.toHours(Main.wrapper.getUptime()) + " hours "
				+ TimeUnit.MINUTES.toMinutes(Main.wrapper.getUptime())
				+ " minutes "
				+ TimeUnit.SECONDS.toSeconds(Main.wrapper.getUptime())
				+ " seconds.");
	}

}
