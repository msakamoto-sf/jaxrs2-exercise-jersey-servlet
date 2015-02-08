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
package exercise2;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;

public class ApplicationWorld {
    public final static String CONTEXT_KEY = "application.world";
    final String contextPath;
    final Properties config;

    public ApplicationWorld(ServletContext servletContext) throws IOException {
        this.contextPath = servletContext.getContextPath();
        this.config = new Properties();
        this.config.load(servletContext.getResourceAsStream("/WEB-INF/config.properties"));
    }

    public static ApplicationWorld get(ServletContext servletContext) {
        return (ApplicationWorld) servletContext.getAttribute(CONTEXT_KEY);
    }

    public String getContextPath() {
        return this.contextPath;
    }

    public String getConfig(String key) {
        return this.config.getProperty(key);
    }
}
