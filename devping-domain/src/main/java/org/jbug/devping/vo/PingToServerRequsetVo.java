package org.jbug.devping.vo;

import java.util.List;

public class PingToServerRequsetVo extends DataVo {
	private List<String> userIdsWithTag;
	private String userId;
	private String nickName;
	private String question;
	
	public List<String> getUserIdsWithTag() {
		return userIdsWithTag;
	}
	public void setUserIdsWithTag(List<String> userIdsWithTag) {
		this.userIdsWithTag = userIdsWithTag;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Override
	public String toString() {
		return "PingToServerRequsetVo [userIdsWithTag=" + userIdsWithTag
				+ ", userId=" + userId + ", nickName=" + nickName
				+ ", question=" + question + "]";
	}
}
