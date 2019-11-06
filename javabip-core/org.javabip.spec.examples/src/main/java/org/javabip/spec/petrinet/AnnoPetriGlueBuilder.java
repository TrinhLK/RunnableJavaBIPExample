package org.javabip.spec.petrinet;

import org.javabip.glue.GlueBuilder;

public class AnnoPetriGlueBuilder extends GlueBuilder{

	@Override
	public void configure() {
		
		port(AnnoPetri.class, "request").requires(Resource.class, "get_res");
		port(AnnoPetri.class, "request").requires(Resource.class, "free_res", SynchronizerResource.class, "sync");
		port(Resource.class, "free_res").requires(AnnoPetri.class, "request");
		port(Resource.class, "free_res").requires(SynchronizerResource.class, "sync");
		port(Resource.class, "get_res").requires(AnnoPetri.class, "request");
		port(SynchronizerResource.class, "sync").requires(Resource.class, "free_res");
		port(SynchronizerResource.class, "sync").requires(AnnoPetri.class, "request");
		
		port(SynchronizerResource.class, "sync").accepts(Resource.class, "free_res", AnnoPetri.class, "request");
		port(Resource.class, "free_res").accepts(SynchronizerResource.class, "sync", AnnoPetri.class, "request");
		port(Resource.class, "get_res").accepts(AnnoPetri.class, "request");
		port(AnnoPetri.class, "request").accepts(Resource.class, "get_res", "free_res", SynchronizerResource.class, "sync");
		
//		port(AnnoPetri.class, "request").requires(Resource.class, "get_res");
//		port(AnnoPetri.class, "releash").requires(Resource.class, "free_res");
//		//port(Resource.class, "get_res").requires(AnnoPetri.class, "request");
//		port(Resource.class, "free_res").requires(AnnoPetri.class, "releash");
//		port(SynchronizerResource.class, "sync").requires(AnnoPetri.class, "request");
//		port(SynchronizerResource.class, "sync").requires(Resource.class, "free_res");
//		
//		port(AnnoPetri.class, "request").accepts(Resource.class, "get_res", Resource.class, "free_res");
//		port(Resource.class, "get_res").accepts(AnnoPetri.class, "request");
//		port(Resource.class, "free_res").accepts(AnnoPetri.class, "releash", SynchronizerResource.class, "sync");
//		port(AnnoPetri.class, "releash").accepts(Resource.class, "free_res");
//		port(SynchronizerResource.class, "sync").accepts(AnnoPetri.class, "releash", Resource.class, "free_res");
		
		data(AnnoPetri.class,"atomId").to(Resource.class, "atomId");
		data(Resource.class, "resourceAvailable").to(SynchronizerResource.class, "resourceAvailable");
//		data(SynchronizerResource.class, "resourceSTT").to(Resource.class, "resourceSTT");
	}
}
