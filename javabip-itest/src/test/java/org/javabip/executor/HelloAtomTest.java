package org.javabip.executor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.javabip.api.BIPActor;
import org.javabip.api.BIPEngine;
import org.javabip.api.BIPGlue;
import org.javabip.engine.factory.EngineFactory;
import org.javabip.glue.GlueBuilder;
import org.javabip.spec.hello.HelloGlueBuilder;
import org.javabip.spec.hello.HelloReceiver;
import org.javabip.spec.hello.HelloReceiver1;
import org.javabip.spec.hello.HelloReceiver2;
import org.javabip.spec.hello.HelloReceiver3;
import org.javabip.spec.hello.HelloSender;
import org.javabip.spec.helloatom.HelloAtomGlueBuilder;
import org.javabip.spec.helloatom.HelloAtomLoop;
import org.javabip.spec.helloatom.LoopConnector;
import org.javabip.spec.helloatom.Plus;

import akka.actor.ActorSystem;

public class HelloAtomTest {
	private ActorSystem system;
    private EngineFactory engineFactory;
    
    private void initialize() {
        system = ActorSystem.create("MySystem");
        engineFactory = new EngineFactory(system);
    }
    
    private void cleanup() {
        system.shutdown();
    }
    
    private BIPGlue createGlue(String bipGlueFilename) {
		BIPGlue bipGlue = null;

		InputStream inputStream;
		try {
			inputStream = new FileInputStream(bipGlueFilename);

			bipGlue = GlueBuilder.fromXML(inputStream);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return bipGlue;
	}
    
    public void runningTrackerPeer() {
    	//BIPGlue bipGlue = createGlue("src/test/resources/trackerPeerGlue.xml");
    	BIPGlue bipGlue = new HelloAtomGlueBuilder().build();
    	
//    	try {
//			bipGlue.toXML(new FileOutputStream(new File("Tracker-Peer.xml")));
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
    	
    	BIPEngine engine = engineFactory.create("myEngine", bipGlue);
    	
    	//Component
//		HelloAtom hello1 = new HelloAtom(1);
//		HelloAtom hello2 = new HelloAtom(2);
//		HelloAtom hello3 = new HelloAtom(3);
//		
//		//Connector
//		final BIPActor executor1 = engine.register(hello1, "1", true);
//		final BIPActor executor2 = engine.register(hello2, "2", true);
//		final BIPActor executor3 = engine.register(hello3, "3", true);
//		LoopConnector lc = new LoopConnector(2);
    	
		HelloAtomLoop a1 = new HelloAtomLoop(1);
		HelloAtomLoop a2 = new HelloAtomLoop(2);
		HelloAtomLoop a3 = new HelloAtomLoop(3);
		HelloAtomLoop a4 = new HelloAtomLoop(4);
		HelloAtomLoop a5 = new HelloAtomLoop(5);
		HelloAtomLoop a6 = new HelloAtomLoop(6);
		HelloAtomLoop a7 = new HelloAtomLoop(7);
		HelloAtomLoop a8 = new HelloAtomLoop(8);
		HelloAtomLoop a9 = new HelloAtomLoop(9);

		Plus p12 = new Plus(a1, a2);
		
//		final BIPActor executorLC = engine.register(lc, "2", true);
		final BIPActor executor1 = engine.register(a1, "1", true);
		final BIPActor executor2 = engine.register(a1, "2", true);
		final BIPActor executor3 = engine.register(a1, "3", true);
		final BIPActor executor4 = engine.register(a1, "4", true);
		final BIPActor executor5 = engine.register(a1, "5", true);
		final BIPActor executor6 = engine.register(a1, "6", true);
		final BIPActor executor7 = engine.register(a1, "7", true);
		final BIPActor executor8 = engine.register(a1, "8", true);
		final BIPActor exePlus12 = engine.register(p12, "12", true);
		
		engine.specifyGlue(bipGlue);
		engine.start();
		
		engine.execute();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		engine.stop();
		engineFactory.destroy(engine);
		
		//assertTrue("Hello 1 has not made any transitions", hello1.noOfTransitions > 0);
		
    }
    
    public static void main (String[] args) {
    	HelloAtomTest testTP = new HelloAtomTest();
		testTP.initialize();
		testTP.runningTrackerPeer();
		testTP.cleanup();
    }
}
