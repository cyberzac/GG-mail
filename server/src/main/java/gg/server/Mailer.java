package gg.server;

import com.google.protobuf.InvalidProtocolBufferException;
import gg.proto.EmailWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

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

    @Autowired
    private MailSender mailSender;

    public String sendMail(byte[] bytes) {
        EmailWrapper emailWrapper = null;
        try {
            emailWrapper = new EmailWrapper(bytes);
        } catch (InvalidProtocolBufferException e) {
           return "Invalid mail: "+e.getMessage();
        }
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(emailWrapper.getTo().toArray(new String[0]));
        mail.setFrom(emailWrapper.getFrom());
        mail.setSubject(emailWrapper.getSubject());
        mail.setText(emailWrapper.getBody());
        mailSender.send(mail);
        return "ok";
    }

    protected void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
}
