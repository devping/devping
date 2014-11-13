package org.jbug.devping.store;

import edu.princeton.cs.algs4.TST;
import org.jbug.devping.cache.ITagCache;
import org.jbug.devping.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by jhouse on 9/28/14.
 */
@Component
public class TagStore {


    @Autowired
    private TST<String> tagTree;

    @Autowired
    private ITagCache tagCache;


    public void addTagToTreeAndCache(UserVo userVo) {
        System.out.println("In tagStore");
        Set<String> userListForTag;
        String userName = userVo.getUserId();
        for (String tag : userVo.getPersonalTagList()) {
            tagTree.put(tag, tag);
            userListForTag = tagCache.get(tag);
            if (userListForTag == null) {
                userListForTag = new TreeSet<>();
                System.out.println("In userListForTag for statement");
            }
            // update this user to userList
            userListForTag.add(userName);
            // update userList for the store
            System.out.println("before put store to cache");
            tagCache.put(tag, userListForTag);


        }
    }

    public void removeUserFromTag(UserVo userVo) {
        Set<String> userListForTag;
        String userName = userVo.getUserId();
        for (String tag : userVo.getPersonalTagList()) {
            userListForTag = tagCache.get(tag);
            if (userListForTag == null) {
                userListForTag = new TreeSet<>();
                System.out.println("In userListForTag for statement");
            }
            // update this user to userList
            userListForTag.remove(userName);
            // update userList for the store
            System.out.println("before put store to cache");
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

    private TreeSet<String> findPeopleWithTag(String tag) {

        TreeSet<String> peopleWithTag = (TreeSet) tagCache.get(tag);
//        System.out.println(people.size());
//        System.out.println(people.contains("ljhiyh"));
        if (peopleWithTag == null)
            peopleWithTag = new TreeSet<>();

        return peopleWithTag;

    }
    public TreeSet<String> findPeopleWithTagList(List<String> tagList) {
        TreeSet<String> peopleWithTag = null;
        TreeSet<String> selectedPeople = null;

        if (tagList.size() <0) {
            selectedPeople = new TreeSet<>();
        }else{
            for(String tag : tagList){
                peopleWithTag = findPeopleWithTag(tag);
                selectedPeople = combine(selectedPeople, peopleWithTag);
            }
        }
        return selectedPeople;

    }


    private TreeSet combine(TreeSet<String> selectedPeople, TreeSet<String> peopleWithTag) {
        TreeSet<String> newSelectedPeople = new TreeSet<>();
        if (selectedPeople == null)
            selectedPeople = peopleWithTag;
        else {
            if (selectedPeople.size() <= peopleWithTag.size()) {
                for (String selectedPrivousPerson : selectedPeople) {
                    if(peopleWithTag.contains(selectedPrivousPerson)){
                        newSelectedPeople.add(selectedPrivousPerson);
                    }
                }
            }else{
                for (String selectedPersonByTag : peopleWithTag) {
                    if(selectedPeople.contains(selectedPersonByTag)){
                        newSelectedPeople.add(selectedPersonByTag);
                    }
                }
            }
            selectedPeople = newSelectedPeople;

        }
        return selectedPeople;
    }




}
