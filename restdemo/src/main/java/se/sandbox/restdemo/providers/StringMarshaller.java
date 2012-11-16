package se.sandbox.restdemo.providers;

import se.sandbox.restdemo.generic.Field;
import se.sandbox.restdemo.generic.Form;
import se.sandbox.restdemo.generic.Item;
import se.sandbox.restdemo.generic.Items;
import se.sandbox.restdemo.generic.Link;
import se.sandbox.restdemo.generic.Msg;
import se.sandbox.restdemo.generic.Prop;

public interface StringMarshaller
{
    String marshall(Field field);
    
    String marshall(Form form);

    String marshall(Items items);
    
    String marshall(Item item);

    String marshall(Msg msg);
    
    String marshall(Link link);
    
    String marshall(Prop prop);
}
