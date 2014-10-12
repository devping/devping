package org.jbug.devping.vo;

import java.util.List;

public class AdeptsSuggectionVo extends DataVo {
    private List<String> tagList;
    private List<String> userIdsWithTag;
    private String TotalMembers;

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public List<String> getUserIdsWithTag() {
        return userIdsWithTag;
    }

    public void setUserIdsWithTag(List<String> userIdsWithTag) {
        this.userIdsWithTag = userIdsWithTag;
    }

    public String getTotalMembers() {
        return TotalMembers;
    }

    public void setTotalMembers(String totalMembers) {
        TotalMembers = totalMembers;
    }

    @Override
    public String toString() {
        return "AdeptsSuggectionVo [tagList=" + tagList + ", userIdsWithTag="
                + userIdsWithTag + ", TotalMembers=" + TotalMembers + "]";
    }
}
