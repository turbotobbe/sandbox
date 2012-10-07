package se.lingonskogen.restblog.webapp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import se.lingonskogen.restblog.domain.PostType;
import se.lingonskogen.restblog.domain.PostsType;

@Path("/posts")
public class PostResource
{
    
    @GET
    @Produces({
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_VIEW_POSTS_XML_VERSION,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_VIEW_POSTS_XML,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_XML_VERSION,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_XML
        })
    public PostsType getPostsType()
    {
        PostsType postsType = new PostsType();
        postsType.getPostType().add(buildPostType(2));
        postsType.getPostType().add(buildPostType(3));
        return postsType;
    }
    
    @GET
    @Path("/{bid}")
    @Produces({
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_VIEW_POSTS_XML_VERSION,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_VIEW_POSTS_XML,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_XML_VERSION,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_XML
        })
    public PostsType getPostsTypeByBlog(@PathParam("bid") String bid)
    {
        PostsType postsType = new PostsType();
        postsType.getPostType().add(buildPostType(4));
        postsType.getPostType().add(buildPostType(5));
        return postsType;
    }

    @GET
    @Path("/{bid}/{pid}")
    @Produces({
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_VIEW_POST_XML_VERSION,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_VIEW_POST_XML,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_XML_VERSION,
        se.lingonskogen.restblog.mediatypes.v1.MediaTypes.APP_XML
        })
    public PostType getPostType(@PathParam("bid") String bid, @PathParam("pid") String pid)
    {
        return buildPostType(1);
    }

    private PostType buildPostType(int i)
    {
        PostType postType = new PostType();
        postType.setTitle("my-post-" + i);
        postType.setContent("my-post-" + i + "-content");
        return postType;
    }

}
