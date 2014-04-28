package me.michaelkrauty.MCWrapper.commands;

public class Help {

	public Help() {
		String commands = "";
		for (int i = 0; i < Command.commands.size(); i++) {
			if (i == Command.commands.size()) {
				commands = commands + Command.commands.get(i) + ".";
			} else {
				commands = commands + Command.commands.get(i) + ", ";
			}
		}
		System.out.println("Commands: " + commands);
	}
}
