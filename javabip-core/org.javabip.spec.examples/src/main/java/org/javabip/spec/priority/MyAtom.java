package org.javabip.spec.priority;

import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Ports({ @Port(name = "p", type = PortType.enforceable),
	@Port(name = "q", type = PortType.enforceable) })
@ComponentType(initial = "LOOP", name = "org.bip.spec.priority.MyAtom")
public class MyAtom {

	Logger logger = LoggerFactory.getLogger(MyAtom.class);
	
	@Transition(name = "p", source = "LOOP", target = "LOOP")
	public void pTrans() {
		System.out.println("On port P");
	}

	@Transition(name = "q", source = "LOOP", target = "LOOP")
	public void qTrans() {
		System.out.println("On port Q");
	}
}
