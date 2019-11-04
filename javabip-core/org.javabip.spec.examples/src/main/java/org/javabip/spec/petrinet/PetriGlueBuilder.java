package org.javabip.spec.petrinet;

import org.javabip.glue.GlueBuilder;

public class PetriGlueBuilder extends GlueBuilder {

	@Override
	public void configure() {
		// TODO Auto-generated method stub
		//p accepts a: p chỉ nhận tương tác với cổng a, ngoài ra không tương tác vs cổng khác
		//p requires a: 
		//port(SyncPetri.class, "free_res").requires(PetriAtom.class, "sync");
		
		//port(PetriAtom.class, "free_res").requires(PetriAtom.class, "get_res");
		//port(PetriAtom.class, "free_res").accepts(PetriAtom.class, "get_res", SyncPetri.class, "sync");
		//port(SyncPetri.class, "sync").requires(PetriAtom.class, "get_res");
		port(SyncPetri.class, "sync").requires(PetriAtom.class, "free_res");
		
		
//		port(PetriAtom.class, "get_res").accepts(SyncPetri.class, "sync");
//		port(PetriAtom.class, "free_res").accepts(SyncPetri.class, "sync");
//		port(SyncPetri.class, "sync").accepts(PetriAtom.class, "get_res", "free_res");
		
		//data(PetriAtom.class, "resource").to(SyncPetri.class,"resource");
	}

}
/**
 * Nếu chỉ có requires: free requires get, sync requires free thì nó sẽ chi chạy hai hàm get_resource.
 * Nếu thêm phần get accepts free: sẽ chạy get rồi free lần lượt, tuy nhiên tới atom thứ 2 chỉ có get
 * */
