package se.lingonskogen.restblog.webapp.providers.v1;

import javax.ws.rs.core.UriBuilder;

import se.lingonskogen.restblog.mediatypes.v1.Link;
import se.lingonskogen.restblog.mediatypes.v1.MediaTypes;
import se.lingonskogen.restblog.mediatypes.v1.Verb;
import se.lingonskogen.restblog.mediatypes.v1.ViewBlog;
import se.lingonskogen.restblog.mediatypes.v1.ViewBlogs;
import se.lingonskogen.restblog.mediatypes.v1.ViewPost;
import se.lingonskogen.restblog.mediatypes.v1.ViewPosts;
import se.lingonskogen.restblog.mediatypes.v1.ViewRoot;
import se.lingonskogen.restblog.webapp.BlogResource;
import se.lingonskogen.restblog.webapp.PostResource;
import se.lingonskogen.restblog.webapp.RootResource;

public class LinkBuilder
{
    public Link buildLink(String title, Verb verb, String url, String mediaType)
    {
        Link link = new Link();
        link.setTitle(title);
        link.setVerb(verb);
        link.setMediatype(mediaType);
        link.setUrl(url);
        return link;
    }

    public Link buildGet(String title, Class<?> cls, String... paths)
    {
        UriBuilder builder = null;
        String mediaType = null;
        if (ViewRoot.class.equals(cls))
        {
            builder = UriBuilder.fromResource(RootResource.class);
            mediaType = MediaTypes.APP_VIEW_ROOT_XML_VERSION;
        }
        else if (ViewBlogs.class.equals(cls))
        {
            builder = UriBuilder.fromResource(BlogResource.class);
            mediaType = MediaTypes.APP_VIEW_BLOGS_XML_VERSION;
        }
        else if (ViewPosts.class.equals(cls))
        {
            builder = UriBuilder.fromResource(PostResource.class);
            mediaType = MediaTypes.APP_VIEW_POSTS_XML_VERSION;
        }
        else if (ViewBlog.class.equals(cls))
        {
            builder = UriBuilder.fromResource(BlogResource.class);
            mediaType = MediaTypes.APP_VIEW_BLOG_XML_VERSION;
        }
        else if (ViewPost.class.equals(cls))
        {
            builder = UriBuilder.fromResource(PostResource.class);
            mediaType = MediaTypes.APP_VIEW_POST_XML_VERSION;
        }
        for (String path : paths)
        {
            builder.path(path);
        }
        return buildLink(title, Verb.GET, builder.build().toString(), mediaType);
    }

}
