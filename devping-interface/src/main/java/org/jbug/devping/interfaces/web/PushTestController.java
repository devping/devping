package org.jbug.devping.interfaces.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by kyungseopahn on 2014. 10. 14..
 * Handle Push message
 */
@Controller
public class PushTestController {

    private static final Logger logger = LoggerFactory.getLogger(PushTestController.class);

    @RequestMapping(value = "/push")
    public String push(Locale locale, Model model, HttpServletRequest request) {
        return "/pushTest";
    }
}
