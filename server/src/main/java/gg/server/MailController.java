package gg.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

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

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private Mailer mailer;

    private final ModelAndView success = new ModelAndView("success");


    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";

    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleEmailUpload(@RequestParam("email") MultipartFile email) {
        if (!email.isEmpty()) {
            try {
                byte[] bytes = email.getBytes();
                log.debug("Got POST {} bytes", bytes.length);
                mailer.sendMail(bytes);
            } catch (IOException e) {
                return errorPage("Failed receiving email data", e);
            } catch (RuntimeException e) {
                return errorPage("Failed sending mail", e);
            }
        }
        return success;
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ModelAndView handleEmailPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                log.debug("Got PUT");
                mailer.sendMail(inputStream);
            }
        } catch (IOException e) {
            return errorPage("Failed handling put {}", e);
        } catch (RuntimeException e) {
            return errorPage("Failed sending mail", e);
        }
        return success;
    }

    private ModelAndView errorPage(String reason, Exception exception) {
        log.error("{}: {}", reason, exception.getMessage());
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("reason", reason);
        mav.addObject("exception", exception);
        return mav;
    }

    // Use for mocking

    void setMailer(Mailer mailer) {
        this.mailer = mailer;
    }
}
