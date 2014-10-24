package org.jbug.devping.interfaces.api;

import org.jbug.devping.channel.ChannelService;
import org.jbug.devping.service.TagService;
import org.jbug.devping.utils.StringUtil;
import org.jbug.devping.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/cs")
public class CsTestController {
	private static final Logger logger = LoggerFactory.getLogger(CsTestController.class);

    @Autowired
    HttpSession httpSession;
    @Autowired
    TagService tagService;

    @Autowired
    ChannelService channelService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String tagMain() {
        return "/csTest";
    }

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    public String tagEcho() {
        return "/echo";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {
        UserVo userVo = new UserVo();
        userVo.setName("Jooho Lee");
        userVo.setUserId("ljhiyh");
        userVo.setNickName("Jhouse");
        HashSet<String> tagList = new HashSet<>();
        tagList.add("java");
        tagList.add("jboss");
        userVo.setPersonalTagList(tagList);
        httpSession.setAttribute("userVo",userVo);

        return "/echo";
    }

	@RequestMapping(value = "/traceTagName.ajax", method = RequestMethod.POST)
	public @ResponseBody TagPrefixVo traceTagName(@RequestBody TagPrefixVo data) {
		logger.info(data.toString());
		
		logger.info("suggest tag list.");
        List<String> tagListWithPrefix = new ArrayList<>();
        TreeSet<String> tagTreeSetWithPrefix = tagService.findTagWithPrefix(data.getPrefix());
        tagListWithPrefix.addAll(tagTreeSetWithPrefix);

		data.setTagList(tagListWithPrefix);
		data.setDate((new Date()).toString());

		return data;
	}
	
	@RequestMapping(value = "/searchUser.ajax", method = RequestMethod.POST)
	public @ResponseBody AdeptsSuggectionVo searchAdepts(@RequestBody AdeptsSuggectionVo data) {
		logger.info(data.toString());
		
		logger.info("provide expert list.");
        TreeSet<String> selectedPeople = null;
        selectedPeople = tagService.findPeopleWithTagList(data.getTagList());
        data.setUserIdsWithTag(StringUtil.setToList(selectedPeople));
		data.setTotalMembers("1");
		data.setDate((new Date()).toString());
		
		return data;
	}
	
	@RequestMapping(value = "/ping.ajax", method = RequestMethod.POST)
	public @ResponseBody PingToServerResponseVo ping(@RequestBody PingToServerRequsetVo data) {
		logger.info(data.toString());
		
		logger.info("send message by WebSocket.");
		logger.info("make channel ID.");

        PingToServerResponseVo pingToServerResponseVo = channelService.ping(data);


		
		return pingToServerResponseVo;
	}
}
