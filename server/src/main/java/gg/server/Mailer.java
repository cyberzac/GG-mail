package gg.server;

import com.google.protobuf.InvalidProtocolBufferException;
import gg.proto.EmailWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: zac
 * Date: 2010-jul-08
 * Time: 10:33:42
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
@Component
public class Mailer {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MailSender mailSender;

    public void sendMail(byte[] bytes) throws EmailException {
        try {
            sendMail(new EmailWrapper(bytes));
        } catch (InvalidProtocolBufferException e) {
            throw new EmailException(e);
        }
    }

    private void sendMail(EmailWrapper emailWrapper) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(emailWrapper.getToAsArray());
        mail.setFrom(emailWrapper.getFrom());
        mail.setSubject(emailWrapper.getSubject());
        mail.setText(emailWrapper.getBody());
        mailSender.send(mail);
        log.debug("Sent mail");
    }


    public void sendMail(InputStream inputStream) throws EmailException {
        try {
            sendMail(new EmailWrapper(inputStream));
        } catch (IOException e) {
            throw new EmailException(e);
        }

    }

    // Use for mocking

    void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

}
