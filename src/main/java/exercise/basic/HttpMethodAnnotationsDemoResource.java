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

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/ex1/http-method-annotations-demo")
public class HttpMethodAnnotationsDemoResource {

    @GET
    @Produces("text/plain")
    public String forGet() {
        return "GET:" + this.getClass().getName() + "\r\n";
    }

    @POST
    @Produces("text/plain")
    public String forPost() {
        return "POST:" + this.getClass().getName() + "\r\n";
    }

    @PUT
    @Produces("text/plain")
    public String forPut() {
        return "PUT:" + this.getClass().getName() + "\r\n";
    }

    @DELETE
    @Produces("text/plain")
    public String forDelete() {
        return "DELETE:" + this.getClass().getName() + "\r\n";
    }
}
