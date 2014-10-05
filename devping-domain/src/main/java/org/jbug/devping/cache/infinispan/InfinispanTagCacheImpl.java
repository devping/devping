package org.jbug.devping.cache.infinispan;

import org.infinispan.client.hotrod.RemoteCache;
import org.jbug.devping.cache.ITagCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class InfinispanTagCacheImpl extends AbstractInfinispanCache implements ITagCache {
    final static Logger logger = LoggerFactory.getLogger(InfinispanTagCacheImpl.class);
	private RemoteCache<String, Set<String>> tagCache;

    public InfinispanTagCacheImpl() {
        tagCache = remoteCacheManager.getCache("TagCache");
    }
    @SuppressWarnings("unchecked")
	@Override
	public Set<String> get(String key) {
		return (Set<String>) tagCache.get(key);
	}

	@Override
	public void put(String key, Set<String> userListForTag) {
		tagCache.put(key, userListForTag);
	}

    @Override
    public void remove(String key) {
        tagCache.remove(key);
    }


}
