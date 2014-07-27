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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/ex1/resource-paths-demo")
public class PathDemoResource {

    String dumpUriInfo(UriInfo info) {
        String abs_path = info.getAbsolutePath().toString();
        String abs_base = info.getBaseUri().toString();
        String req_uri = info.getRequestUri().toString();
        return "UriInfo : [\r\nAbsolutePath:'" + abs_path + "',\r\nBaseUri:'"
                + abs_base + "',\r\nRequestUri:'" + req_uri + "'\r\n]\r\n";
    }

    /**
     * @param info
     * @return
     */
    @GET
    @Produces("text/plain")
    public String forResourceRoot(@Context UriInfo info) {
        return dumpUriInfo(info);
    }

    /**
     * @param info
     * @return
     */
    @GET
    @Path("sub1")
    public String forSub1a(@Context UriInfo info) {
        return "sub1a\r\n" + dumpUriInfo(info);
    }

    /**
     * [[FATAL] A resource model has ambiguous (sub-)resource method for HTTP method GET and input mime-types as defined
     * by
     * 
     * @Consumes and @Produces annotations at Java methods public java.lang.String
     *           net.glamenvseptzen.jersey.exercises.ex1.ResourcePathsDemo.forSub1a(javax.ws.rs.core.UriInfo) and public
     *           java.lang.String
     *           net.glamenvseptzen.jersey.exercises.ex1.ResourcePathsDemo.forSub1b(javax.ws.rs.core.UriInfo) at
     *           matching regular expression /sub1. These two methods produces and consumes exactly the same mime-types
     *           and therefore their invocation as a resource methods will always fail.;
     *           source='org.glassfish.jersey.server.model.RuntimeResource@3d1cda9']
     * @param info
     * @return
     */
    // @GET
    // @Path("sub1")
    // public String forSub1b(@Context UriInfo info) {
    // return "sub1b\r\n" + dumpUriInfo(info);
    // }

    @GET
    @Path("sub/sub2")
    public String forSubSub2(@Context UriInfo info) {
        return "subsub2\r\n" + dumpUriInfo(info);
    }

}
