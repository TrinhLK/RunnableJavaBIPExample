package org.javabip.spec.newclientserver;

import java.util.ArrayList;

import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Ports({ @Port(name = "take", type = PortType.enforceable),
	@Port(name = "execute", type = PortType.enforceable),
	@Port(name = "finish", type = PortType.enforceable)})
@ComponentType(initial = "0", name = "org.bip.spec.newclientserver.ClientServerMonitor")
public class Monitor {

	Logger logger = LoggerFactory.getLogger(Monitor.class);
	
	ArrayList<Object> listRequest;
	
	public Monitor() {
		// TODO Auto-generated constructor stub
	}
	
	@Transition(name = "take", source = "0", target = "1")
	public void takeRequest() {
		logger.debug("Taking Request");
	}
	
	@Transition(name = "execute", source = "1", target = "2")
	public void executing() {
		logger.debug("Executing request");
	}
	
	@Transition(name = "finish", source = "2", target = "0")
	public void finishing() {
		logger.debug("Finishing request");
	}
}
