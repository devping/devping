package org.jbug.devping.vo;

import java.util.Set;

public class UserVo {
    private String name;
    private Set<String> PersonalTagList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPersonalTagList() {
        return PersonalTagList;
    }

    public void setPersonalTagList(Set<String> personalTagList) {
        PersonalTagList = personalTagList;
    }
}
