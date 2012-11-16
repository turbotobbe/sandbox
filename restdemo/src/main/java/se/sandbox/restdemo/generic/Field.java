package se.sandbox.restdemo.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Field implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String label;
    
    private String id;
    
    private String type;
    
    private String value;

    private final List<Msg> msgs = new ArrayList<Msg>();
    
    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public List<Msg> getMsgs()
    {
        return msgs;
    }
    
}
