package org.jbug.devping.interfaces.api;

import org.jbug.devping.service.TagService;
import org.jbug.devping.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


    @RequestMapping(value = "/pingRequest", method = RequestMethod.POST)
    public
    @ResponseBody
    Project pingRequestTest() {

//        JSONParser parser = new JSONParser();
//        try {
//            JSONObject jObj = (JSONObject) parser.parse(pingDataJson);
//            System.out.println(jObj);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        System.out.println("test");
        return new Project("devping");


//        TreeSet<String> peopleWithTag = null;
//        TreeSet<String> selectedPeople = null;
//        StringTokenizer stringTokenizer = new StringTokenizer(tagList);
//        while (stringTokenizer.hasMoreTokens()) {
//            peopleWithTag = tagService.findPeopleWithTag(stringTokenizer.nextToken());
//            combine(selectedPeople, peopleWithTag);
//        }
//
//        //결과값 확인( store )
//
//        ModelAndView model = new ModelAndView("csTestResult");
//        model.addObject("selectedPeople", selectedPeople);
//        model.addObject("tagList", tagList);
//        model.addObject("case", "PingRequest");

//        return model;
    }

    class Project {
        private String projectName;

        Project(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }
    }


}
