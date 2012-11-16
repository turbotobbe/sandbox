package se.sandbox.restdemo;

import javax.ws.rs.core.MediaType;

import se.sandbox.restdemo.generic.Field;
import se.sandbox.restdemo.generic.Form;
import se.sandbox.restdemo.generic.Link;
import se.sandbox.restdemo.generic.Msg;
import se.sandbox.restdemo.generic.Prop;

public class Builder
{
    private final String resourcePath;

    public Builder()
    {
        this.resourcePath = "http://localhost:8080/restdemo/rest";
    }

    public Field buildField(String label)
    {
        Field field = new Field();
        field.setLabel(label);
        field.setId(label.toLowerCase());
        field.setType("string");
        field.setValue("<default>");
        return field;
    }

    public Form buildForm(String label, String href, String verb)
    {
        Form form = new Form();
        form.setLabel(label);
        form.setHref(buildUri(resourcePath, href));
        form.setRel(MediaType.APPLICATION_JSON);
        form.setVerb(verb);
        return form;
    }

    public Link buildLink(String label, String href)
    {
        Link link = new Link();
        link.setLabel(label);
        link.setHref(buildUri(resourcePath, href));
        link.setRel(MediaType.APPLICATION_JSON);
        return link;
    }

    public Msg buildMsg(String level, String label, String value)
    {
        Msg msg = new Msg();
        msg.setLevel(level);
        msg.setLabel(label);
        msg.setValue(value);
        return msg;
    }

    public Prop buildProp(String label)
    {
        Prop prop = new Prop();
        prop.setLabel(label);
        prop.setId(label.toLowerCase());
        prop.setType("string");
        prop.setValue("dummy");
        return prop;
    }

    private String buildUri(String pre, String post)
    {
        if (pre.endsWith("/") && post.startsWith("/")) {
            post = post.substring(1);
        }
        return pre + post;
    }

}
