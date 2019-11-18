package org.javabip.spec.clientserver;

import java.util.ArrayList;

import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.DataOut.AccessType;
import org.javabip.spec.trackpeer.Peer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.javabip.api.PortType;

@Ports({ @Port(name = "request", type = PortType.enforceable),
	@Port(name = "download", type = PortType.enforceable),
	@Port(name = "releash", type = PortType.enforceable)})
@ComponentType(initial = "zero", name = "org.bip.spec.clientserver.Client")
public class Client {

	Logger logger = LoggerFactory.getLogger(Peer.class);
	private int clientId;
	private int serverId;
	private int resourceId;
	private boolean takingResource;
	
	public Client(int _id) {
		// TODO Auto-generated constructor stub
		clientId = _id;
		this.serverId = -1;
		this.resourceId = -1;
		//System.out.println("Create Client: " + clientId);
		//this.myRs = null;
		//listResource = new ArrayList<Integer>();
	}
	
	public Client(int _id, int _rId) {
		// TODO Auto-generated constructor stub
		clientId = _id;
		this.serverId = -1;
		this.resourceId = _rId;
		System.out.println("Create Client: " + clientId);
		this.takingResource = false;
		//listResource = new ArrayList<Integer>();
	}
	
	@Transition(name = "request", source = "zero", target = "one")
	public void requesting(@Data(name = "serverId") Integer sid) {
		//this.myRs = resource;
		this.serverId = sid;
		System.out.println("Client {" + clientId + "}: is REQUESTING {" + serverId + "} to access resource {" + resourceId + "}.");
	}
	
	@Transition(name = "download", source = "one", target = "one", guard = "canAccess")
	public void downloading() {
		System.out.println("Client {" + clientId + "}: is DOWNLOADING resource {" + resourceId + "}");
		takingResource = true;
	}
	@Transition(name = "releash", source = "one", target = "zero", guard = "canReleash")
	public void releashing() {
		System.out.println("Client {" + clientId + "}: is RELEASING resource {" + resourceId + "}.\nLogged out");
		this.takingResource = false;
	}
	
	@Data(name = "resourceIdToUse", accessTypePort = AccessType.any)
	public int getResourceId() {
		return resourceId;
	}
	
	@Data(name = "takingRs", accessTypePort = AccessType.any)
	public boolean isTakingResource() {
		return takingResource;
	}
	
	@Guard(name = "canReleash")
	public boolean canReleash() {
		return takingResource;
	}
	@Guard(name = "canAccess")
	public boolean canAccess(@Data(name = "serverId") Integer sid, @Data(name = "accessed") Boolean canAccess) {
		serverId = sid;
		if (serverId >= 0 && canAccess == true) {
			return true;
		}
		return false;
	}
	

//	@Guard(name = "canAccess")
//	public boolean canAccess(@Data(name = "serverId") Integer sid, @Data(name = "listResource") ArrayList<Resource> listResource) {
//		// System.out.println("Peer " + peerId + " registered with " + trackerId
//		// + ", interacting with " + id + ": "
//		// + (trackerId >= 0 && id == trackerId));
//		if (serverId >= 0 && sid == serverId) {
//			for (Resource r : listResource) {
//				if (r.getId() == resourceId && r.resourceAvailable() == true) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	
}
