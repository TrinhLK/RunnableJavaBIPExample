package org.javabip.spec.clientserver;

import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.api.PortType;

//@Ports({ @Port(name = "hold", type = PortType.spontaneous)})
//@ComponentType(initial = "0", name = "org.bip.spec.clientserver.Resource")
public class Resource {

	private int resourceId;
	private boolean status;
	
	public Resource(int _id) {
		// TODO Auto-generated constructor stub
		this.resourceId = _id;
		this.status = true;
	}
	
	public int getId() {
		return resourceId;
	}
	
	public boolean resourceAvailable() {
		return status;
	}
}
