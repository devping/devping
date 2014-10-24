package org.jbug.devping.vo;

import java.util.List;

/**
 * Created by jhouse on 10/7/14.
 */
public class TagPrefixVo extends DataVo {
    private String prefix;
    private List<String> tagList;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "TagPrefixVo [prefix=" + prefix + ", tagList=" + tagList + "]";
    }
}
