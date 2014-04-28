package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;

public class ServerCommand {

	public ServerCommand(String[] cmd) {
		String out = "";
		int serverid = Integer.parseInt(cmd[0]);
		for(int i = 1; i < cmd.length; i++){
			if(i == cmd.length){
				out = out + cmd[i];
			}else{
				out = out + cmd[i] + " ";
			}
		}
		Server server = Main.wrapper.getServer(serverid);
		server.executeCommand(out);
	}

}
