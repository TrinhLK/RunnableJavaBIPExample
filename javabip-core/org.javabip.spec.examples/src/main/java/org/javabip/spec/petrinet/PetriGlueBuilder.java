package org.javabip.spec.petrinet;

import org.javabip.glue.GlueBuilder;

public class PetriGlueBuilder extends GlueBuilder {

	@Override
	public void configure() {
		// TODO Auto-generated method stub
		//port(SyncPetri.class, "free_res").requires(PetriAtom.class, "sync");
		port(PetriAtom.class, "free_res").accepts(PetriAtom.class, "get_res");
		port(PetriAtom.class, "free_res").requires(PetriAtom.class, "get_res");
		port(PetriAtom.class, "get_res").accepts(PetriAtom.class, "free_res");
		port(PetriAtom.class, "get_res").requires();
		
		port(SyncPetri.class, "sync").requires(PetriAtom.class, "free_res");
		port(PetriAtom.class, "free_res").accepts(SyncPetri.class, "sync");
		
		//data(PetriAtom.class, "resource").to(SyncPetri.class,"resource");
	}

}
