package se.sandbox.restdemo.providers;

import java.util.List;

import se.sandbox.restdemo.generic.Field;
import se.sandbox.restdemo.generic.Form;
import se.sandbox.restdemo.generic.Item;
import se.sandbox.restdemo.generic.Items;
import se.sandbox.restdemo.generic.Link;
import se.sandbox.restdemo.generic.Msg;
import se.sandbox.restdemo.generic.Prop;

public class JsonStringMarshaller implements StringMarshaller
{
    private String pair(String key, String value)
    {
        return String.format("%s: %s", quote(key), quote(value));
    }

    private String list(String key, String list)
    {
        return String.format("%s: [%s]", quote(key), list);
    }

    private String quote(String str)
    {
        return "\"" + str + "\"";
    }

    private String marshallFields(List<Field> fields)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fields.size(); i++)
        {
            sb.append(marshall(fields.get(i)));
            if (i < fields.size()-1) sb.append(",");
        }
        return sb.toString();
    }

    public String marshall(Field field)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(pair("label", field.getLabel())).append(",");
        sb.append(pair("id", field.getId())).append(",");
        sb.append(pair("type", field.getType())).append(",");
        sb.append(pair("value", field.getValue()));
        if (!field.getMsgs().isEmpty()) {
            sb.append(",").append(list("msgs", marshallMsgs(field.getMsgs())));
        }
        sb.append("}");
        return sb.toString();
    }

    public String marshall(Form form)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(pair("label", form.getLabel())).append(",");
        sb.append(pair("href", form.getHref())).append(",");
        sb.append(pair("rel", form.getRel())).append(",");
        sb.append(pair("verb", form.getVerb())).append(",");
        sb.append(list("fields", marshallFields(form.getFields())));
        if (!form.getMsgs().isEmpty()) {
            sb.append(",").append(list("msgs", marshallMsgs(form.getMsgs())));
        }
        sb.append("}");
        return sb.toString();
    }

    public String marshall(Items items)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(list("items", marshallItems(items.getItems())));
        if (!items.getLinks().isEmpty()) {
            sb.append(",").append(list("links", marshallLinks(items.getLinks())));
        }
        sb.append("}");
        return sb.toString();
    }

    private String marshallItems(List<Item> items)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++)
        {
            sb.append(marshall(items.get(i)));
            if (i < items.size()-1) sb.append(",");
        }
        return sb.toString();
    }

    public String marshall(Item item)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(list("props", marshallProps(item.getProps())));
        if (!item.getLinks().isEmpty()) {
            sb.append(",").append(list("links", marshallLinks(item.getLinks())));
        }
        sb.append("}");
        return sb.toString();
    }

    private String marshallLinks(List<Link> links)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < links.size(); i++)
        {
            sb.append(marshall(links.get(i)));
            if (i < links.size()-1) sb.append(",");
        }
        return sb.toString();
    }

    public String marshall(Link link)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(pair("label", link.getLabel())).append(",");
        sb.append(pair("href", link.getHref())).append(",");
        sb.append(pair("rel", link.getRel()));
        sb.append("}");
        return sb.toString();
    }

    private String marshallMsgs(List<Msg> msgs)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msgs.size(); i++)
        {
            sb.append(marshall(msgs.get(i)));
            if (i < msgs.size()-1) sb.append(",");
        }
        return sb.toString();
    }

    public String marshall(Msg msg)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(pair("level", msg.getLevel())).append(",");
        sb.append(pair("label", msg.getLabel())).append(",");
        sb.append(pair("value", msg.getValue()));
        sb.append("}");
        return sb.toString();
    }
    
    private String marshallProps(List<Prop> props)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < props.size(); i++)
        {
            sb.append(marshall(props.get(i)));
            if (i < props.size()-1) sb.append(",");
        }
        return sb.toString();
    }

    public String marshall(Prop prop)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(pair("label", prop.getLabel())).append(",");
        sb.append(pair("id", prop.getId())).append(",");
        sb.append(pair("type", prop.getType())).append(",");
        sb.append(pair("value", prop.getValue()));
        sb.append("}");
        return sb.toString();
    }

}
