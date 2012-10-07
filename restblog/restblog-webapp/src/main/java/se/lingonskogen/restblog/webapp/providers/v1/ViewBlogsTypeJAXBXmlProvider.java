package se.lingonskogen.restblog.webapp.providers.v1;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import se.lingonskogen.restblog.domain.BlogType;
import se.lingonskogen.restblog.domain.BlogsType;
import se.lingonskogen.restblog.mediatypes.v1.Blog;
import se.lingonskogen.restblog.mediatypes.v1.ViewBlog;
import se.lingonskogen.restblog.mediatypes.v1.ViewBlogs;

@Provider
public class ViewBlogsTypeJAXBXmlProvider extends JAXBXmlProvider<BlogsType, ViewBlogs>
{
    private LinkBuilder linkBuilder = new LinkBuilder();
    
    public ViewBlogsTypeJAXBXmlProvider(@Context HttpHeaders headers)
    {
        super(headers, BlogsType.class, ViewBlogs.class);
    }

    @Override
    protected ViewBlogs toMediaType(BlogsType t)
    {
        ViewBlogs viewBlogs = new ViewBlogs();
        for (BlogType blogType : t.getBlogType())
        {
            Blog blog = new Blog();
            blog.setTitle(blogType.getTitle());
            blog.setContent(blogType.getContent());
            ViewBlog viewBlog = new ViewBlog();
            viewBlog.setBlog(blog);
            viewBlog.getLink().add(linkBuilder.buildGet("Self", ViewBlog.class, blogType.getTitle()));
            viewBlogs.getViewBlog().add(viewBlog);
        }
        return viewBlogs;
    }

    @Override
    protected BlogsType fromMediaType(ViewBlogs s)
    {
        BlogsType blogsType = new BlogsType();
        for (ViewBlog viewBlog : s.getViewBlog())
        {
            Blog blog = viewBlog.getBlog();
            BlogType blogType = new BlogType();
            blogType.setTitle(blog.getTitle());
            blogType.setContent(blog.getContent());
            blogsType.getBlogType().add(blogType);
        }
        return blogsType;
    }
}
