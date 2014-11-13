package org.jbug.devping.store;

import edu.princeton.cs.algs4.TST;
import org.jbug.devping.cache.ITagCache;
import org.jbug.devping.configuration.DevPingCacheConfig;
import org.jbug.devping.configuration.DevPingDomainApplicationContextConfiguration;
import org.jbug.devping.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/*
 * Tag 메서드 관련 테스트 케이스
 *  - register
 *  - search
 *  - filter (and , xor)
 *  - remove (but only related users will be removed, 
 *              which means it will never removed once store is registered)
 *              
 *	- rank 
 *		- top 20 tags that people have input. In other words, people have been looking for.
 *		- top 20 tags that people have as personal information such as school, university and company.              
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DevPingDomainApplicationContextConfiguration.class,DevPingCacheConfig.class})
public class TestTag {

    /*
     * Architecture : WAS ---------> Infinispan (2 caches) - TagListTreeCache
     * (Using tries algorithm, store will be stored as a tree for searching) -
     * TagListCache (Store tags when client log in. Type is
     * ("username","SET Object(store)")
     *
     * Flow : Basic condition) Tag put ==> SYNC, TagList put ==> ASYNC, TagList
     * get ==>SYNC ex) ljhiyh = java, was, weblogic, jboss, websphere - Put
     * individual store list 1. A client log in DevPing. 2. Create SET Object.
     * ("username","SET Object(store)") : Set tagList = new HashSet(); 3. Get
     * connection with TagListCache : RemoteCache tagListCache =
     * GetCache("TagListCache"); 4. Put SET object with username as key :
     * tagListCache.put("ljhiyh",tagList);
     *
     * - Update TagListTree 1. After the job that put store to tagListCache, get
     * connection with TagListTreeCache : RemoteCache tagListTreeCache =
     * GetCache("TagListTreeCache"); 2. Get latest Tree and update then, put the
     * tree : tagListTree.put("tagListTree",TST object);
     *
     * TagListTreeCache 의 마지막 update시간이 10초가 지나면 or TagListCache의 사이즈가 마지막 사이즈보다
     * 10이 넘어가면 or TagListCache의 마지막 update시간이 10초가 지나면 TagListCache의 데이터를 가지고
     * 업데이트를 한다.
     *
     * TagListCache 의 사이즈가 마지막 사이즈와 같거나 작다면 or
     */
//    @Autowired
//    RemoteCacheManager remoteCacheManager;

    @Autowired
    TST<String> tagTree;

    @Autowired
    ITagCache ITagCache;

    @Test
    public void testRemoteCacheConnection() throws Exception {

//        // put,get
//        tagCache.put("a", "a");
//        assertEquals("a", tagCache.get("a"));
//
//        // update,get
//        tagCache.put("a", "b");
//        assertEquals("b", tagCache.get("a"));
//
//        // remove
//        tagCache.remove("a");
//        assertNull(tagCache.get("a"));
    }

    @Test
    public void testPutUserToTagTree() throws Exception {

        String UserName1 = "ljhiyh";
        String UserName2 = "jhouse";
        String UserName3 = "jooho";

        UserVo user1 = userLogin(UserName1);
        UserVo user2 = userLogin(UserName2);
        UserVo user3 = userLogin(UserName3);

        // Add 8 tags for user1 (ljhiyh)
        addTagToTree(user1, tagTree);
        assertEquals(8, tagTree.size());

        // Add 6 tags but 3tags are different from user1(ljhiyh)
        addTagToTree(user2, tagTree);
        assertEquals(11, tagTree.size());

        // Add 7 tags but 1tags are different from user1(ljhiyh),user2(jhouse)
        addTagToTree(user3, tagTree);
        assertEquals(12, tagTree.size());

        // Check user list for each store
        assertEquals(3, ((Set) ITagCache.get("java")).size());
        assertEquals(2, ((Set) ITagCache.get("jboss")).size());
        assertEquals(2, ((Set) ITagCache.get("레드")).size());

        // Check user name for each store
        assertEquals("[jooho, ljhiyh]",
                ((Set) ITagCache.get("jboss")).toString());

    }

    @SuppressWarnings("unchecked")
    private void addTagToTree(UserVo userVo, TST<String> tagTree) {

        Set<String> userListforTag;

        for (String key : userVo.getPersonalTagList()) {
            tagTree.put(key, key);
            userListforTag = (Set<String>) ITagCache.get(key);
            if (userListforTag == null) {
                userListforTag = new TreeSet<>();
            }
            // update this user to userList
            userListforTag.add(userVo.getUserId());
            // update userList for the store
            ITagCache.put(key, userListforTag);
        }

    }

    private UserVo userLogin(String userName) {
        Map<String, UserVo> testUserTagList = new HashMap<>();
        Set<String> tag1 = new HashSet<String>();
        tag1.add("java");
        tag1.add("jboss");
        tag1.add("weblogic");
        tag1.add("websphere");
        tag1.add("eap");
        tag1.add("wildfly");
        tag1.add("jbug");
        tag1.add("레드");

        Set<String> tag2 = new HashSet<String>();
        tag2.add("java");
        tag2.add("jbug");
        tag2.add("jdg");
        tag2.add("infinispan");
        tag2.add("algorithm");
        tag2.add("wildfly");

        Set<String> tag3 = new HashSet<String>();
        tag3.add("java");
        tag3.add("spring");
        tag3.add("jboss");
        tag3.add("jbug");
        tag3.add("algorithm");
        tag3.add("wildfly");
        tag3.add("레드");

        UserVo user1 = new UserVo.Builder()
                .userId("ljhiyh")
                .personalTagList(tag1)
                .build();

        UserVo user2 =  new UserVo.Builder()
                .userId("jhouse")
                .personalTagList(tag2)
                .build();

        UserVo user3 = new UserVo.Builder()
                .userId("jooho")
                .personalTagList(tag3)
                .build();

        testUserTagList.put("ljhiyh", user1);
        testUserTagList.put("jhouse", user2);
        testUserTagList.put("jooho", user3);

        return testUserTagList.get(userName);
    }

	/*
     * Prefix로 시작하는 단어 찾기
	 */

    @Test
    public void testTST_prefixMatch() throws Exception {
        Iterator<String> actualData = tagTree.prefixMatch("j").iterator();

        String[] expectedTestData = {"java", "jboss", "jbug", "jdg"};
        List<String> expectedData = Arrays.asList(expectedTestData);

        int index = 0;
        while (actualData.hasNext()) {
            assertThat(actualData.next(), is(expectedData.get(index++)));
        }

    }

    @Test
    public void testTST_prefixMatch_Korean() throws Exception {
        tagTree.put("레드","레드");
        tagTree.put("레이드","레이드");
        tagTree.put("레드라","레드라");

        Iterator<String> actualData = tagTree.prefixMatch("레").iterator();

        String[] expectedTestData = {"레드","레드라","레이드"};
        List<String> expectedData = Arrays.asList(expectedTestData);

        int index = 0;
        while (actualData.hasNext()) {
            String real = actualData.next();
            String expected = expectedData.get(index++);
            System.out.println("Real = " +real + " Expected : "+expected) ;
            System.out.println((0));
            assertThat(real, is(expected));
        }


    }


    @Test
    public void testRank() throws Exception {

    }

}
