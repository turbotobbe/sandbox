package se.lingonskogen.restblog.webapp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import se.lingonskogen.restblog.domain.RootType;

@Path("/")
public class RootResource
{
    @GET
    @Produces({
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_VIEW_ROOT_XML_VERSION,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_VIEW_ROOT_XML,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_XML_VERSION,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_XML
        })
    public RootType getRootType()
    {
        return new RootType();
    }
}
