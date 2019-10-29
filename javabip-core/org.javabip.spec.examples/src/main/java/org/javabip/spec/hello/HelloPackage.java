package org.javabip.spec.hello;

import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Ports({ @Port(name = "p", type = PortType.enforceable) })
@ComponentType(initial = "START", name = "org.bip.spec.hello.HelloPackage")
public class HelloPackage {

	public int noOfTransitions = 0;
	private String content;
	
	Logger logger = LoggerFactory.getLogger(HelloPackage.class);

	public HelloPackage(String _content) {
		content = _content;
	}
	
	@Transition(name = "p", source = "START", target = "END")
	public void moving() {
		System.out.println("Moving " + this.content + " START to END.");
		noOfTransitions++;
	}
}
