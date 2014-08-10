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

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/ex1/list-parameter-demo")
public class ListParametersDemoResource {

    @GET
    @Produces("text/plain")
    public String get(@QueryParam("list1") List<String> list1,
            @QueryParam("list2[]") List<Integer> list2,
            @QueryParam("list3") List<String> list3,
            @QueryParam("list4[]") List<Integer> list4) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list1.size(); i++) {
            String s = list1.get(i);
            sb.append("list1[" + i + "]=" + s + "\r\n");
        }
        for (int i = 0; i < list2.size(); i++) {
            int s = list2.get(i);
            sb.append("list2[" + i + "]=" + s + "\r\n");
        }
        for (int i = 0; i < list3.size(); i++) {
            String s = list3.get(i);
            sb.append("list3[" + i + "]=" + s + "\r\n");
        }
        for (int i = 0; i < list4.size(); i++) {
            int s = list4.get(i);
            sb.append("list4[" + i + "]=" + s + "\r\n");
        }
        return sb.toString();
    }

    @POST
    @Produces("text/plain")
    public String post(@QueryParam("list1") List<String> list1,
            @QueryParam("list2[]") List<Integer> list2,
            @FormParam("list3") List<String> list3,
            @FormParam("list4[]") List<Integer> list4) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list1.size(); i++) {
            String s = list1.get(i);
            sb.append("list1[" + i + "]=" + s + "\r\n");
        }
        for (int i = 0; i < list2.size(); i++) {
            int s = list2.get(i);
            sb.append("list2[" + i + "]=" + s + "\r\n");
        }
        for (int i = 0; i < list3.size(); i++) {
            String s = list3.get(i);
            sb.append("list3[" + i + "]=" + s + "\r\n");
        }
        for (int i = 0; i < list4.size(); i++) {
            int s = list4.get(i);
            sb.append("list4[" + i + "]=" + s + "\r\n");
        }
        return sb.toString();
    }
}
