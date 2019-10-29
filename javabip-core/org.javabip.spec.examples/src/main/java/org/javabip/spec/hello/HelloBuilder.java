package org.javabip.spec.hello;

import org.javabip.glue.GlueBuilder;


public class HelloBuilder extends GlueBuilder{

	@Override
	public void configure() {
		// TODO Auto-generated method stub
		//data(HelloPackage.class, "helloId").to(HelloPackage.class, "helloId");
//		port(HelloSender.class, "p").accepts();
//		port(HelloSender.class, "p").requires();
//		port(HelloReceiver.class,"p").accepts();
//		port(HelloReceiver.class, "p").requires();
//		
		data(HelloSender.class, "sendId").to(HelloReceiver.class, "sendId");
	}

}
