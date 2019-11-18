package org.javabip.spec.clientserver;

import java.util.ArrayList;

import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.DataOut.AccessType;
import org.javabip.api.PortType;

@Ports({ @Port(name = "run", type = PortType.enforceable),
	@Port(name = "announce", type = PortType.enforceable),
	@Port(name = "receive", type = PortType.enforceable)})
@ComponentType(initial = "0", name = "org.bip.spec.clientserver.NewServer")
public class NewServer{

	private ArrayList<Resource> listResource;
	private int nServerId;
	
	public NewServer(int _id) {
		// TODO Auto-generated constructor stub
		this.nServerId = _id;
		listResource = new ArrayList<Resource>();
		System.out.println("Create NewServer: " + nServerId);
	}
	
	@Transition(name = "receive", source = "0", target = "1", guard = "checkResource")
	public void receiving(@Data(name = "resourceId") Integer rId) {
		//listResource.add(rs);
		System.out.println("Server {" + nServerId + "}: RECEIVED resource {" + rId + "}");
		//listResource.add(new Resource(rId));
	}
	
	@Transition(name = "run", source = "0", target = "0")
	public void running() {
		System.out.println("Server{" + nServerId + "} is running....");
	}
	
	@Transition(name = "announce", source = "1", target = "0")
	public void announcing() {
		System.out.println("Server{" + nServerId + "} FINISHED the transition");
	}
	
	@Data(name = "newServer", accessTypePort = AccessType.any)
	public int getNewServerId() {
		return nServerId;
	}
	
	@Guard(name = "checkResource")
	public boolean getResource(@Data(name = "resourceId") Integer rId) {
		return (rId > 0);
	}
}
