package org.jbug.devping.service;

import org.jbug.devping.tag.TagStore;
import org.jbug.devping.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.TreeSet;

/**
 * Created by jhouse on 9/28/14.
 */
@Service
public class TagService {
    final static Logger logger = LoggerFactory.getLogger(TagService.class);
    @Autowired
    TagStore tagStore;

    public void LoginUpdateTagService(UserVo userVo) {

        System.out.println("in TagService");

        tagStore.addTagToTreeAndCache(userVo);
    }
    public void LogoutUpdateTagService(UserVo userVo) {
        tagStore.removeUserFromTag(userVo);
    }

    public TreeSet<String> findTagWithPrefix(String prefix) {
        return tagStore.findTagWithPrefix(prefix);
    }

    public TreeSet<String> findPeopleWithTag(String tag) {
        return tagStore.findPeopleWithTag(tag);
    }


}
