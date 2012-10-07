package se.lingonskogen.restblog.client;

import javax.ws.rs.core.MediaType;

import se.lingonskogen.restblog.common.MediaTypeBuilder;
import se.lingonskogen.restblog.mediatypes.v2.Bar;
import se.lingonskogen.restblog.mediatypes.v2.Foo;
import se.lingonskogen.restblog.mediatypes.v2.MediaTypes;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.WebResource;

public class Main
{
    private static final MediaType mtFoo = MediaTypeBuilder.build(MediaTypes.APP_FOO_XML_VERSION);
    private static final MediaType mtBar = MediaTypeBuilder.build(MediaTypes.APP_BAR_XML_VERSION);

    public static void main(String[] args)
    {
        Client client = Client.create();
//        client.addFilter(new LoggingFilter());
        testRequest(client, mtFoo, "http://localhost:8080/restblog-webapp/rest/foo");
    }

    private static void testRequest(Client client, MediaType mediaType, String uri)
    {
        // uri should be base, omitted or from a link
        WebResource resource = client.resource(uri);
        // mediaType should be application/*.v1+xml for first request, or from a link
        ClientResponse response = resource.accept(mediaType).get(ClientResponse.class);
        int status = response.getStatus();
        if (status == Status.OK.getStatusCode())
        {
            MediaType mt = response.getType();
            if (mt.isCompatible(mtFoo))
            {
                Foo foo = response.getEntity(Foo.class);
                System.out.println(Foo.class.getName() + " => " + foo.getName() + "(" + foo.getAge() + ")");
            }
            if (mt.isCompatible(mtBar))
            {
                Bar bar = response.getEntity(Bar.class);
                System.out.println(Bar.class.getName() + " => " + bar.getName());
            }
        }
        else
        {
            System.out.println(status + " " + response.getType());
        }
    }
}
