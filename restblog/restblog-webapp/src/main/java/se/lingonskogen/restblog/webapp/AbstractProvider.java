package se.lingonskogen.restblog.webapp;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import se.lingonskogen.restblog.common.MediaTypeBuilder;
import se.lingonskogen.restblog.mediatypes.v1.MediaTypes;

public abstract class AbstractProvider<T> implements MessageBodyWriter<T>, MessageBodyReader<T>
{
    private final HttpHeaders headers;
    
    private final Class<? extends T> cls;
    
    private final MediaType generic;
    
    private final MediaType specific;
    
    private final boolean legacy;

    protected AbstractProvider(@Context HttpHeaders headers, Class<? extends T> cls, MediaType generic, MediaType specific)
    {
        this.headers = headers;
        this.cls = cls;
        this.generic = generic;
        this.specific = specific;
        String version = this.specific.getParameters().get("version");
        this.legacy = !MediaTypes.VERSION.equals(version);
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations,
            MediaType mediaType)
    {
        return canHandle(type, mediaType);
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations,
            MediaType mediaType)
    {
        return canHandle(type, mediaType);
    }

    @Override
    public long getSize(T t, Class<?> type, Type genericType, Annotation[] annotations,
            MediaType mediaType)
    {
        return -1;
    }

    private boolean canHandle(Class<?> type, MediaType mediaType)
    {
        System.out.println("mediatype: " + mediaType.toString());
        if (!cls.equals(type))
        {
            System.out.println("mismatch class");
            return false;
        }
        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        for (String accepts : headers.getRequestHeader("Accept"))
        {
            mediaTypes.addAll(MediaTypeBuilder.buildAll(accepts));
        }
        if (specific.isCompatible(mediaType) && matchVersion(specific, mediaTypes, legacy))
        {
            return true;
        }
        if (generic.isCompatible(mediaType) && matchVersion(generic, mediaTypes, legacy))
        {
            return true;
        }
        return false;
    }
    
    private boolean matchVersion(MediaType mediaType, List<MediaType> mediaTypes, boolean legacy)
    {
        String version = mediaType.getParameters().get("version");
        for (MediaType mt : mediaTypes)
        {
            if (mediaType.isCompatible(mt))
            {
                String v = mt.getParameters().get("version");
                if (!legacy && v == null)
                {
                    return true;
                }
                if (version == null && v==null)
                {
                    return true;
                }
                else if (version != null && version.equals(v))
                {
                    return true;
                }
                else if (v != null && v.equals(version))
                {
                    return true;
                }
            }
        }
        return false;
    }

}
