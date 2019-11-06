package org.javabip.executor;

import java.util.ArrayList;

import org.javabip.api.BIPActor;
import org.javabip.api.BIPEngine;
import org.javabip.api.BIPGlue;
import org.javabip.engine.factory.EngineFactory;
import org.javabip.spec.petrinet.*;

import akka.actor.ActorSystem;

public class PetriNetTest {

	private ActorSystem system;
    private EngineFactory engineFactory;

    private void initialize() {
        system = ActorSystem.create("MySystem");
        engineFactory = new EngineFactory(system);
    }

    private void cleanup() {
        system.shutdown();
    }
    
    public void runningHanoiSample(BIPGlue bipGlue) {

    	BIPEngine engine = engineFactory.create("myEngine", bipGlue);

		int size = 3;

		//HanoiOptimalMonitor hanoiMonitor;
		try {
			AnnoPetri petriAtom1 = new AnnoPetri(1);
			AnnoPetri petriAtom2 = new AnnoPetri(2);
			Resource res = new Resource();
			
			ArrayList<AnnoPetri> listAnno = new ArrayList<AnnoPetri>();
			listAnno.add(petriAtom1); listAnno.add(petriAtom2);
			SynchronizerResource sync = new SynchronizerResource(listAnno);
			
			BIPActor actor1 = engine.register(petriAtom1, "atom1", true);
			BIPActor actor2 = engine.register(petriAtom2, "atom2", true);
			BIPActor actor3 = engine.register(res, "resource", true);
			BIPActor actor4 = engine.register(sync, "sync", true);
			
			engine.specifyGlue(bipGlue);
			engine.start();

			engine.execute();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			//System.out.println("Finished test, number of transitions " + petriAtom.getNumberOfMoves());
			System.out.flush();
			
			engine.stop();
			engineFactory.destroy(engine);
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
    
    public static void main (String[] args) {
        PetriNetTest demo = new PetriNetTest();

		BIPGlue bipGlue4Hanoi = new AnnoPetriGlueBuilder()
				.build();

        demo.initialize();
        demo.runningHanoiSample(bipGlue4Hanoi);
        demo.cleanup();
    }
}
