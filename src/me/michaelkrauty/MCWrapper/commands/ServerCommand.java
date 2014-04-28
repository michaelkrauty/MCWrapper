package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;

public class ServerCommand {

	public ServerCommand(String[] cmd) {
		try{
		int serverid = Integer.parseInt(cmd[1]);
		String out = "";
		for(int i = 1; i < cmd.length; i++){
			if(i == cmd.length){
				out = out + cmd[i];
			}else{
				out = out + cmd[i] + " ";
			}
		}
		Server server = Main.wrapper.getServer(serverid);
		server.executeCommand(out);
		System.out.println("Command sent to server " + server.getId() + ": " + out);
		}catch(Exception e){
			System.out.println("Server ID must be an int > 0!");
		}
	}

}
