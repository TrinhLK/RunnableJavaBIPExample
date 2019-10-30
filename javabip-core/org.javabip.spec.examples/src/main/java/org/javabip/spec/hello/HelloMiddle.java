package org.javabip.spec.hello;

import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
import org.javabip.api.DataOut.AccessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Ports({ @Port(name = "t", type = PortType.enforceable) })
@ComponentType(initial = "START", name = "org.bip.spec.hello.HelloMiddle")
public class HelloMiddle {

	Logger logger = LoggerFactory.getLogger(HelloMiddle.class);
	private int data;
	private int middleId;
	
	public HelloMiddle(int id) {
		this.data = -1;
		middleId = id;
	}
	
	@Transition(name = "t", source = "START", target = "END")
	public void receiving(@Data(name = "sendId") Integer id) {
		this.data = id;
		System.out.println("Middle takes Hello World from " + this.data);
	}
	
	@Data(name = "transferedData", accessTypePort = AccessType.any)
	public int transferedData() {
		//System.out.println("..." + data);
		return data;
	}
}
