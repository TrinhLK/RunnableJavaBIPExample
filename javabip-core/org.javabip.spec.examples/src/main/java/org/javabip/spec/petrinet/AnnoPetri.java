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

@Ports({ @Port(name = "request", type = PortType.enforceable)})
@ComponentType(initial = "zero", name = "org.bip.spec.petrinet.AnnoPetri")
public class AnnoPetri {

	Logger logger = LoggerFactory.getLogger(AnnoPetri.class);

	private int petriId;
	
	public AnnoPetri(int id) {
		// TODO Auto-generated constructor stub
		petriId = id;
	}
	
	@Transition(name = "request", source = "zero", target = "zero")
	public void getResource() {
    	//System.out.println(petriId + " - requests to get resource.");
	}
	
	
	@Data(name = "atomId", accessTypePort = AccessType.any)
	public int atomId() {
		return petriId;
	}
}
