package org.javabip.spec.priority;

import org.javabip.glue.GlueBuilder;

public class AnnoPetriGlueBuilder extends GlueBuilder{

	@Override
	public void configure() {
		// TODO Auto-generated method stub
		port(AnnoPetri.class, "request").requires(Resource.class, "get_res");
		port(AnnoPetri.class, "unrequest").requires(Resource.class, "free_res");
		port(Resource.class, "get_res").requires(AnnoPetri.class, "request");
		port(Resource.class, "free_res").requires(AnnoPetri.class, "unrequest");
		//port(SynchronizerResource.class, "sync").requires(AnnoPetri.class, "unrequest", Resource.class, "free_res");
		port(Resource.class, "free_res").requires(SynchronizerResource.class, "sync");
		port(SynchronizerResource.class, "sync").requires(Resource.class, "free_res");
		
		port(AnnoPetri.class, "request").accepts(Resource.class, "get_res");
		port(AnnoPetri.class, "unrequest").accepts(Resource.class, "free_res");
		port(Resource.class, "get_res").accepts(AnnoPetri.class, "request");
		port(Resource.class, "free_res").accepts(AnnoPetri.class, "unrequest");
		port(SynchronizerResource.class, "sync").accepts(Resource.class, "free_res", AnnoPetri.class, "unrequest");
		
		data(AnnoPetri.class,"atomId").to(Resource.class, "atomId");
		data(Resource.class, "resourceAvailable").to(SynchronizerResource.class, "resourceAvailable");
	}

}
