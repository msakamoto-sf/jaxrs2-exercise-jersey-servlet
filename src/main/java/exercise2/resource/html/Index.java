/*
 * Copyright 2015 "Masahiko Sakamoto" <sakamoto.gsyc.3s@gmail.com>
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
package exercise2.resource.html;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import exercise2.UserContext;

@Path("/")
public class Index {

    final int lifecycle_indicator;

    public Index() {
        lifecycle_indicator = new Random().nextInt(100);
        System.out.println("Resource class constructor, Index, lifecycle_indicator = " + lifecycle_indicator);
    }

    @GET
    @Produces("text/html")
    public String index() throws Exception {

        // TODO
        final String root_url = "/jersey-ex/";
        final String static_web_path = "/jersey-ex/static/"; // TODO rewrite to ServletContext.getContextPath()
        final UserContext userContext = new UserContext();
        userContext.setAuthenticated(true);
        userContext.setId(1000);
        userContext.setDisplayName("user1");

        System.out.println("Resource class Index#index(), lifecycle_indicator = " + lifecycle_indicator);
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("exercise2/mustache-tmpl/layout-default.html");
        Map<String, Object> layoutScope = new HashMap<>();
        layoutScope.put("rooturl", root_url);
        layoutScope.put("title", "Jersey Exercise Demo Application(2)");
        layoutScope.put("user", userContext);
        layoutScope.put("body", "Body Contents Block");
        layoutScope.put("staticroot", static_web_path);
        StringWriter sw = new StringWriter();
        mustache.execute(sw, layoutScope).flush();
        return sw.toString();
    }
}
