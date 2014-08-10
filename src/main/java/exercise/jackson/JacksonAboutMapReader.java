package exercise.jackson;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class JacksonAboutMapReader implements
        MessageBodyReader<LinkedHashMap<String, Object>> {

    /**
     * about ObjectMapper thread-safety:
     * 
     * @see http://wiki.fasterxml.com/JacksonFAQ#Data_Binding.2C_general
     * @see http://wiki.fasterxml.com/JacksonFAQThreadSafety
     */
    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean isReadable(Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return type == LinkedHashMap.class;
    }

    @Override
    public LinkedHashMap<String, Object> readFrom(
            Class<LinkedHashMap<String, Object>> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
            throws IOException, WebApplicationException {
        try {
            LinkedHashMap<String, Object> result = mapper.readValue(
                    entityStream,
                    new TypeReference<LinkedHashMap<String, Object>>() {
                    });
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
