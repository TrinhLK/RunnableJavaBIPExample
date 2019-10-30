package org.javabip.spec.hello;

import org.javabip.glue.GlueBuilder;


public class HelloGlueBuilder extends GlueBuilder{

	@Override
	public void configure() {
		// TODO Auto-generated method stub
		port(HelloSender.class, "s").accepts(HelloReceiver.class, "r");
		port(HelloReceiver.class, "r").requires(HelloSender.class, "s");
		
		//One to One
		data(HelloSender.class, "sendId").to(HelloReceiver.class, "sendId");
	}

}
