package org.jbug.devping.interfaces.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by nadal on 14. 10. 6.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String showLoginPage() {
            return "login";
    }

    @RequestMapping(value = "/oauth2callback", method = RequestMethod.GET)
    public String oauth2callback(@RequestParam("code") String code, @RequestParam("state") String state) {
        System.out.println(code);
//        logger.info("code: {}", code);
//        GoogleAPIAdapter ga = new GoogleAPIAdapter();
//        GoogleTokenDto googleTokenDto = ga.getTokens(code);
//        System.out.println(googleTokenDto.getId_token());
//
//        Jwt jwt = JwtHelper.decode(googleTokenDto.getId_token());
//        String claims = jwt.getClaims();
//
//        ObjectMapper om = new ObjectMapper();
//
//        try {
//            GoogleClaim gc = om.readValue(claims, GoogleClaim.class);
//            System.out.println(gc.getEmail());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return "oauth2callback";
    }
}
