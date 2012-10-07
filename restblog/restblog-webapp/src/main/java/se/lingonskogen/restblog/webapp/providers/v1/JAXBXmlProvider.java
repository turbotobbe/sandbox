package se.lingonskogen.restblog.webapp.providers.v1;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import se.lingonskogen.restblog.common.MediaTypeBuilder;
import se.lingonskogen.restblog.domain.BlogType;
import se.lingonskogen.restblog.domain.BlogsType;
import se.lingonskogen.restblog.domain.PostType;
import se.lingonskogen.restblog.domain.PostsType;
import se.lingonskogen.restblog.domain.RootType;
import se.lingonskogen.restblog.mediatypes.v1.MediaTypes;
import se.lingonskogen.restblog.webapp.AbstractJAXBProvider;

public abstract class JAXBXmlProvider<T, S> extends AbstractJAXBProvider<T, S>
{
    private static final Map<Class<?>, MediaType> GENERICS = new HashMap<Class<?>, MediaType>();
    
    private static final Map<Class<?>, MediaType> SPECIFICS = new HashMap<Class<?>, MediaType>();

    static
    {
        GENERICS.put(RootType.class, MediaTypeBuilder.build(MediaTypes.APP_XML_VERSION));
        SPECIFICS.put(RootType.class, MediaTypeBuilder.build(MediaTypes.APP_VIEW_ROOT_XML_VERSION));
        
        GENERICS.put(BlogType.class, MediaTypeBuilder.build(MediaTypes.APP_XML_VERSION));
        SPECIFICS.put(BlogType.class, MediaTypeBuilder.build(MediaTypes.APP_VIEW_BLOG_XML_VERSION));
        GENERICS.put(BlogsType.class, MediaTypeBuilder.build(MediaTypes.APP_XML_VERSION));
        SPECIFICS.put(BlogsType.class, MediaTypeBuilder.build(MediaTypes.APP_VIEW_BLOGS_XML_VERSION));
        
        GENERICS.put(PostType.class, MediaTypeBuilder.build(MediaTypes.APP_XML_VERSION));
        SPECIFICS.put(PostType.class, MediaTypeBuilder.build(MediaTypes.APP_VIEW_POST_XML_VERSION));
        GENERICS.put(PostsType.class, MediaTypeBuilder.build(MediaTypes.APP_XML_VERSION));
        SPECIFICS.put(PostsType.class, MediaTypeBuilder.build(MediaTypes.APP_VIEW_POSTS_XML_VERSION));
    }
    
    protected JAXBXmlProvider(HttpHeaders headers, Class<? extends T> clsT, Class<? extends S> clsS)
    {
        super(headers, clsT, clsS, GENERICS.get(clsT), SPECIFICS.get(clsT));
    }
    
}
