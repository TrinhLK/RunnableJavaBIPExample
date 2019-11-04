package org.javabip.spec.petrinet;

import java.util.ArrayList;

import org.javabip.annotations.Data;
import org.javabip.annotations.ExecutableBehaviour;
import org.javabip.annotations.Guard;
import org.javabip.api.PortType;
import org.javabip.executor.BehaviourBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncPetri {

	Logger logger = LoggerFactory.getLogger(SyncPetri.class);
	ArrayList<PetriAtom> listAtom = new ArrayList<PetriAtom>();

    public SyncPetri(ArrayList<PetriAtom> _listAtom) {
    	listAtom = _listAtom;
    }

    @ExecutableBehaviour
    public BehaviourBuilder initializeBehavior() throws NoSuchMethodException {
  	
		BehaviourBuilder behaviourBuilder = new BehaviourBuilder(this);
		behaviourBuilder.setComponentType( this.getClass().getCanonicalName() );
		//add states
		behaviourBuilder.addState("SYNC");
		  
		//add ports
		behaviourBuilder.addPort("sync", PortType.enforceable, this.getClass());
		  
		//Initial states
		behaviourBuilder.setInitialState("SYNC");
		
		//Transitions
		behaviourBuilder.addTransitionAndStates("sync", "SYNC", "SYNC", "resourceAvailable", SyncPetri.class.getMethod("synch"));
		  
		return behaviourBuilder;
    }
    
    public void synch() {
    	
		String rs = "";
		for (PetriAtom a : listAtom) {
			rs += a.petriId() + " ";
		}
		
		System.out.println(rs + ": synchronize");
    	
    }
    
    @Guard(name = "resourceAvailable")
    public boolean resourceAvailable(@Data(name = "resource") Boolean res) {
    	return (res == false);
    }

}
