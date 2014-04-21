package me.michaelkrauty.MCWrapper.commands;

public class Test {

	public Test() {
		System.out.println("test");
	}

	public Test(String command, String[] args) {
		System.out.println("Command: " + command);
		System.out.println("Args:");
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
	}

}
