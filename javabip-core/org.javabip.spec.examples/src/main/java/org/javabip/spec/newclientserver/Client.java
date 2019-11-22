package org.javabip.spec.newclientserver;

import java.util.ArrayList;

import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.BIPActor;
import org.javabip.api.BIPActorAware;
import org.javabip.api.DataOut.AccessType;
import org.javabip.api.PortType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.javabip.api.BIPActor;
import org.javabip.api.BIPActorAware;

@Ports({ @Port(name = "connect", type = PortType.spontaneous), 
	 @Port(name = "download", type = PortType.spontaneous),
	 @Port(name = "logout", type = PortType.spontaneous) })
@ComponentType(initial = "0", name = "org.bip.spec.newclientserver.Client")

public class Client implements BIPActorAware {

	Logger logger = LoggerFactory.getLogger(Client.class);
	private BIPActor bipActor;
	private int id;
	private ArrayList<Server> servers;
	private int resourceId;
	
	public Client(int _id, int _res) {
		// TODO Auto-generated constructor stub
		id = _id;
		resourceId = _res;
		servers = new ArrayList<Server>();
	}
	
	public void connectServer(@Data(name = "server") Server server) {
		if (!this.servers.contains(server)) {
			this.servers.add(server);
		}
		logger.debug("Client {" + bipActor + "} CONNECT to server {" + server + "}");
		System.out.println("Client {" + bipActor + "} CONNECT to server {" + server + "}");
	}
	
	public void downloadResource(@Data(name = "server") Server server, @Data(name = "resourceId") Integer resId) {
		if (this.servers.contains(server)) {
			this.servers.add(server);
			resourceId = resId;
			logger.debug("Client {" + bipActor + "} DOWNLOAD resource {" + resourceId + "} from server {" + server + "}.\n");
		}
		
	}
	
	public void logout(@Data(name = "server") Server server) {
		if (this.servers.contains(server)) {
			
		}
	}
	@Override
	public void setBIPActor(BIPActor actor) {
		// TODO Auto-generated method stub
		
	}

}
