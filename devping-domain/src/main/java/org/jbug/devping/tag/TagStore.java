package org.jbug.devping.tag;

import edu.princeton.cs.algs4.TST;
import org.jbug.devping.cache.TagCache;
import org.jbug.devping.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by jhouse on 9/28/14.
 */
@Component()
public class TagStore {


    @Autowired
    private TST<String> tagTree;

    @Autowired
    private TagCache tagCache;


    public void addTagToTreeAndCache(UserVo userVo) {
        System.out.println("In tagStore");
        Set<String> userListForTag;
        String userName =userVo.getName();
        for (String tag : userVo.getPersonalTagList()) {
            tagTree.put(tag, tag);
            userListForTag = tagCache.get(tag);
            if (userListForTag == null) {
                userListForTag = new TreeSet<>();
                System.out.println("In userListForTag for statement");
            }
            // update this user to userList
            userListForTag.add(userName);
            // update userList for the tag
            System.out.println("before put tag to cache");
            tagCache.put(tag, userListForTag);


        }
    }
    public void removeUserFromTag(UserVo userVo) {
        Set<String> userListForTag;
        String userName =userVo.getName();
        for (String tag : userVo.getPersonalTagList()) {
            userListForTag = tagCache.get(tag);
            if (userListForTag == null) {
                userListForTag = new TreeSet<>();
                System.out.println("In userListForTag for statement");
            }
            // update this user to userList
            userListForTag.remove(userName);
            // update userList for the tag
            System.out.println("before put tag to cache");
            tagCache.put(tag, userListForTag);


        }

    }

    public TreeSet<String> findTagWithPrefix(String prefix) {

        Iterator<String> tempTagListWithPrefix = tagTree.prefixMatch(prefix).iterator();
        TreeSet<String> tagListWithPrefix = new TreeSet<>();

        while (tempTagListWithPrefix.hasNext()) {
            tagListWithPrefix.add(tempTagListWithPrefix.next());
        }
        return tagListWithPrefix;
    }

    public TreeSet<String> findPeopleWithTag(String tag) {

        TreeSet<String> people = (TreeSet)tagCache.get(tag);
//        System.out.println(people.size());
//        System.out.println(people.contains("ljhiyh"));
        if(people == null)
            people = new TreeSet<>();
        return people;

    }


}
