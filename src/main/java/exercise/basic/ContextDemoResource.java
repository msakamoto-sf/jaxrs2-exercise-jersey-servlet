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
package exercise.basic;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

@Path("/ex1/context-demo/{pathparam1}/{pathparam1}/{pathparam2}/{pathparam2}/{pathparam3}")
public class ContextDemoResource {

    @Context
    Application myApplication;

    @Context
    HttpHeaders httpHeaders;

    String dumpUriInfo(UriInfo info) {
        String r = "UriInfo:[\r\n";
        String abs_path = info.getAbsolutePath().toString();
        String abs_base = info.getBaseUri().toString();
        String req_uri = info.getRequestUri().toString();
        r += "AbsolutePath:'" + abs_path + "'\r\n";
        r += "BaseUri:'" + abs_base + "'\r\n";
        r += "RequestUri:'" + req_uri + "'\r\n";
        r += "QueryParameters:[\r\n";
        MultivaluedMap<String, String> queryParams = info.getQueryParameters();
        for (Map.Entry<String, List<String>> e : queryParams.entrySet()) {
            r += "  " + e.getKey() + ":[\r\n";
            List<String> values = e.getValue();
            for (String v : values) {
                r += "    " + v + "\r\n";
            }
            r += "  ]\r\n";
        }
        r += "]\r\n";
        MultivaluedMap<String, String> pathParams = info.getPathParameters();
        r += "PathParameters:[\r\n";
        for (Map.Entry<String, List<String>> e : pathParams.entrySet()) {
            r += "  " + e.getKey() + ":[\r\n";
            List<String> values = e.getValue();
            for (String v : values) {
                r += "    " + v + "\r\n";
            }
            r += "  ]\r\n";
        }
        r += "]\r\n";
        List<PathSegment> pathSegments = info.getPathSegments();
        r += "PathSegments:[\r\n";
        for (PathSegment ps : pathSegments) {
            r += "  " + ps.getPath() + "\r\n";
        }
        r += "]\r\n";
        return r + "]\r\n";
    }

    /**
     * @param info
     * @return
     */
    @GET
    @Produces("text/plain")
    public String forResourceRoot(@Context UriInfo info) {
        String classes = "Application#getClasses = [\r\n";
        for (Class<?> c : myApplication.getClasses()) {
            classes += c.getName() + "\r\n";
        }
        classes += "]\r\n";

        String headers = "HttpHeaders = [\r\n";
        MultivaluedMap<String, String> mvheaders = httpHeaders
                .getRequestHeaders();
        for (Map.Entry<String, List<String>> header : mvheaders.entrySet()) {
            headers += "  " + header.getKey() + ":[\r\n";
            List<String> values = header.getValue();
            for (String v : values) {
                headers += "    " + v + "\r\n";
            }
            headers += "  ]\r\n";
        }
        headers += "]\r\n";

        String cookies = "HttpHeaders#getCookies() = [\r\n";
        for (Map.Entry<String, Cookie> c : httpHeaders.getCookies().entrySet()) {
            cookies += "  " + c.getKey() + "=" + c.getValue().toString()
                    + "\r\n";
        }
        cookies += "]\r\n";

        return classes + headers + cookies + dumpUriInfo(info);
    }
}
