package org.javabip.spec.hello;

import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Ports({ @Port(name = "r", type = PortType.enforceable) })
@ComponentType(initial = "START", name = "org.bip.spec.hello.HelloReceiver")
public class HelloReceiver {

	Logger logger = LoggerFactory.getLogger(HelloReceiver.class);
	private int data;
	private int receiveId;
	
	public HelloReceiver(int id) {
		this.data = -1;
		receiveId = id;
	}
	
	@Transition(name = "r", source = "START", target = "END")
	public void receiving(@Data(name = "transferedData") Integer id) {
		this.data = id;
		System.out.println("--- " + id);
		if (receiveId == 1 || receiveId == 3)
			System.out.println("I'm " + receiveId + ", Hello World received from " + data);
	}
	
}
