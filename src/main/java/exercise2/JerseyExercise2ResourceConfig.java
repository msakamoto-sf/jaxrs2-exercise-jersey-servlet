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

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyExercise2ResourceConfig extends ResourceConfig {

    public JerseyExercise2ResourceConfig() {
        System.out.println("JerseyExercise2Application constructor.");
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindFactory(UserContextFactory.class).to(UserContext.class);
            }
        });
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindFactory(ApplicationWorldFactory.class).to(ApplicationWorld.class);
            }
        });
    }
}
