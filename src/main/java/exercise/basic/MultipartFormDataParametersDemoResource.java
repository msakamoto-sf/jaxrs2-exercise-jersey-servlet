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
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.google.common.io.BaseEncoding;
import com.google.common.io.ByteStreams;

/**
 * @author "Masahiko Sakamoto" <sakamoto.gsyc.3s@gmail.com>
 * @see https://jersey.java.net/documentation/latest/media.html#multipart
 * @see https://jersey.java.net/apidocs/2.11/jersey/org/glassfish/jersey/media/multipart/package-summary.html
 * @see https://github.com/jersey/jersey/tree/2.11/examples/multipart-webapp
 * @see http://yumix.hatenablog.jp/entry/2012/12/17/002515
 * @see http://kokuzawa.github.io/blog/2013/12/22/jaxrs-upload-multiple-files/
 * @see http://stackoverflow.com/questions/14563219/jaxrs-multipart
 */
@Path("/ex1/multipart-form-data-demo")
public class MultipartFormDataParametersDemoResource {

    @POST
    @Path("/simple")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("text/plain")
    public String simple(@FormDataParam("upfile") String contentsAsString,
            @FormDataParam("upfile") InputStream fileStream,
            @FormDataParam("upfile") FormDataContentDisposition filecd)
            throws IOException {
        byte[] filedata = ByteStreams.toByteArray(fileStream);
        String hexdump = BaseEncoding.base16().lowerCase().encode(filedata);
        StringBuilder sb = new StringBuilder();
        sb.append("filedata=[" + contentsAsString + "]\r\n");
        sb.append("filename=[" + filecd.getFileName() + "]\r\n");
        sb.append("filesize=[" + filecd.getSize() + "]\r\n");
        sb.append("filetype=[" + filecd.getType() + "]\r\n");
        sb.append("hexdump=[" + hexdump + "]\r\n");
        return sb.toString();
    }

    @POST
    @Path("/multi")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("text/plain")
    public String multi(@QueryParam("query1") String query1,
            @QueryParam("query2") String query2,
            @FormDataParam("textparam1") String textparam1,
            @FormDataParam("textparam2") String textparam2,
            @FormDataParam("upfile[]") List<FormDataBodyPart> multiparts)
            throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("query1=" + query1 + "\r\n");
        sb.append("query2=" + query2 + "\r\n");
        sb.append("textparam1=" + textparam1 + "\r\n");
        sb.append("textparam2=" + textparam2 + "\r\n");
        sb.append("----------------\r\n");
        for (int i = 0; i < multiparts.size(); i++) {
            FormDataBodyPart part = multiparts.get(i);
            byte[] filedata = ByteStreams.toByteArray(part
                    .getValueAs(InputStream.class));
            String hexdump = BaseEncoding.base16().lowerCase().encode(filedata);
            FormDataContentDisposition filecd = part
                    .getFormDataContentDisposition();
            sb.append("filename[" + i + "]=[" + filecd.getFileName() + "]\r\n");
            sb.append("filesize[" + i + "]=[" + filecd.getSize() + "]\r\n");
            sb.append("filetype[" + i + "]=[" + filecd.getType() + "]\r\n");
            sb.append("hexdump[" + i + "]=[" + hexdump + "]\r\n");
            sb.append("----------------\r\n");
        }
        return sb.toString();
    }
}
