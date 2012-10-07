package se.lingonskogen.restblog.webapp.providers.v1;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import se.lingonskogen.restblog.domain.RootType;
import se.lingonskogen.restblog.mediatypes.v1.ViewBlogs;
import se.lingonskogen.restblog.mediatypes.v1.ViewPosts;
import se.lingonskogen.restblog.mediatypes.v1.ViewRoot;

@Provider
public class ViewRootTypeJAXBXmlProvider extends JAXBXmlProvider<RootType, ViewRoot>
{
    private LinkBuilder linkBuilder = new LinkBuilder();
    
    public ViewRootTypeJAXBXmlProvider(@Context HttpHeaders headers)
    {
        super(headers, RootType.class, ViewRoot.class);
    }

    @Override
    protected ViewRoot toMediaType(RootType t)
    {
        
        ViewRoot viewRoot = new ViewRoot();

        viewRoot.getLink().add(linkBuilder.buildGet("Root", ViewRoot.class));
        viewRoot.getLink().add(linkBuilder.buildGet("Blogs", ViewBlogs.class));
        viewRoot.getLink().add(linkBuilder.buildGet("Posts", ViewPosts.class));
        return viewRoot;
    }

    @Override
    protected RootType fromMediaType(ViewRoot s)
    {
        return new RootType();
    }
}
