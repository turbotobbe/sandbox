package se.lingonskogen.restblog.webapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Providers;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public abstract class AbstractJAXBProvider<T, S> extends AbstractProvider<T>
{
    @Context
    private Providers providers;

    private final Class<? extends S> clsS;
    
    protected AbstractJAXBProvider(HttpHeaders headers, Class<? extends T> clsT, Class<? extends S> clsS, MediaType generic, MediaType specific)
    {
        super(headers, clsT, generic, specific);
        this.clsS = clsS;
    }

    protected abstract S toMediaType(T t);

    protected abstract T fromMediaType(S s);
    
    @Override
    public void writeTo(T t, Class<?> type, Type genericType, Annotation[] annotations,
            MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) throws IOException, WebApplicationException
    {
        try {
            S s = toMediaType(t);
            ContextResolver<JAXBContext> resolver = providers.getContextResolver(JAXBContext.class, mediaType);
            JAXBContext context = resolver.getContext(clsS);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(s, entityStream);
        }
        catch (Exception e)
        {
            throw new WebApplicationException(e, Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public T readFrom(Class<T> type, Type genericType, Annotation[] annotations,
            MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream) throws IOException, WebApplicationException
    {
        try {
            ContextResolver<JAXBContext> resolver = providers.getContextResolver(JAXBContext.class, mediaType);
            JAXBContext context = resolver.getContext(clsS);
            Unmarshaller unmarshaller = context.createUnmarshaller();
//            unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            S s = (S) unmarshaller.unmarshal(entityStream);
            return fromMediaType(s);
        }
        catch (Exception e)
        {
            throw new WebApplicationException(e, Status.INTERNAL_SERVER_ERROR);
        }
    }

}
