package org.javabip.spec.clientserver;

import org.javabip.glue.GlueBuilder;


public class ClientServerGlueBuilder extends GlueBuilder{

	@Override
	public void configure() {
		// TODO Auto-generated method stub
		//--requires
		port(Client.class,"request").requires(Server.class, "run", NewServer.class, "run");
		port(Client.class,"download").requires(Server.class, "run");
		port(Client.class,"releash").requires(Server.class, "run");
		port(Client.class,"releash").requires(Server.class, "close");
		
		port(Server.class, "run").requires(Client.class, "request");
		port(Server.class, "run").requires(Client.class, "download");
		port(Server.class, "run").requires(Client.class, "releash");
		port(Server.class, "close").requires(NewServer.class, "run");
		port(Server.class,"move").requires(NewServer.class, "receive");
		
		port(NewServer.class, "run").requires(Client.class, "request");
		port(NewServer.class,"announce").requires(Server.class, "run");
		port(NewServer.class,"receive").requires(Server.class, "move");
		
		//--accepts
		port(Client.class, "request").accepts(Server.class, "run", NewServer.class, "run");
		port(Client.class, "download").accepts(Server.class, "run", Server.class, "close");
		port(Client.class,"releash").accepts(Server.class, "run");
		
		port(Server.class, "close").accepts(NewServer.class,"run");
		port(Server.class, "move").accepts(NewServer.class,"receive");
		//port(Server.class, "run").accepts(Client.class,"request", "download", "releash");
		//port(NewServer.class, "run").accepts(Server.class, "close", Client.class, "request");
		port(NewServer.class, "receive").accepts(Server.class, "move");
		
		data(Client.class,"resourceIdToUse").to(Server.class, "resourceIdToUse");
		data(Client.class,"takingRs").to(Server.class, "takingRs");
//		data(Client.class,"resourceIdToUse").to(NewServer.class, "resourceIdToUse");
//		data(Client.class,"takingRs").to(NewServer.class, "takingRs");
		data(Server.class, "serverId").to(Client.class, "serverId");
		data(Server.class, "accessed").to(Client.class, "accessed");
//		data(Server.class, "accessed").to(NewServer.class, "accessed");
		data(NewServer.class, "newServer").to(Server.class, "newServer");
		data(Server.class, "resourceId").to(NewServer.class, "resourceId");
		//data(Server.class, "listResource").to(Client.class, "listResource");
//		data(Server.class, "resource").to(Client.class, "resource");
//		data(NewServer.class, "newServerID").to(Server.class, "newServerID");
	}

}
