package org.jbug.devping.interfaces.web;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import org.apache.taglibs.standard.tag.common.core.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by kyungseopahn on 2014. 10. 14..
 * Handle Push message
 */
@Controller
public class PushTestController {

    private static final Logger logger = LoggerFactory.getLogger(PushTestController.class);

    @RequestMapping(value = "/push", method = RequestMethod.GET)
    public String push(Locale locale, Model model, HttpServletRequest request) {

        logger.info("/push");
        return "pushForm";
    }

    @RequestMapping(value = "/pushToMobile", method = RequestMethod.POST)
    public String pushToMobile(Locale locale, Model model, HttpServletRequest request) {

        logger.info("/pushToMobile");

        String message = request.getParameter("message");
        String deviceId = request.getParameter("deviceId");
        String apiKey = request.getParameter("apiKey");


        try {

            message = Util.URLEncode(message, "UTF-8");
            final Message.Builder messageBuilder = new Message.Builder();
            messageBuilder.addData("msg", message);
            final Result result = new Sender(apiKey).send(messageBuilder.build(), deviceId, 5);
            final String messageId = result.getMessageId();

            //boolean success = (messageId != null);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "hello";
    }


}
