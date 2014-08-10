package exercise.jackson;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonAboutMapWriter implements
        MessageBodyWriter<LinkedHashMap<String, Object>> {

    /**
     * about ObjectMapper thread-safety:
     * 
     * @see http://wiki.fasterxml.com/JacksonFAQ#Data_Binding.2C_general
     * @see http://wiki.fasterxml.com/JacksonFAQThreadSafety
     */
    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public long getSize(LinkedHashMap<String, Object> bean, Class<?> type,
            Type genericType, Annotation[] annotations, MediaType mediaType) {
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return type == LinkedHashMap.class;
    }

    @Override
    public void writeTo(LinkedHashMap<String, Object> bean, Class<?> type,
            Type genericType, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) throws IOException,
            WebApplicationException {
        String json = mapper.writeValueAsString(bean);
        try (PrintWriter out = new PrintWriter(entityStream)) {
            out.print(json);
        } catch (Exception e) {
            throw e;
        }
    }

}
