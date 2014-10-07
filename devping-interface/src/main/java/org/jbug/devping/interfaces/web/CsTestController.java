package org.jbug.devping.interfaces.web;

import org.jbug.devping.vo.AdeptsSuggectionVo;
import org.jbug.devping.vo.PingToServerRequsetVo;
import org.jbug.devping.vo.PingToServerResponseVo;
import org.jbug.devping.vo.TagPrefixVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
public class CsTestController {

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String tagMain() {
		return "/csTest";
	}
	
	private static final Logger logger = LoggerFactory.getLogger(CsTestController.class);
	
	@RequestMapping(value = "/traceTagName.ajax", method = RequestMethod.POST)
	public @ResponseBody TagPrefixVo traceTagName(@RequestBody TagPrefixVo data) {
		logger.info(data.toString());
		
		logger.info("suggest tag list.");
		data.setTagList(null);
		data.setDate("2014-10-08 23:15:08");
		
		return data;
	}
	
	@RequestMapping(value = "/searchUser.ajax", method = RequestMethod.POST)
	public @ResponseBody AdeptsSuggectionVo searchAdepts(@RequestBody AdeptsSuggectionVo data) {
		
		logger.info("provide expert list.");
		data.setUserIdsWithTag(null);
		data.setTotalMembers("1");
		data.setDate("2014-10-08 23:15:08");
		
		return data;
	}
	
	@RequestMapping(value = "/ping.ajax", method = RequestMethod.POST)
	public @ResponseBody PingToServerResponseVo ping(@RequestBody PingToServerRequsetVo data) {
		
		logger.info("send message by WebSocket.");
		logger.info("make channel ID.");
		PingToServerResponseVo answer = new PingToServerResponseVo();
		answer.setChannelId("1234");
		answer.setDate("2014-10-08 23:15:08");
		answer.setResult("success");
		answer.setFunc("pingToServer");
		
		return answer;
	}
}
