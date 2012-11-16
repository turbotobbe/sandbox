package se.sandbox.restdemo.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Items implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final List<Item> items = new ArrayList<Item>();
    
    private final List<Link> links = new ArrayList<Link>();

    public List<Item> getItems()
    {
        return items;
    }

    public List<Link> getLinks()
    {
        return links;
    }
    
    
}
