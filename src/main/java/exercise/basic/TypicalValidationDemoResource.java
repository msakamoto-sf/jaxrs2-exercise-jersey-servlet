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

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * 
 * @author FengJing
 */
@Path("/ex1/validation-demo")
public class TypicalValidationDemoResource {

    /**
     * @param intnum
     * @param name2to5
     * @return
     */
    @GET
    @Path("demo1")
    @Produces("text/plain")
    public String demo1(
            @NotNull @Max(20) @Min(10) @QueryParam("intnum") int intnum,
            @NotNull @Size(min = 2, max = 5) @QueryParam("name2to5") String name2to5) {
        System.out.println("intnum=[" + intnum + "]");
        System.out.println("name2to5=[" + name2to5 + "]");
        return "demo1";
    }

}
