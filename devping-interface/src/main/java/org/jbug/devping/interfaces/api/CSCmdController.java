package org.jbug.devping.interfaces.api;

import org.jbug.devping.service.TagService;
import org.jbug.devping.vo.TagPrefixVo;
import org.jbug.devping.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by jhouse on 9/27/14.
 */
@Controller
@RequestMapping(value = "/cs")
public class CSCmdController {
    @Autowired
    TagService tagService;
    private HashMap<String, UserVo> TestUserVo = new HashMap<>();


    @RequestMapping(value = "/tagPrefix", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    TagPrefixVo pingRequestTest(@RequestBody final TagPrefixVo tagPrefixVo) {

        System.out.println(tagPrefixVo);

        System.out.println("test");
        return tagPrefixVo;


//
//        //결과값 확인( store )
//
//        ModelAndView model = new ModelAndView("csTestResult");
//        model.addObject("selectedPeople", selectedPeople);
//        model.addObject("tagList", tagList);
//        model.addObject("case", "PingRequest");

//        return model;
    }




}
