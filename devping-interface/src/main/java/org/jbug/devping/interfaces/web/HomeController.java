package org.jbug.devping.interfaces.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private int id = 0;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping({"","main"})
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "home";
	}
	
	@RequestMapping(value = "/ping.do", method = RequestMethod.POST)
	public @ResponseBody String ping(Locale locale, @RequestParam("data") String data) {
		logger.info("server get Ping");
		logger.info(data.toString());
		return String.valueOf(id++);
	}
	
	@RequestMapping(value = "/sendMessage.do", method = RequestMethod.POST)
	public @ResponseBody String sendMessage(Locale locale, @RequestParam("data") String data) {
		logger.info("server get message!");
		logger.info(data.toString());
		return "server get message!";
	}
}
