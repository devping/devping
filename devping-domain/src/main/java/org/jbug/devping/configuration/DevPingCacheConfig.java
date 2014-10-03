package org.jbug.devping.configuration;

import edu.princeton.cs.algs4.TST;
import org.jbug.devping.cache.infinispan.InfinispanTagCacheImpl;
import org.jbug.devping.cache.ITagCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = "org.jbug.devping")
public class DevPingCacheConfig {
    final static Logger logger = LoggerFactory.getLogger(DevPingCacheConfig.class);

    private TST<String> tagTree = null;
    private ITagCache tagCache = null;


    @Bean
    public TST<String> tagTree() {
        if (tagTree == null)
            return new TST<>();

        return tagTree;
    }

    @Bean
    public ITagCache tagCache() {
        if (tagTree == null)
            tagCache = new InfinispanTagCacheImpl();

        return tagCache;
    }


}
