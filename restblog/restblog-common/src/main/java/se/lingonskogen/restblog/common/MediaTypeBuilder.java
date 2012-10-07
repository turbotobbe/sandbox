package se.lingonskogen.restblog.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

public class MediaTypeBuilder
{
    public static List<MediaType> buildAll(String mediaTypes)
    {
        List<MediaType> mts = new ArrayList<MediaType>();
        String[] split = mediaTypes.split(",");
        for (String mediaType : split)
        {
            mts.add(build(mediaType));
        }
        return mts;
    }
    
    public static MediaType build(String mediaType)
    {
        System.out.println("MT: " + mediaType);
        String[] split = mediaType.split("/");
        if (split.length != 2)
        {
            throw new RuntimeException("Invalid syntax");
        }
        String type = split[0].trim();
        String[] split2 = split[1].split(";");
        String subtype = split2[0];
        Map<String, String> parameters = new HashMap<String, String>();
        for (int i = 1; i < split2.length; i++)
        {
            String[] split3 = split2[i].split("=");
            if (split3.length != 2)
            {
                throw new RuntimeException("Invalid syntax");
            }
            parameters.put(split3[0].trim(), split3[1].trim());
        }
        MediaType mt = new MediaType(type, subtype, parameters);
        return mt;
    }
}
