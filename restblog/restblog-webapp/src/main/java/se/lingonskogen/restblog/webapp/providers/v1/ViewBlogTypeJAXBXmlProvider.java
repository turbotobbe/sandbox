package se.lingonskogen.restblog.webapp.providers.v1;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import se.lingonskogen.restblog.domain.BlogType;
import se.lingonskogen.restblog.mediatypes.v1.Blog;
import se.lingonskogen.restblog.mediatypes.v1.ViewBlog;
import se.lingonskogen.restblog.mediatypes.v1.ViewPosts;

@Provider
public class ViewBlogTypeJAXBXmlProvider extends JAXBXmlProvider<BlogType, ViewBlog>
{
    private LinkBuilder linkBuilder = new LinkBuilder();
    
    public ViewBlogTypeJAXBXmlProvider(@Context HttpHeaders headers)
    {
        super(headers, BlogType.class, ViewBlog.class);
    }

    @Override
    protected ViewBlog toMediaType(BlogType t)
    {
        Blog blog = new Blog();
        blog.setTitle(t.getTitle());
        blog.setContent(t.getContent());
        ViewBlog viewBlog = new ViewBlog();
        viewBlog.setBlog(blog);
        viewBlog.getLink().add(linkBuilder.buildGet("Self", ViewBlog.class, t.getTitle()));
        viewBlog.getLink().add(linkBuilder.buildGet("Posts", ViewPosts.class, t.getTitle()));
        return viewBlog;
    }

    @Override
    protected BlogType fromMediaType(ViewBlog s)
    {
        Blog blog = s.getBlog();
        BlogType blogType = new BlogType();
        blogType.setTitle(blog.getTitle());
        blogType.setContent(blog.getContent());
        return blogType;
    }
}
