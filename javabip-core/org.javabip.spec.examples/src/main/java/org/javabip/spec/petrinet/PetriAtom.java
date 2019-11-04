package org.javabip.spec.petrinet;

import org.javabip.annotations.Data;
import org.javabip.annotations.ExecutableBehaviour;
import org.javabip.api.PortType;
import org.javabip.api.DataOut.AccessType;
import org.javabip.executor.BehaviourBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PetriAtom {
	
	Logger logger = LoggerFactory.getLogger(PetriAtom.class);

	private int petriId;
	public static boolean resource;
	public static int curId = 0;
	
    public PetriAtom(int id) {
    	petriId = id;
    }

    @ExecutableBehaviour
    public BehaviourBuilder initializeBehavior() throws NoSuchMethodException {
  	
		BehaviourBuilder behaviourBuilder = new BehaviourBuilder(this);
		behaviourBuilder.setComponentType( this.getClass().getCanonicalName() );
		//add states
		behaviourBuilder.addState("GET");
		behaviourBuilder.addState("USE");
		behaviourBuilder.addState("SYNC");
		  
		//add ports
		behaviourBuilder.addPort("get_res", PortType.enforceable, this.getClass());
		behaviourBuilder.addPort("free_res", PortType.enforceable, this.getClass());
		  
		//Initial states
		behaviourBuilder.setInitialState("GET");
		
		//Transitions
		behaviourBuilder.addTransitionAndStates("get_res", "GET", "USE", "", PetriAtom.class.getMethod("getRS"));
		behaviourBuilder.addTransitionAndStates("free_res", "USE", "GET", "", PetriAtom.class.getMethod("freeRS"));
		  
		return behaviourBuilder;
    }

    public void getRS() {
    	if (resource == false && curId == 0) {
    		resource = true;
        	curId = petriId;
    		System.out.println(petriId + ": get resource\t" + "curId = " + curId + "\t resource taken = " + resource);
    	}
    }
    
    public void freeRS() {
    	if (resource == true && curId == petriId) {
    		System.out.println(petriId + ": free resource");
        	resource = false;
        	curId = 0;
    	}
    }
    
    @Data(name = "petriId", accessTypePort = AccessType.any)
	public int petriId() {
		return petriId;
	}
    
    @Data(name = "resource", accessTypePort = AccessType.any)
	public boolean resource() {
		return resource;
	}
}
