package se.sandbox.restdemo.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Form implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String label;

    private String href;
    
    private String rel;
    
    private String verb;
    
    private final List<Field> fields = new ArrayList<Field>();

    private final List<Msg> msgs = new ArrayList<Msg>();
    
    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public String getRel()
    {
        return rel;
    }

    public void setRel(String rel)
    {
        this.rel = rel;
    }

    public String getVerb()
    {
        return verb;
    }

    public void setVerb(String verb)
    {
        this.verb = verb;
    }

    public List<Field> getFields()
    {
        return fields;
    }

    public List<Msg> getMsgs()
    {
        return msgs;
    }
    
}
