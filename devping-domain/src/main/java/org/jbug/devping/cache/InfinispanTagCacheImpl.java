package org.jbug.devping.cache;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.commons.util.FileLookupFactory;
import org.jgroups.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class InfinispanTagCacheImpl implements TagCache {
    final static Logger logger = LoggerFactory.getLogger(InfinispanTagCacheImpl.class);
    private final String CONFIG_FILE = "hotrod.properties";
    private RemoteCacheManager remoteCacheManager;
	private RemoteCache<String, Set<String>> treeCache;

    public InfinispanTagCacheImpl() {
        if(remoteCacheManager == null)
            init();
    }

    private void init(){
        Configuration configuration = null;
        ConfigurationBuilder builder = new ConfigurationBuilder();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        builder.classLoader(cl);
        InputStream stream = FileLookupFactory.newInstance().lookupFile(CONFIG_FILE, cl);

        if (stream == null) {
            logger.error("Can't Found configFile=" + CONFIG_FILE);
        } else {
            try {
                Properties prop = new Properties();
                prop.load(stream);
                builder.withProperties(prop);
            } catch (IOException ioe) {
                Util.close(stream);
            }
        }

        configuration = builder.build();
        remoteCacheManager = new RemoteCacheManager(configuration);
        waitForConnectionReady();
        treeCache = remoteCacheManager.getCache("TagCache");

    }

    private void waitForConnectionReady() {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @SuppressWarnings("unchecked")
	@Override
	public Set<String> get(String key) {
		return (Set<String>) treeCache.get(key);
	}

	@Override
	public void put(String key, Set<String> userListforTag) {
		treeCache.put(key, userListforTag);
	}

    @Override
    public <T> void remove(T key) {
        treeCache.remove(key);
    }


}
