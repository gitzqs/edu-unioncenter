package com.zqs.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/test")
public interface TestService {
	
	@POST
	@Path("/a")
	String test();
}
