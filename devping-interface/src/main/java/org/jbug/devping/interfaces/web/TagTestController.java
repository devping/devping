package org.jbug.devping.interfaces.web;

import org.jbug.devping.service.TagService;
import org.jbug.devping.utils.StringUtil;
import org.jbug.devping.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by jhouse on 9/27/14.
 */
@Controller
@RequestMapping(value = "/tag")
public class TagTestController {
    @Autowired
    TagService tagService;

    @Autowired
    HttpSession httpSession;
    private HashMap<String, UserVo> TestUserVo = new HashMap<>();

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String tagMain() {
        return "/tagTest";
    }

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    public String tagEcho() {
        return "/echo";
    }

    @RequestMapping(value = "/mqtt", method = RequestMethod.GET)
    public String hiveQ() {
        return "/hiveQ";
    }



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {
//        UserVo userVo = new UserVo();
//        userVo.setName("Jooho Lee");
//        userVo.setUserId("ljhiyh");
//        userVo.setNickName("Jhouse");
//        HashSet<String> tagList = new HashSet<>();
//        tagList.add("java");
//        tagList.add("jboss");
//        userVo.setPersonalTagList(tagList);
//        httpSession.setAttribute("userVo",userVo);
//
        return "/echo";
    }

    @RequestMapping(value = "/loginUpdateTag", method = RequestMethod.POST)
    public ModelAndView loginUpdateTagTest(
            String id,
            String tagList
    ) {

        Set<String> personalTag = new HashSet<>();

        StringTokenizer stringTokenizer = new StringTokenizer(tagList);
        while (stringTokenizer.hasMoreTokens()) {
            personalTag.add(stringTokenizer.nextToken());
        }

        UserVo userVo = UserVo.getBuilder()
            .userId(id)
            .password("123")
            .personalTagList(personalTag)
            .build();
        TestUserVo.put(id, userVo);
        tagService.loginUpdateTagService(userVo);

        //결과값 확인( prefix )
        TreeSet<String> tagListWithJ = tagService.findTagWithPrefix("j");
        TreeSet<String> tagListWithW = tagService.findTagWithPrefix("w");
        TreeSet<String> tagListWithKorean = tagService.findTagWithPrefix("웹");

        //자바 테그를 가진 사람들
        List<String> javaTag = new ArrayList<>();
        javaTag.add("java");
        TreeSet<String> peopleWithTag = tagService.findPeopleWithTagList(javaTag);


        ModelAndView model = new ModelAndView("tagTestResult");
        model.addObject("tagListWithJ", tagListWithJ);
        model.addObject("tagListWithW", tagListWithW);
        model.addObject("tagListWithKorean", tagListWithKorean);
        model.addObject("peopleWithTag", peopleWithTag);
        model.addObject("case", "loginUpdateTag");
        return model;
    }


    @RequestMapping(value = "/logoutUpdateTag", method = RequestMethod.POST)
    public ModelAndView logoutUpdateTagTest(String id) {
        UserVo userVo = TestUserVo.get(id);

        tagService.logoutUpdateTagService(userVo);

        //결과값 확인( prefix )
        TreeSet<String> tagListWithJ = tagService.findTagWithPrefix("j");
        TreeSet<String> tagListWithW = tagService.findTagWithPrefix("w");
        TreeSet<String> tagListWithKorean = tagService.findTagWithPrefix("웹");

        //자바 테그를 가진 사람들
        List<String> javaTag = new ArrayList<>();
        javaTag.add("java");
        TreeSet<String> peopleWithTag = tagService.findPeopleWithTagList(javaTag);


        ModelAndView model = new ModelAndView("tagTestResult");
        model.addObject("tagListWithJ", tagListWithJ);
        model.addObject("tagListWithW", tagListWithW);
        model.addObject("tagListWithKorean", tagListWithKorean);
        model.addObject("peopleWithTag", peopleWithTag);
        model.addObject("case", "logoutUpdateTag");

        return model;
    }

    @RequestMapping(value = "/searchTagWithPrefix", method = RequestMethod.POST)
    public ModelAndView searchTagWithPrefixTest(String prefix) {
        //결과값 확인( prefix )
        TreeSet<String> tagListWithPrefix = tagService.findTagWithPrefix(prefix);
        ModelAndView model = new ModelAndView("tagTestResult");
        model.addObject("tagListWithPrefix", tagListWithPrefix);
        model.addObject("prefix", prefix);
        model.addObject("case", "searchTagWithPrefix");

        return model;
    }

    @RequestMapping(value = "/findPeopleWithTagList", method = RequestMethod.POST)
    public ModelAndView findPeopleWithTagListTest(String tagList) {

        TreeSet<String> selectedPeople = null;
        List<String> tagListCollection = StringUtil.arrayToList(tagList);
        selectedPeople = tagService.findPeopleWithTagList(tagListCollection);

        //결과값 확인( store )
        ModelAndView model = new ModelAndView("tagTestResult");
        model.addObject("peopleWithTagList", selectedPeople);
        model.addObject("tagList", tagList);
        model.addObject("case", "findPeopleWithTagList");

        return model;
    }




}
