package org.javabip.spec.hello;

import org.javabip.executor.BehaviourBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloConnector {
	
	Logger logger = LoggerFactory.getLogger(HelloConnector.class);
	
	public BehaviourBuilder initializeBehavior() throws NoSuchMethodException {
		BehaviourBuilder behaviourBuilder = new BehaviourBuilder(this);
		return behaviourBuilder;
	}
}
