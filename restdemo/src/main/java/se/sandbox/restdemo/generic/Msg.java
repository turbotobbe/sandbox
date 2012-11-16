package se.sandbox.restdemo.generic;

import java.io.Serializable;

public class Msg implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String level;
    
    private String label;
    
    private String value;

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
    
}
