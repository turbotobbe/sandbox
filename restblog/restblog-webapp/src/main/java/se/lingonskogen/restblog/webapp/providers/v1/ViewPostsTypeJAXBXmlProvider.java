package se.lingonskogen.restblog.webapp.providers.v1;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import se.lingonskogen.restblog.domain.PostType;
import se.lingonskogen.restblog.domain.PostsType;
import se.lingonskogen.restblog.mediatypes.v1.Post;
import se.lingonskogen.restblog.mediatypes.v1.ViewPost;
import se.lingonskogen.restblog.mediatypes.v1.ViewPosts;

@Provider
public class ViewPostsTypeJAXBXmlProvider extends JAXBXmlProvider<PostsType, ViewPosts>
{
    private LinkBuilder linkBuilder = new LinkBuilder();
    
    public ViewPostsTypeJAXBXmlProvider(@Context HttpHeaders headers)
    {
        super(headers, PostsType.class, ViewPosts.class);
    }

    @Override
    protected ViewPosts toMediaType(PostsType t)
    {
        ViewPosts viewPosts = new ViewPosts();
        for (PostType blogType : t.getPostType())
        {
            Post post = new Post();
            post.setTitle(blogType.getTitle());
            post.setContent(blogType.getContent());
            ViewPost viewPost = new ViewPost();
            viewPost.setPost(post);
            // add link with blog key in path to
            viewPost.getLink().add(linkBuilder.buildGet("Self", ViewPost.class, blogType.getTitle()));
            viewPosts.getViewPost().add(viewPost);
        }
        return viewPosts;
    }

    @Override
    protected PostsType fromMediaType(ViewPosts s)
    {
        PostsType postsType = new PostsType();
        for (ViewPost viewPost : s.getViewPost())
        {
            Post post = viewPost.getPost();
            PostType blogType = new PostType();
            blogType.setTitle(post.getTitle());
            blogType.setContent(post.getContent());
            postsType.getPostType().add(blogType);
        }
        return postsType;
    }
}
