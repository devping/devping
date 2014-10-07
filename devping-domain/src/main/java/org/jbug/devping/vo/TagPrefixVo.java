package org.jbug.devping.vo;

import java.util.ArrayList;

/**
 * Created by jhouse on 10/7/14.
 */
public class TagPrefixVo {
    private String prefix;
    private ArrayList<TagVo> tagList = new ArrayList<>();

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public ArrayList<TagVo> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<TagVo> tagList) {
        this.tagList = tagList;
    }


}
