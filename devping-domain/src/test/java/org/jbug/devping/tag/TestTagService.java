package org.jbug.devping.tag;

import org.jbug.devping.configuration.DevPingCacheConfig;
import org.jbug.devping.configuration.DevPingDomainApplicationContextConfiguration;
import org.jbug.devping.vo.UserVo;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Ignore("Don't know how to execute Component-scan")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DevPingDomainApplicationContextConfiguration.class, DevPingCacheConfig.class})
public class TestTagService {
//
//    @Autowired
//    RemoteCacheManager remoteCacheManager;
//
//	@Autowired
//    TagService tagService;
//    @Autowired TST<String> tagTree;
//
//    //This is hack for test ..To-do  SHOULD BE CHANGED
//    RemoteCache tagCache;
//
//    @Before
//    public void before() throws Exception {
//        tagCache = remoteCacheManager.getCache("TagCache");
//    }
//
//
//    /*
//	 * 로그인 할때 - Tag 추가 - User 추가 - Rank 추가
//	 */
//	@Test
//	public void LoginUpdateTagService() throws Exception {
//
//		String UserName1 = "ljhiyh";
//		String UserName2 = "jhouse";
//		String UserName3 = "jooho";
//
//		UserVo user1 = userLogin(UserName1);
//		UserVo user2 = userLogin(UserName2);
//		UserVo user3 = userLogin(UserName3);
//
//		// Add 8 tags for user1 (ljhiyh)
//		tagService.LoginUpdateTagService(user1);
//		assertEquals(8, tagTree.size());
//
//		// Add 6 tags but 3tags are different from user1(ljhiyh)
//		tagService.LoginUpdateTagService(user2);
//		assertEquals(11, tagTree.size());
//
//		// Add 7 tags but 1tags are different from
//		// user1(ljhiyh),user2(jhouse)
//		tagService.LoginUpdateTagService(user3);
//		assertEquals(12, tagTree.size());
//
//		// Check user list for each tag
//		assertEquals(3, ((Set) tagCache.get("java")).size());
//		assertEquals(2, ((Set) tagCache.get("jboss")).size());
//		assertEquals(2, ((Set) tagCache.get("레드")).size());
//
//		// Check user name for each tag
//		assertEquals("[jooho, ljhiyh]", ((Set) tagCache.get("jboss")).toString());
//
//	}
//
//	private UserVo userLogin(String userName) {
//		Map<String, UserVo> testUserTagList = new HashMap<>();
//		Set<String> tag1 = new HashSet<String>();
//		tag1.add("java");
//		tag1.add("jboss");
//		tag1.add("weblogic");
//		tag1.add("websphere");
//		tag1.add("eap");
//		tag1.add("wildfly");
//		tag1.add("jbug");
//		tag1.add("레드");
//
//		Set<String> tag2 = new HashSet<String>();
//		tag2.add("java");
//		tag2.add("jbug");
//		tag2.add("jdg");
//		tag2.add("infinispan");
//		tag2.add("algorithm");
//		tag2.add("wildfly");
//
//		Set<String> tag3 = new HashSet<String>();
//		tag3.add("java");
//		tag3.add("spring");
//		tag3.add("jboss");
//		tag3.add("jbug");
//		tag3.add("algorithm");
//		tag3.add("wildfly");
//		tag3.add("레드");
//
//		UserVo user1 = new UserVo();
//		user1.setName("ljhiyh");
//		user1.setPersonalTagList(tag1);
//
//		UserVo user2 = new UserVo();
//		user2.setName("jhouse");
//		user2.setPersonalTagList(tag2);
//
//		UserVo user3 = new UserVo();
//		user3.setName("jooho");
//		user3.setPersonalTagList(tag3);
//
//		testUserTagList.put("ljhiyh", user1);
//		testUserTagList.put("jhouse", user2);
//		testUserTagList.put("jooho", user3);
//
//		return testUserTagList.get(userName);
//	}

//	public void TestLoginTagService(UserVo userVo) {
//
//		tagService.LoginUpdateTagService(userVo);
//
//	}

	/*
	 * 로그아웃 할때 - User 삭제 - Rank 삭제
	 */
	public void LogOutService(UserVo userVo) {

	}

	/*
	 * Tag Search with prefix - 같은 글자로 시작되는 tag 리턴. - 랭크순으로 5개의 Tag list 리턴.
	 */
	public Set<String> TagPrefixSearchService(String character) {
		return new LinkedHashSet<>();
	}

	/*
	 * 해당 Tag를 가지고 있는 User List - 500명 이상이면 랜덤하게 500명만 리턴.
	 */
	public Set<String> UserListWithTag(String tag) {

		return new HashSet<String>();
	}

}
