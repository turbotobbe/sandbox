package se.sandbox.restdemo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.sandbox.restdemo.generic.Form;
import se.sandbox.restdemo.generic.Item;
import se.sandbox.restdemo.generic.Items;
import se.sandbox.restdemo.providers.JsonStringMarshaller;
import se.sandbox.restdemo.providers.StringMarshaller;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class RestResource
{

    @GET
    public Response getRoot() {
        Builder builder = new Builder();
        StringMarshaller marshaller = new JsonStringMarshaller();

        Items items = new Items();
        items.getLinks().add(builder.buildLink("List Foos", "/foos"));
        items.getLinks().add(builder.buildLink("List Bars", "/bars"));
        
        String content = marshaller.marshall(items);
        return Response.ok(content).build();
    }
    
    @GET
    @Path("foos")
    public Response getListFoos() {
        
        Builder builder = new Builder();
        StringMarshaller marshaller = new JsonStringMarshaller();

        Items items = new Items();
        for (int i = 0; i < 3; i++)
        {
            Item item = new Item();
            for (int j = 0; j < 1; j++)
            {
                item.getProps().add(builder.buildProp("Prop-" + j));
            }
            item.getLinks().add(builder.buildLink("View", "/foos/" + i));
            items.getItems().add(item);
        }
        items.getLinks().add(builder.buildLink("Create Foo", "/create/foo"));
        
        String content = marshaller.marshall(items);
        return Response.ok(content).build();
    }

    @GET
    @Path("foos/{index}")
    public Response getViewFoo(@PathParam("index") String index) {
        
        Builder builder = new Builder();
        StringMarshaller marshaller = new JsonStringMarshaller();

        Item item = new Item();
        for (int j = 0; j < 3; j++)
        {
            item.getProps().add(builder.buildProp("Prop-" + j));
        }
        item.getLinks().add(builder.buildLink("View", "/foos/" + index));
        item.getLinks().add(builder.buildLink("Update", "/update/foo/" + index));
        item.getLinks().add(builder.buildLink("Delete", "/delete/foo/" + index));
        
        String content = marshaller.marshall(item);
        return Response.ok(content).build();
    }

    @GET
    @Path("create/foo")
    public Response getCreateFoo() {
        
        Builder builder = new Builder();
        StringMarshaller marshaller = new JsonStringMarshaller();

        Form form = builder.buildForm("Create Foo", "/create/foo", "POST");
        for (int i = 0; i < 3; i++)
        {
            form.getFields().add(builder.buildField("Prop-" + i));
        }
        
        String content = marshaller.marshall(form);
        return Response.ok(content).build();
    }

    @GET
    @Path("update/foo/{index}")
    public Response getUpdateFoo(@PathParam("index") String index) {
        
        Builder builder = new Builder();
        StringMarshaller marshaller = new JsonStringMarshaller();

        Form form = builder.buildForm("Update Foo", "/update/foo/" + index, "PUT");
        for (int i = 0; i < 3; i++)
        {
            form.getFields().add(builder.buildField("Prop-" + i));
        }
        
        String content = marshaller.marshall(form);
        return Response.ok(content).build();
    }

    @GET
    @Path("delete/foo/{index}")
    public Response getDeleteFoo(@PathParam("index") String index) {
        
        Builder builder = new Builder();
        StringMarshaller marshaller = new JsonStringMarshaller();

        Form form = builder.buildForm("Update Foo", "/update/foo/" + index, "DELETE");
        for (int i = 0; i < 3; i++)
        {
            form.getFields().add(builder.buildField("Prop-" + i));
        }
        form.getMsgs().add(builder.buildMsg("WARNING", "Delete!", "Are your sure?"));
        String content = marshaller.marshall(form);
        return Response.ok(content).build();
    }
}
