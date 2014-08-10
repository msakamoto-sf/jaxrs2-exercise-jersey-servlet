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
package exercise.jackson;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author "Masahiko Sakamoto" <sakamoto.gsyc.3s@gmail.com>
 * @see http://d.hatena.ne.jp/tanakakns/20130918/1379513591
 * 
 * Why "jackson()" jsonobj is LinkedHashMap, not Map ?
 * Why "jackson()" return type is LinkedHashMap, not Map ?
 * ... Jackson ObjectMapper#readValue(... Map<String, Object>) finally generates LinkedHashMap.
 * Ideally, we only use abstract type.
 * BUT in REAL, Jackson generates LinkedHashMap.
 * So we need to use LinkedHashMap for type consistency. 
 */
@Path("/ex1/jackson-demo")
public class JacksonDemoResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedHashMap<String, Object> jackson(
            @QueryParam("query1") String query1,
            @QueryParam("query2") String query2,
            LinkedHashMap<String, Object> jsonobj) {
        Map<String, String> queries = new LinkedHashMap<>();
        queries.put("query1", query1);
        queries.put("query2", query2);
        jsonobj.put("queries", queries);
        return jsonobj;
    }
}
