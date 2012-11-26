package se.lingonskogen.sandbox.logserver;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class RootResource
{
	private final static Logger LOG = Logger.getLogger(RootResource.class.getName());
	
    @GET
    public Response getRoot()
    {
    	LOG.info("Get Root!");
        return Response.ok("OK", MediaType.TEXT_PLAIN).build();
    }
}
