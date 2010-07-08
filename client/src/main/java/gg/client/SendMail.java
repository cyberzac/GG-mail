package gg.client;

import gg.proto.EmailWrapper;
import gnu.getopt.Getopt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class SendMail {

    private static Logger log = LoggerFactory.getLogger("SendMail");

    public static void main(String[] args) {

        int c;
        String from = "nobody@inter.net";
        String subject = "";
        Getopt getOpt = new Getopt("ggmail", args, "f:s:");
        while ((c = getOpt.getopt()) != -1) {
            switch (c) {
                case 'f':
                    from = getOpt.getOptarg();
                    break;
                case 's':
                    subject = getOpt.getOptarg();
            }
        }
        StringBuilder bodyBuilder = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while (!".".equals(line)) {
            bodyBuilder.append(line);
            try {
                line = in.readLine();
            } catch (IOException e) {
                log.error("Bad mail day, retry...");
                System.exit(1);
            }

        }
        List<String> input = Arrays.asList(args);
        List<String> to = input.subList(getOpt.getOptind(), input.size());
        String body = bodyBuilder.toString();
        EmailWrapper emailWrapper = new EmailWrapper(from, to, subject, body);
        emailWrapper.send();
    }
}
