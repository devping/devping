package org.jbug.devping.cache.infinispan;

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

/**
 * Created by jhouse on 10/3/14.
 */
public abstract class AbstractInfinispanCache {
    final static Logger logger = LoggerFactory.getLogger(AbstractInfinispanCache.class);
    public boolean isInit = false;
    protected String CONFIG_FILE = "hotrod-server.properties";
    protected RemoteCacheManager remoteCacheManager;
    protected String cacheName = null;

    public AbstractInfinispanCache() {
        String configFile = System.getProperty("infinispan.hotrod");
        if (configFile != null)
            CONFIG_FILE = configFile;

        if (remoteCacheManager == null)
            init();

    }

    public boolean isInitialized() {
        return isInit;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    private void init() {
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
        isInit = true;
    }

    private void waitForConnectionReady() {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}
