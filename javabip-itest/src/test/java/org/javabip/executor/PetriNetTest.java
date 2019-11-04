package org.javabip.executor;

import java.util.ArrayList;

import org.javabip.api.BIPActor;
import org.javabip.api.BIPEngine;
import org.javabip.api.BIPGlue;
import org.javabip.engine.factory.EngineFactory;
import org.javabip.spec.petrinet.PetriAtom;
import org.javabip.spec.petrinet.PetriGlueBuilder;
import org.javabip.spec.petrinet.SyncPetri;

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
		PetriAtom petriAtom;
		try {
			PetriAtom petriAtom1 = new PetriAtom(1);
			PetriAtom petriAtom2 = new PetriAtom(2);
			
			ArrayList<PetriAtom> listPetriAtoms = new ArrayList<PetriAtom>();
			listPetriAtoms.add(petriAtom1); 
			listPetriAtoms.add(petriAtom2);
			SyncPetri syncPetri = new SyncPetri(listPetriAtoms);
			
			BIPActor actor1 = engine.register(petriAtom1, "atom1", false);
			BIPActor actor2 = engine.register(petriAtom2, "atom2", false);
			BIPActor actor3 = engine.register(syncPetri, "syncPetri", false);
			
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

		BIPGlue bipGlue4Hanoi = new PetriGlueBuilder()
				.build();

        demo.initialize();
        demo.runningHanoiSample(bipGlue4Hanoi);
        demo.cleanup();
    }
}
