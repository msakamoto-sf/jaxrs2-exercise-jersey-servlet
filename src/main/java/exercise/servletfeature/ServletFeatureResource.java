/*
 * Copyright 2014 "Masahiko Sakamoto" <sakamoto.gsyc.3s@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package exercise.servletfeature;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/ex1/servlet-feature-demo")
public class ServletFeatureResource {

    @Context
    ServletContext servletContext;

    @Context
    ServletConfig servletConfig;

    @GET
    @Path("demo1")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getServletContextAndConfigs()
            throws JsonProcessingException {
        Map<String, String> dump = new HashMap<>();

        dump.put("servlet-name", servletConfig.getServletName());
        /*
         * Enumeration to for: http://d.hatena.ne.jp/chiheisen/20110410/1302447986
         * http://stackoverflow.com/questions/7160568
         * /iterating-through-enumeration-of-hastable-keys-throws-nosuchelementexception-err
         */
        for (String pname : Collections.list(servletConfig
                .getInitParameterNames())) {
            String k = "servlet-init-param-" + pname;
            dump.put(k, servletConfig.getInitParameter(pname));
        }

        dump.put("server-info", servletContext.getServerInfo());
        StringBuilder sb = new StringBuilder();
        sb.append(servletContext.getMajorVersion());
        sb.append(".");
        sb.append(servletContext.getMinorVersion());
        dump.put("version", sb.toString());
        dump.put("context-path", servletContext.getContextPath());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonout = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(dump);
        return Response.status(Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8"))
                .entity(jsonout).encoding("UTF-8").build();
    }
}
