package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.Main;
import me.michaelkrauty.MCWrapper.Server;

public class Start {

	public Start(int serverid) {
		boolean contains = false;
		for(int i = 0; i < Main.servers.size(); i++){
			if(Main.servers.get(i).getId() == serverid){
				contains = true;
			}
		}
		if(contains){
			Main.wrapper.getServer(serverid).start();
		}else{
			Server server = new Server(serverid);
			Main.servers.add(server);
			server.start();
		}
	}
}
