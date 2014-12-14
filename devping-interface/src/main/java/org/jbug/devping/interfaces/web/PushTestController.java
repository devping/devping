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
import java.util.concurrent.TimeUnit;

/**
 * Created by kyungseopahn on 2014. 10. 14..
 * Handle Push message
 */
@Controller
public class PushTestController {

    private static final Logger logger = LoggerFactory.getLogger(PushTestController.class);
    private static final int TTL = (int) TimeUnit.MINUTES.toSeconds(300);

    @RequestMapping(value = "/push", method = RequestMethod.GET)
    public String push(Locale locale, Model model, HttpServletRequest request) {

        logger.info("/push");
        return "pushForm";
    }

    @RequestMapping(value = "/pushToMobile", method = RequestMethod.POST)
    public String pushToMobile(Locale locale, Model model, HttpServletRequest request) {

        logger.info("/pushToMobile");

        String deviceId = request.getParameter("deviceId");
        String text = "hahahah";
        String apiKey = "";

        logger.info(deviceId);


        try {

            text = Util.URLEncode(text, "UTF-8");
            final Message.Builder builder = new Message.Builder();
            builder.collapseKey("announcement")
                    .addData("action", "announcement")
                    .addData("extraData", text)
                    .timeToLive(TTL);

            Message message = builder.build();
            final Result result = new Sender(apiKey).sendNoRetry(message, deviceId);
            final String messageId = result.getMessageId();

            boolean success = (messageId != null);

            logger.info("" + success);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "push";
    }


}
