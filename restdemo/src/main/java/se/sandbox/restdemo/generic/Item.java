package se.sandbox.restdemo.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final List<Prop> props = new ArrayList<Prop>();

    private final List<Link> links = new ArrayList<Link>();
    
    public List<Prop> getProps()
    {
        return props;
    }

    public List<Link> getLinks()
    {
        return links;
    }
    
}
