package org.javabip.spec.helloatom;

import org.javabip.annotations.ExecutableBehaviour;
import org.javabip.api.PortType;
import org.javabip.executor.BehaviourBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoopConnector {

	Logger logger = LoggerFactory.getLogger(LoopConnector.class);

    private int size;

    int numberOfActive = 0;

    public LoopConnector(int size) {
        this.size = size;
    }

    public int getNumberOfMoves() {
        return numberOfActive;
    }
    
    @ExecutableBehaviour
    public BehaviourBuilder initializeBehavior() throws NoSuchMethodException {
    	
    	BehaviourBuilder behaviourBuilder = new BehaviourBuilder(this);
    	behaviourBuilder.setComponentType( this.getClass().getCanonicalName() );
    	
    	behaviourBuilder.addState("state-1st");
        behaviourBuilder.addState("state-2nd");
        
        behaviourBuilder.addPort("12", PortType.enforceable, this.getClass());
        
        behaviourBuilder.setInitialState("state-1st");
        behaviourBuilder.addTransitionAndStates("12", "state-1st", "state-2st", "", LoopConnector.class.getMethod("plus12"));
        
    	return behaviourBuilder;
    }
    
    public void plus12(HelloAtomLoop a1, HelloAtomLoop a2) {
    	numberOfActive = a1.getActive() + a2.getActive();
        logger.debug("add 1st.active and 2nd.active");
        a1.setActive(numberOfActive);
        a2.setActive(numberOfActive);
    }
}
