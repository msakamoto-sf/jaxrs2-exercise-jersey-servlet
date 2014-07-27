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
package exercise.filter;

import java.util.Map;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

import exercise.filter.namebindannotations.AlwaysThrowException;
import exercise.filter.namebindannotations.AlwaysUnauthorized;
import exercise.filter.namebindannotations.NameBoundFilterA;
import exercise.filter.namebindannotations.NameBoundFilterB;

@Path("/ex1/lifecycle-demo")
public class ResourceDemo {

    @Context
    Application resourceDemoApp;

    String uuid;

    /**
     * from jsr339-jaxrs-2.0-final-spce:
     * 
     * "3.1.1 Lifecycle and Environment"
     * 
     * "Bydefault a new resource class instance is created for each request to that resource."
     */
    public ResourceDemo() {
        this.uuid = UUID.randomUUID().toString();
        System.out.println("Resource initialized, uuid=" + this.uuid);
    }

    String dumpProp() {
        String r = "";
        // this must be empty if non servlet configuration.
        // filters properties are not retrieved by Application#getProperties().
        Map<String, Object> prop = resourceDemoApp.getProperties();
        for (Map.Entry<String, Object> e : prop.entrySet()) {
            String k = e.getKey();
            String v = e.getValue().toString();
            String out = "dumpProp[" + k + "]=[" + v + "]";
            System.out.println(out);
            r += out + "\r\n";
        }
        return r;
    }

    /**
     * @return
     */
    @GET
    @Path("sub1")
    @Produces("text/plain")
    @NameBoundFilterA
    public String forSub1() {
        return dumpProp() + "sub1, uuid=" + this.uuid + "\r\n";
    }

    /**
     * @return
     */
    @GET
    @Path("sub2")
    @Produces("text/plain")
    @NameBoundFilterB
    public String forSub2() {
        return dumpProp() + "sub2, uuid=" + this.uuid + "\r\n";
    }

    /**
     * @return
     */
    @GET
    @Path("sub3")
    @Produces("text/plain")
    public String forSub3() {
        return dumpProp() + "sub3, uuid=" + this.uuid + "\r\n";
    }

    /**
     * @return
     */
    @GET
    @Path("sub4")
    @Produces("text/plain")
    @NameBoundFilterA
    @AlwaysUnauthorized
    @NameBoundFilterB
    public String forSub4() {
        // THIS INVOKES RESPONSE FILTER ALSO. (FilterDemoA -> FilterDemoB -> NameBoundFilterDemoB ->
        // NameBoundFilterDemoA)
        return dumpProp() + "sub4, uuid=" + this.uuid + "\r\n";
    }

    /**
     * @return
     */
    @GET
    @Path("sub5")
    @Produces("text/plain")
    @NameBoundFilterA
    @AlwaysThrowException
    @NameBoundFilterB
    public String forSub5() {
        // stops at AlwaysThrowExceptionFilter. response filters are NOT called.
        return dumpProp() + "sub5, uuid=" + this.uuid + "\r\n";
    }
}
