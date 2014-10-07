package org.jbug.devping.vo;

import java.util.Set;

public class UserVo {
	private String name;
	private String userId;
	private String nickName;
	private Set<String> PersonalTagList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Set<String> getPersonalTagList() {
		return PersonalTagList;
	}
	public void setPersonalTagList(Set<String> personalTagList) {
		PersonalTagList = personalTagList;
	}
	
	@Override
	public String toString() {
		return "UserVo [name=" + name + ", userId=" + userId + ", nickName="
				+ nickName + ", PersonalTagList=" + PersonalTagList + "]";
	}
}
