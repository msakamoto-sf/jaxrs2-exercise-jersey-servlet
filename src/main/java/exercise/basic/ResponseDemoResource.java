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

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.StreamingOutput;

import com.google.common.io.ByteSource;
import com.google.common.io.Resources;

@Path("/ex1/typical-response-demo")
public class ResponseDemoResource {

    /**
     * @return
     * @throws java.net.URISyntaxException
     */
    @GET
    @Path("created201")
    public Response created201() throws URISyntaxException {
        URI uri = new URI("http://www.example.com/foo/bar");
        return Response.created(uri).build();
    }

    /**
     * @return
     * @throws java.net.URISyntaxException
     */
    @GET
    @Path("seeother303")
    public Response seeother303() throws URISyntaxException {
        URI uri = new URI("http://www.example.com/foo/bar");
        return Response.seeOther(uri).build();
    }

    /**
     * @return
     * @throws java.net.URISyntaxException
     */
    @GET
    @Path("tempredirect307")
    public Response tempredirect307() throws URISyntaxException {
        URI uri = new URI("http://www.example.com/foo/bar");
        return Response.temporaryRedirect(uri).build();
    }

    /**
     * @return
     */
    @GET
    @Path("custom500")
    public Response custom500() {
        MediaType mt = MediaType.APPLICATION_OCTET_STREAM_TYPE;
        return Response.serverError().entity("custom 500 error")
                .type(mt.withCharset("Windows-31J")).build();
    }

    /**
     * @return
     */
    @GET
    @Path("custom999")
    public Response custom999() {
        MediaType mt = new MediaType("application", "binary", "UTF-8");
        return Response.status(999).entity("custom http status code").type(mt)
                .build();
    }

    /**
     * @return
     * @throws java.net.URISyntaxException
     */
    @GET
    @Path("binarydl")
    public Response binarydl() throws URISyntaxException {
        return Response
                .status(Status.OK)
                .entity(new byte[] { 0x0, 0x1, 0x2, 0x3 })
                .type("foo/bar")
                .header("Content-Disposition",
                        "attachment; filename=\"binary.bin\"")
                .header("X-Custom1", "value1; value2, value3")
                .header("X-Custom2", "")
                .location(new URI("http://www.example.com/foo/bar")).build();
    }

    /**
     * @return
     */
    @GET
    @Path("sampleimage.png")
    public Response anydata() {
        URL resurl = Resources.getResource("exercise/sampleimage.png");
        final ByteSource byteSource = Resources.asByteSource(resurl);
        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                byteSource.copyTo(os);
                os.flush();
            }
        };
        return Response.status(Status.OK).type("image/png").entity(stream)
                .build();
    }

    /**
     * @return
     * @throws java.net.URISyntaxException
     */
    @GET
    @Path("as_sjis")
    public Response as_sjis() throws URISyntaxException {
        return Response.status(Status.OK)
                .entity(new byte[] { 0x0, 0x1, 0x2, 0x3 })
                .type(MediaType.TEXT_PLAIN_TYPE.withCharset("Windows-31J"))
                .entity("こんにちわ").encoding("Windows-31J").build();
    }
}
