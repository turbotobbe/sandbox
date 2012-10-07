package se.lingonskogen.restblog.webapp.providers.v1;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import se.lingonskogen.restblog.domain.PostType;
import se.lingonskogen.restblog.mediatypes.v1.Post;
import se.lingonskogen.restblog.mediatypes.v1.ViewPost;

@Provider
public class ViewPostTypeJAXBXmlProvider extends JAXBXmlProvider<PostType, ViewPost>
{
    private LinkBuilder linkBuilder = new LinkBuilder();
    
    public ViewPostTypeJAXBXmlProvider(@Context HttpHeaders headers)
    {
        super(headers, PostType.class, ViewPost.class);
    }

    @Override
    protected ViewPost toMediaType(PostType t)
    {
        Post post = new Post();
        post.setTitle(t.getTitle());
        post.setContent(t.getContent());
        ViewPost viewPost = new ViewPost();
        viewPost.setPost(post);
        viewPost.getLink().add(linkBuilder.buildGet("Self", ViewPost.class, t.getTitle()));
        return viewPost;
    }

    @Override
    protected PostType fromMediaType(ViewPost s)
    {
        Post blog = s.getPost();
        PostType blogType = new PostType();
        blogType.setTitle(blog.getTitle());
        blogType.setContent(blog.getContent());
        return blogType;
    }
}
