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
package exercise.exceptionmapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @see https://jersey.java.net/documentation/latest/representations.html
 * @see http://vividcode.hatenablog.com/entry/java/jax-rs/not-found-customize
 * @see http://stackoverflow.com/questions/583973/jax-rs-jersey-how-to-customize-error-handling
 */
@Path("/ex1/exception-demo")
public class ExceptionDemoResource {

    /**
     * @return
     */
    @GET
    @Path("demo1")
    @Produces("text/plain")
    public String demo1() {
        boolean t = true;
        if (t) {
            throw new CustomNotFoundException();
        }
        return "not reach here.";
    }

    /**
     * @return
     */
    @GET
    @Path("demo2")
    @Produces("text/plain")
    public String demo2() throws CustomException {
        boolean t = true;
        if (t) {
            throw new CustomException("customized matcher");
        }
        return "not reach here.";
    }

    /**
     * @return
     */
    @GET
    @Path("demo3")
    @Produces("text/plain")
    public String demo3() throws CustomException2 {
        boolean t = true;
        if (t) {
            throw new CustomException2(
                    "results runtime container's exception handler");
        }
        return "not reach here.";
    }
}
