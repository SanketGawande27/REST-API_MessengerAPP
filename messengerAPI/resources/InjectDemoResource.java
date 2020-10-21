package org.sanket.messengerAPI.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("annotations")
	public String getParamsUsingAnnotation(@MatrixParam("param") String matrixParam,
			@HeaderParam("customHeader") String header, @CookieParam("Name") String cookie) {

		return "Matrix Param: " + matrixParam + " Header Params: " + header + "Cookie: " + cookie;
		// return "Matrix param: " + matrixParam + "Header param: " + header + "Cookie
		// param: " + cookie;

	}

	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, HttpHeaders headers) {
		String path = uriInfo.getAbsolutePath().toString();
		// String cookie = headers.getDate().toString();
		return "PAth:  " + path; // + "Cookies: " + cookie;
	}
}
