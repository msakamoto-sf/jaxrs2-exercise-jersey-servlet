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

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/ex1/typical-request-parameters-demo/item/{id}")
public class RequestParametersDemoResource {

    String dumpUriInfo(UriInfo info) {
        String abs_path = info.getAbsolutePath().toString();
        String abs_base = info.getBaseUri().toString();
        String req_uri = info.getRequestUri().toString();
        return "UriInfo : [\r\nAbsolutePath:'" + abs_path + "',\r\nBaseUri:'"
                + abs_base + "',\r\nRequestUri:'" + req_uri + "'\r\n]\r\n";
    }

    /**
     * @param uriInfo
     * @param id
     * @param age
     * @param isAdmin
     * @param name
     * @return
     */
    @GET
    @Produces("text/plain")
    public String getItem(@Context UriInfo uriInfo, @PathParam("id") String id,
            @DefaultValue("100") @QueryParam("age") int age,
            @DefaultValue("false") @QueryParam("is_admin") boolean isAdmin,
            @DefaultValue("bob") @QueryParam("name") String name) {
        String r = "getItem() : id=[" + id + "]\r\n";
        r += "age=[" + age + "]\r\n";
        r += "is_admin=[" + isAdmin + "]\r\n";
        r += "name=[" + name + "]\r\n";
        return r + dumpUriInfo(uriInfo);
    }

    /**
     * @param id
     * @param uriInfo
     * @return
     */
    @GET
    @Path("admin")
    @Produces("text/plain")
    public String getItemAdmin(@PathParam("id") String id,
            @Context UriInfo uriInfo) {
        return "getItemAdmin() : id=[" + id + "]\r\n" + dumpUriInfo(uriInfo);
    }

    /**
     * @param uriInfo
     * @param id
     * @param newName
     * @param newAge
     * @param newAdmin
     * @return
     */
    @POST
    @Produces("text/plain")
    public String updateItem(@Context UriInfo uriInfo,
            @PathParam("id") String id,
            @DefaultValue("jon") @FormParam("name") String newName,
            @DefaultValue("10") @FormParam("age") int newAge,
            @DefaultValue("false") @FormParam("is_admin") boolean newAdmin) {
        String r = "updateItem() : id=[" + id + "]\r\n";
        r += "age=[" + newAge + "]\r\n";
        r += "is_admin=[" + newAdmin + "]\r\n";
        r += "name=[" + newName + "]\r\n";
        return r + dumpUriInfo(uriInfo);
    }
}
