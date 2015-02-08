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

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.glassfish.hk2.api.Factory;

public class UserContextFactory implements Factory<UserContext> {

    final HttpServletRequest request;

    @Inject
    public UserContextFactory(HttpServletRequest request) {
        System.out.println("UserContextFactory constructor called.");
        this.request = request;
    }

    @Override
    public UserContext provide() {
        System.out.println("UserContextFactory#provide()");
        UserContext userContext = (UserContext) request.getAttribute("currentUser");
        return userContext;
    }

    @Override
    public void dispose(UserContext userContext) {
        System.out.println("UserContextFactory#dispose() // nothing to do");
    }
}
