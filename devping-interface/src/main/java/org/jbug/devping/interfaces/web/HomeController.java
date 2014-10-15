package org.jbug.devping.interfaces.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
//@RequestMapping(value = "/")
public class HomeController {
	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private int id = 0;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
@RequestMapping(value = "/main", method = RequestMethod.POST)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		if( request.getParameter("type").equals("login") ){
			//check login info
			System.out.println(request.getParameter("identification") );
			System.out.println(request.getParameter("password") );
			//set ID
			model.addAttribute( "myId", request.getParameter("identification") );
			//success
			return "home";
			//fail
			//return "login";
		} else if( request.getParameter("type").equals("regist") ){
			//regist new member
			System.out.println( request.getParameter("id") );
			System.out.println( request.getParameter("email") );
			System.out.println( request.getParameter("pw") );
			System.out.println( request.getParameter("tags") );
			//set ID
			model.addAttribute( "myId", request.getParameter("id") );
			//success
			return "home";
			//fail
			//return "login";
		}
		//fail
		return "login";
	}
	
//	@RequestMapping(value="/login")
//	public String login(Locale locale, Model model) {
//		logger.info("Welcome login! The client locale is {}.", locale);
//		return "login";
//	}
	
	@RequestMapping(value = "/ping.do", method = RequestMethod.POST)
	public @ResponseBody String ping(Locale locale, @RequestBody HashMap data) {
		logger.info("server get Ping");
		logger.info(data.toString());
		return String.valueOf(id++);
	}
	
	@RequestMapping(value = "/sendMessage.do", method = RequestMethod.POST)
	public @ResponseBody String sendMessage(Locale locale, @RequestBody HashMap data) {
		logger.info("server get message!");
		logger.info(data.toString());
		return "server get message!";
    }
//    @RequestMapping(value = "/signin", method = RequestMethod.GET)
//    public @ResponseBody String signin(Locale locale, @RequestBody HashMap data) {
//        logger.info("server get message!");
//        logger.info(data.toString());
//        return "server get message!";
//    }
    @RequestMapping(value = "/auth/google", method = RequestMethod.GET)
    public @ResponseBody String authgoogle(Locale locale, @RequestBody HashMap data) {
        logger.info("server get message!");
        logger.info(data.toString());
        return "server get message!";
    }
}
