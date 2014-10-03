package org.jbug.devping.store;

import edu.princeton.cs.algs4.TST;
import org.jbug.devping.cache.ITagCache;
import org.jbug.devping.configuration.DevPingCacheConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DevPingCacheConfig.class })
public class TestCacheConfig {


	@Autowired
	TST<String> tagTree;

    @Autowired
    ITagCache ITagCache;

	@Test
	public void testTagTree() {
		assertNotNull(tagTree);
	}

    @Test
    public void testCache() {
        assertNotNull(ITagCache);
    }

}
