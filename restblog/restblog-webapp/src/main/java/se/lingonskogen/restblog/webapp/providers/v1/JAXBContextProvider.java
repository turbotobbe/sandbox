package se.lingonskogen.restblog.webapp.providers.v1;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import se.lingonskogen.restblog.mediatypes.v1.Blog;
import se.lingonskogen.restblog.mediatypes.v1.Link;
import se.lingonskogen.restblog.mediatypes.v1.MediaTypes;
import se.lingonskogen.restblog.mediatypes.v1.Post;
import se.lingonskogen.restblog.mediatypes.v1.Verb;
import se.lingonskogen.restblog.mediatypes.v1.ViewBlog;
import se.lingonskogen.restblog.mediatypes.v1.ViewBlogs;
import se.lingonskogen.restblog.mediatypes.v1.ViewPost;
import se.lingonskogen.restblog.mediatypes.v1.ViewPosts;
import se.lingonskogen.restblog.mediatypes.v1.ViewRoot;

@Provider
public class JAXBContextProvider implements ContextResolver<JAXBContext>
{
    private JAXBContext context = null;

    private static final List<Class<?>> CLASSES = new ArrayList<Class<?>>();

    static
    {
        CLASSES.add(ViewRoot.class);
        CLASSES.add(ViewPosts.class);
        CLASSES.add(ViewPost.class);
        CLASSES.add(ViewBlogs.class);
        CLASSES.add(ViewBlog.class);
        CLASSES.add(Verb.class);
        CLASSES.add(Post.class);
        CLASSES.add(MediaTypes.class);
        CLASSES.add(Link.class);
        CLASSES.add(Blog.class);
    };

    @Override
    public JAXBContext getContext(Class<?> type)
    {
        boolean ok = CLASSES.contains(type);

        if (ok && context == null)
        {
            try
            {
                context = JAXBContext.newInstance(CLASSES.toArray(new Class[CLASSES.size()]));
            }
            catch (JAXBException e)
            {
                e.printStackTrace();
            }
        }
        return ok ? context : null;
    }
}
