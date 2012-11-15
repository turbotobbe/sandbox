package se.sandbox.restdemo.generic;

import java.io.Serializable;

public class Link implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String label;

    private String href;
    
    private String rel;
    
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

}
