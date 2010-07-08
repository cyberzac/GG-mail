package gg.server;

import gg.proto.Email;
import gg.proto.EmailWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * User: zac
 * Date: 2010-jul-06
 * Time: 15:40:40
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
@Controller
@RequestMapping("/sendmail")
public class MailController {

    Logger log = LoggerFactory.getLogger(getClass());
    private Mailer mailer;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView hello() {
        log.debug("hello");
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", "Hello index");
        return mav;

    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleEmailUpload(@RequestParam("email") MultipartFile email) {
        String result = "failed";
        if (!email.isEmpty()) {
            try {
                byte[] bytes = email.getBytes();
                log.debug("sendmail got {} bytes", bytes.length);
                result = mailer.sendMail(bytes);
                result = "ok";
            } catch (IOException e) {
                log.error("Failed receiving email data", e);
            }

        }
        ModelAndView mav = new ModelAndView("result");
        mav.addObject("result", result);
        return mav;
    }


    public void setMailer(Mailer mailer) {
        this.mailer = mailer;
    }
}
