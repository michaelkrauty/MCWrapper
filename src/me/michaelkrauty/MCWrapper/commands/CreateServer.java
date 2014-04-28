package me.michaelkrauty.MCWrapper.commands;

import me.michaelkrauty.MCWrapper.SQL;

public class CreateServer {

	public CreateServer(String name, int memory, int ownerid) {
		int serverid = SQL.getAllServers().size() + 1;
		SQL.createServer(serverid, name, memory, ownerid);
	}

}
