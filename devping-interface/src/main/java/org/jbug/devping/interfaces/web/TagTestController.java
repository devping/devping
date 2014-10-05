package org.jbug.devping.interfaces.web;

import org.jbug.devping.service.TagService;
import org.jbug.devping.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by jhouse on 9/27/14.
 */
@Controller
@RequestMapping(value = "/tag")
public class TagTestController {
    @Autowired
    TagService tagService;
    private HashMap<String, UserVo> TestUserVo = new HashMap<>();

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String tagMain() {
        return "/tagTest";
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

        UserVo userVo = new UserVo();
        userVo.setName(id);
        userVo.setPersonalTagList(personalTag);
        TestUserVo.put(id, userVo);
        tagService.LoginUpdateTagService(userVo);

        //결과값 확인( prefix )
        TreeSet<String> tagListWithJ = tagService.findTagWithPrefix("j");
        TreeSet<String> tagListWithW = tagService.findTagWithPrefix("w");
        TreeSet<String> tagListWithKorean = tagService.findTagWithPrefix("웹");

        //자바 테그를 가진 사람들
        TreeSet<String> peopleWithTag = tagService.findPeopleWithTag("java");


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

        tagService.LogoutUpdateTagService(userVo);

        //결과값 확인( prefix )
        TreeSet<String> tagListWithJ = tagService.findTagWithPrefix("j");
        TreeSet<String> tagListWithW = tagService.findTagWithPrefix("w");
        TreeSet<String> tagListWithKorean = tagService.findTagWithPrefix("웹");

        //자바 테그를 가진 사람들
        TreeSet<String> peopleWithTag = tagService.findPeopleWithTag("java");


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

        TreeSet<String> peopleWithTag = null;
        TreeSet<String> selectedPeople = null;
        StringTokenizer stringTokenizer = new StringTokenizer(tagList);
        while (stringTokenizer.hasMoreTokens()) {
            peopleWithTag = tagService.findPeopleWithTag(stringTokenizer.nextToken());
            selectedPeople = combine(selectedPeople, peopleWithTag);
        }

        //결과값 확인( store )
        ModelAndView model = new ModelAndView("tagTestResult");
        model.addObject("peopleWithTagList", selectedPeople);
        model.addObject("tagList", tagList);
        model.addObject("case", "findPeopleWithTagList");

        return model;
    }


    @RequestMapping(value = "/pingRequest", method = RequestMethod.POST)
    public ModelAndView pingRequestTest( String userId) {

        System.out.println(userId);







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
        ModelAndView model = new ModelAndView("tagTestResult");
//        model.addObject("selectedPeople", selectedPeople);
//        model.addObject("tagList", tagList);
//        model.addObject("case", "PingRequest");

        return model;
    }

    private TreeSet combine(TreeSet<String> selectedPeople, TreeSet<String> peopleWithTag) {
        TreeSet<String> newSelectedPeople = new TreeSet<>();
        if (selectedPeople == null)
            selectedPeople = peopleWithTag;
        else {
            if (selectedPeople.size() <= peopleWithTag.size()) {
                for (String selectedPrivousPerson : selectedPeople) {
                    if(peopleWithTag.contains(selectedPrivousPerson)){
                        newSelectedPeople.add(selectedPrivousPerson);
                    }
                }
            }else{
                for (String selectedPersonByTag : peopleWithTag) {
                    if(selectedPeople.contains(selectedPersonByTag)){
                        newSelectedPeople.add(selectedPersonByTag);
                    }
                }
            }
            selectedPeople = newSelectedPeople;

        }
        return selectedPeople;
    }

}
