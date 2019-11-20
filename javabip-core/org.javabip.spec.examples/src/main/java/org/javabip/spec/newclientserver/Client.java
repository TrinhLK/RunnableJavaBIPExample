package org.javabip.spec.newclientserver;

import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.DataOut.AccessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.javabip.api.PortType;

@Ports({ //@Port(name = "request", type = PortType.enforceable),
	@Port(name = "download", type = PortType.enforceable),
	@Port(name = "releash", type = PortType.enforceable)})
@ComponentType(initial = "0", name = "org.bip.spec.newclientserver.Client")
public class Client {

	Logger logger = LoggerFactory.getLogger(Client.class);
	private int clientId;
	private int serverId;
	private int resourceId;
	
	public Client(int _id, int _rId) {
		// TODO Auto-generated constructor stub
		clientId = _id;
		this.serverId = -1;
		this.resourceId = _rId;
		System.out.println("Create Client: " + clientId);
	}
	
	@Transition(name = "download", source = "0", target = "1", guard = "canAccess")
	public void downloading(@Data(name = "serverId") Integer sid) {
		this.serverId = sid;
		System.out.println("Client {" + clientId + "}: ASKES for downloading resource {" + resourceId + "} from Server{" + serverId + "}");
	}
	@Transition(name = "releash", source = "1", target = "0")
	public void releashing() {
		System.out.println("Client {" + clientId + "}: FINISHES the downloading resource {" + resourceId + "}.\nLogged out");
	}
	
	@Data(name = "resourceIdToUse", accessTypePort = AccessType.any)
	public int getResourceId() {
		return resourceId;
	}
	
	@Guard(name = "canAccess")
	public boolean canAccess(@Data(name = "serverId") Integer sid, @Data(name = "accessed") Boolean canAccess) {
		serverId = sid;
		if (serverId >= 0 && canAccess == true) {
			return true;
		}
		return false;
	}	
}
