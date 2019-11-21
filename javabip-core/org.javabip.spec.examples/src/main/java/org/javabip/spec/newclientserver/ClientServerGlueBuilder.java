package org.javabip.spec.newclientserver;

import org.javabip.glue.GlueBuilder;
import org.javabip.spec.newclientserver.*;

public class ClientServerGlueBuilder extends GlueBuilder{

	@Override
	public void configure() {
		// TODO Auto-generated method stub
		port(ServerA.class, "move").requires(Monitor.class, "take");
		port(Monitor.class, "take").requires(ServerB.class, "receive");
		port(ServerB.class, "receive").requires(Monitor.class, "finish");
		port(Monitor.class, "receive").requires(ServerA.class, "close");
		
		port(Client.class, "download").requires(Monitor.class, "take");
		port(Monitor.class, "take").requires(Server.class, "publish");
		port(Server.class, "publish").requires(Monitor.class, "finish");
		port(Monitor.class, "finish").requires(Client.class, "releash");
		
		data(Client.class,"resourceIdToUse").to(Server.class, "resourceIdToUse");
		data(Server.class, "serverId").to(Client.class, "serverId");
		data(Server.class, "accessed").to(Client.class, "accessed");
		data(ServerB.class, "serverId").to(ServerA.class, "serverId");
		data(ServerA.class, "resourceId").to(ServerB.class, "resourceId");
	}

}
