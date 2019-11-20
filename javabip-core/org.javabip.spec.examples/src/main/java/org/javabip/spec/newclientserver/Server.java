package org.javabip.spec.newclientserver;

import java.util.ArrayList;

import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
import org.javabip.api.DataOut.AccessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Ports({ //@Port(name = "start", type = PortType.enforceable),
	@Port(name = "publish", type = PortType.enforceable),
	@Port(name = "move", type = PortType.enforceable),
	@Port(name = "receive", type = PortType.enforceable),
	@Port(name = "close", type = PortType.enforceable)})
@ComponentType(initial = "0", name = "org.bip.spec.newclientserver.Server")
public class Server {

	Logger logger = LoggerFactory.getLogger(Server.class);
	public static ArrayList<Resource> listResource;
	private int serverId;
	private int resourceId;
	private boolean canBeAccessed;
	
	public Server(int _id) {
		// TODO Auto-generated constructor stub
		serverId = _id;
		listResource = new ArrayList<Resource>();
		
	}
	
	public Server(int _id, Resource... args) {
		serverId = _id;
		listResource = new ArrayList<Resource>();
		System.out.println("Create Server: " + serverId);
		for (Resource res : args) {
			if (!isExistedInResourceList(res)) {
				listResource.add(res);
			}
		}
	}	
	
	@Transition(name = "publish", source = "0", target = "0", guard = "canActive")
	public void running(@Data(name = "resourceIdToUse")Integer id) {
		System.out.println("Server {" + serverId + "} is running....");
		resourceId = id;
		if (getResourceWithId(id) != null) {
			System.out.println("Server {" + serverId + "}: Resource{" + id + "} can be accessed");
			canBeAccessed = true;
		}else {
			canBeAccessed = false;
		}
		
	}
	
	@Transition(name = "receive", source = "0", target = "0", guard = "checkResource")
	public void receiving(@Data(name = "resourceId") Integer rId) {
		System.out.println("Server {" + serverId + "}: RECEIVED resource {" + rId + "}");
		listResource.add(new Resource(rId));
	}
	
	@Transition(name = "move", source = "0", target = "1", guard = "canMove")
	public void moving(@Data(name = "serverId")Integer newSid) {
		System.out.println("Server {" + serverId + "}: is MOVING resource{" + resourceId + "} to Server{" + newSid + "}");
		canBeAccessed = false;
	}
	
	@Transition(name = "close", source = "1", target = "2", guard = "canActive")
	public void closing() {
		System.out.println("Server {" + serverId + "}: Closed");
		listResource = null;
		serverId = -1;
	}
	
	@Guard(name = "canMove")
	public boolean canMove(@Data(name = "serverId") Integer newsId) {
		if (canBeAccessed == true && newsId >=0) {
			return true;
		}
		return false;
	}
	
	@Guard(name = "canActive")
	public boolean canActive() {
		return (serverId >= 0);
	}
	
	@Guard(name = "checkResource")
	public boolean getResource(@Data(name = "resourceId") Integer rId) {
		return (rId > 0);
	}
	
	@Data (name = "resourceId", accessTypePort = AccessType.any)
	public int getResourceId() {
		return resourceId;
	}
	
	@Data (name = "accessed", accessTypePort = AccessType.any)
	public boolean isAccessed() {
		return canBeAccessed;
	}
	
	@Data(name = "serverId", accessTypePort = AccessType.any)
	public int getServerId() {
		return serverId;
	}
	
	
	//@Data(name = "resource", accessTypePort = AccessType.any)
	public Resource getResourceWithId(int id) {
		for (Resource cur : listResource) {
			if (cur.getId() == id) {
				return cur;
			}
		}
		return null;
	}
	
	public boolean isExistedInResourceList(Resource res) {
		for (Resource cur : listResource) {
			if (cur.getId() == res.getId())
				return true;
		}
		return false;
	}
	
	
}
