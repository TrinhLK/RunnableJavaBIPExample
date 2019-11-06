package org.javabip.spec.petrinet;

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

@Ports({ @Port(name = "get_res", type = PortType.enforceable),
	@Port(name = "free_res", type = PortType.enforceable)})
@ComponentType(initial = "zero", name = "org.bip.spec.petrinet.AnnoSync")
public class Resource {

	Logger logger = LoggerFactory.getLogger(Resource.class);
	public int petriId;
	public int resourceAvailable;
	
	public Resource() {
		//listAtom = _listAtom;
		petriId = 0;
		resourceAvailable = 1;
	}
	
	@Transition(name = "get_res", source = "zero", target = "one")
	public void getting(@Data(name = "atomId") Integer id) {
		this.petriId = id;
		System.out.println(petriId + ": get resource");
		resourceAvailable = 0;
	}
	
	@Transition(name = "free_res", source = "one", target = "zero", guard = "canInteract")
	public void freeing() {
    	
		System.out.println(petriId + ": free resource");
		resourceAvailable = 1;
    }
	
	@Guard(name = "canInteract")
	public boolean canInteract(@Data(name = "atomId") Integer id) {
		// System.out.println("Peer " + peerId + " registered with " + trackerId
		// + ", interacting with " + id + ": "
		// + (trackerId >= 0 && id == trackerId));
		return (petriId >= 0 && id == petriId);
	}
	
	@Data(name = "resourceAvailable", accessTypePort = AccessType.any)
	public int resourceAvai() {
		return resourceAvailable;
	}
}
