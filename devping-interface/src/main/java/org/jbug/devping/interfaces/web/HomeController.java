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
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping({
		"main"
	})
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "home";
	}
	
	@RequestMapping(value="/ping-search")
	public String pingSearch(Locale locale, Model model) {
		logger.info("Welcome ping-search! The client locale is {}.", locale);
		return "ping/search";
	}
	
	@RequestMapping(value="/ping-write")
	public String pingQuestion(Locale locale, Model model) {
		logger.info("Welcome ping-write! The client locale is {}.", locale);
		return "ping/question";
	}
	
	@RequestMapping(value = "/searchExperts.do", method = RequestMethod.POST)
	public @ResponseBody String searchExperts(Locale locale, @RequestParam("data") String data) {
		logger.info("Welcome searchExperts! The client locale is {}.", locale);
		
		logger.info(data.toString());
		
		return "server return : " + data;
	}
}
