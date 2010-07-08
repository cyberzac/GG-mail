/**
 * User: zac
 * Date: 2010-jul-06
 * Time: 10:44:31
 * <p/>
 * Copyright © 2010 Martin Zachrison
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package gg.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


public class EmailWrapper {

    private Logger log = LoggerFactory.getLogger(getClass());
    //@Autowired
    private RestTemplate restTemplate = new RestTemplate();

    private final Email.email email;
    private final static String URI = "http://localhost:8080/server/app/sendmail";

    public EmailWrapper(String from, List<String> to, String subject, String body) {
        Email.email.Builder builder = Email.email.newBuilder();
        builder.setFrom(from);
        builder.addAllTo(to);
        builder.setSubject(subject);
        builder.setBody(body);
        email = builder.build();
    }

    public EmailWrapper(byte[] data) throws InvalidProtocolBufferException {
        email = Email.email.parseFrom(data);
    }

    public EmailWrapper(InputStream is) throws IOException {
        email = Email.email.parseFrom(is);
    }

    public void send() {
        log.info("Sending mail is: {}", email);

        URI uri = null;
        try {
            uri = new URI(URI);
        } catch (URISyntaxException e) {
            log.error("Bad URI to email service, ignoring mail");
            return;
        }
        restTemplate.put(uri, email.toByteArray());
        /*
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("email.proto-bin"));
            email.writeTo(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    public List<String> getTo() {
        return email.getToList();
    }

    public String getFrom() {
        return email.getFrom();
    }

    public String getBody() {
        return email.getBody();
    }

    public String getSubject() {
        return email.getSubject();
    }

}
