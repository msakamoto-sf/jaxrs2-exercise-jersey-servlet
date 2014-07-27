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

import java.io.IOException;
import java.util.UUID;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import exercise.filter.namebindannotations.AlwaysThrowException;

@Provider
@AlwaysThrowException
public class AlwaysThrowExceptionFilter implements ContainerRequestFilter {

    String uuid;

    public AlwaysThrowExceptionFilter() {
        this.uuid = UUID.randomUUID().toString();
        System.out.println("AlwaysThrowExceptionFilter initialized, uuid="
                + this.uuid);
    }

    @Override
    public void filter(ContainerRequestContext requestContext)
            throws IOException {
        System.out.println("AlwaysThrowExceptionFilter, request filter, uuid="
                + this.uuid);
        throw new IOException("dummy io exception");
    }

}
