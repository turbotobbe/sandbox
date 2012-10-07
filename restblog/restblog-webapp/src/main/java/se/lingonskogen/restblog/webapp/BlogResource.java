package se.lingonskogen.restblog.webapp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import se.lingonskogen.restblog.domain.BlogType;
import se.lingonskogen.restblog.domain.BlogsType;

@Path("/blogs")
public class BlogResource
{
    @GET
    @Produces({
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_VIEW_BLOGS_XML_VERSION,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_VIEW_BLOGS_XML,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_XML_VERSION,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_XML
        })
    public BlogsType getBlogsType()
    {
        BlogsType blogsType = new BlogsType();
        blogsType.getBlogType().add(buildBlogType(2));
        blogsType.getBlogType().add(buildBlogType(3));
        return blogsType;
    }
    
    @GET
    @Path("/{bid}")
    @Produces({
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_VIEW_BLOG_XML_VERSION,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_VIEW_BLOG_XML,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_XML_VERSION,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_XML
        })
    public BlogType getBlogType(@PathParam("bid") String bid)
    {
        return buildBlogType(1);
    }

    private BlogType buildBlogType(int i)
    {
        BlogType blogType = new BlogType();
        blogType.setTitle("my-blog-" + i);
        blogType.setContent("my-blog-" + i + "-content");
        return blogType;
    }
}
