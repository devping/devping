package org.jbug.devping.interfaces.web;

import org.jbug.devping.domain.member.Member;
import org.jbug.devping.domain.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by nadal on 2014. 10. 5..
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController {
    @Autowired
    private MemberService memberService;


    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public @ResponseBody String signIn( Model model, HttpServletRequest request) {
        Member m = new Member();
        m.setName("ted");
        m.setEmail("ted@ted.com");
        m.setPassword("password");
        m.setProviderType("Google");
        m.setRegDttm(new Date());

        memberService.signIn(m);
        return "login";
    }

}
