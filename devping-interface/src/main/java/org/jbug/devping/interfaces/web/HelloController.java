package org.jbug.devping.interfaces.web;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by nadal on 2014. 9. 15..
 */

@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @RequestMapping(value="/{name}", method = RequestMethod.GET)
    public String hello(@RequestParam("name") String name, Model model) {
        Preconditions.checkNotNull(name, "Name 정보가 없습니다.");
        model.addAttribute("name", name);
        return "/hello";
    }
}

