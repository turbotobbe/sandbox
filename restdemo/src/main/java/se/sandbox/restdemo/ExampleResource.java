package se.sandbox.restdemo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.sandbox.restdemo.generic.Field;
import se.sandbox.restdemo.generic.Form;
import se.sandbox.restdemo.generic.Item;
import se.sandbox.restdemo.generic.Items;
import se.sandbox.restdemo.generic.Link;
import se.sandbox.restdemo.generic.Msg;
import se.sandbox.restdemo.generic.Prop;
import se.sandbox.restdemo.providers.JsonStringMarshaller;
import se.sandbox.restdemo.providers.StringMarshaller;

@Path("ex")
public class ExampleResource
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("form")
    public Response getForm() {
        Form form = new Form();
        form.setLabel("My Form");
        form.setHref("/form");
        form.setRel(MediaType.APPLICATION_JSON);
        form.setVerb("POST");
        for (int i = 0; i < 4; i++)
        {
            Field field = new Field();
            field.setLabel("Label-" + i);
            field.setId("id-" + i);
            field.setType("string");
            field.setValue("<default>");
            form.getFields().add(field);
            Msg msg = new Msg();
            msg.setLevel("WARNING");
            msg.setLabel("Warning!");
            msg.setValue("Warning number " + i);
            field.getMsgs().add(msg);
        }
        for (int i = 0; i < 2; i++)
        {
            Msg msg = new Msg();
            msg.setLevel("ERROR!");
            msg.setLabel("Error");
            msg.setValue("Error number " + i);
            form.getMsgs().add(msg);
        }
        StringMarshaller marshaller = new JsonStringMarshaller();
        String str = marshaller.marshall(form);
        return Response.ok(str).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("items")
    public Response getItems() {
        Items items = new Items();
        for (int i = 0; i < 2; i++)
        {
            Item item = new Item();
            for (int j = 10; j < 12; j++)
            {
                Prop prop = new Prop();
                prop.setLabel("Prop-" + i);
                prop.setId("id-" + j);
                prop.setType("string");
                prop.setValue("value-" + j);
                item.getProps().add(prop);
                Link link = new Link();
                link.setLabel("Link-" + j);
                link.setHref("/link-" + j);
                link.setRel(MediaType.APPLICATION_JSON);
                item.getLinks().add(link);
            }
            items.getItems().add(item);
            Link link = new Link();
            link.setLabel("Link-" + i);
            link.setHref("/link-" + i);
            link.setRel(MediaType.APPLICATION_JSON);
            items.getLinks().add(link);
        }
        StringMarshaller marshaller = new JsonStringMarshaller();
        String str = marshaller.marshall(items);
        return Response.ok(str).build();
    }

}
