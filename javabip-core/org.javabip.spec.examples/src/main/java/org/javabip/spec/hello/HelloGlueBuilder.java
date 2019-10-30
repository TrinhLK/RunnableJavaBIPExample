package org.javabip.spec.hello;

import org.javabip.glue.GlueBuilder;


public class HelloGlueBuilder extends GlueBuilder{

	@Override
	public void configure() {
		// TODO Auto-generated method stub
		//One2Three
//		port(HelloSender.class, "s").accepts(HelloReceiver.class, "r");
//		port(HelloReceiver.class, "r").requires(HelloSender.class, "s");
		
		//One2One
		port(HelloSender.class, "s").accepts(HelloMiddle.class, "t");
		port(HelloMiddle.class, "t").requires(HelloSender.class, "s");
		port(HelloMiddle.class, "t").accepts(HelloReceiver.class, "r");
		port(HelloReceiver.class, "r").requires(HelloMiddle.class, "t");
		//One to One
		data(HelloSender.class, "sendId").to(HelloMiddle.class, "sendId");
		data(HelloMiddle.class, "transferedData").to(HelloReceiver.class, "transferedData");
	}

}
