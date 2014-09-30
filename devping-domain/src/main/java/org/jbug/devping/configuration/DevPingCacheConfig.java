package org.jbug.devping.configuration;

import edu.princeton.cs.algs4.TST;
import org.jbug.devping.cache.InfinispanTagCacheImpl;
import org.jbug.devping.cache.TagCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages="org.jbug.devping")
public class DevPingCacheConfig {
    final static Logger logger = LoggerFactory.getLogger(DevPingCacheConfig.class);

    private TST<String> tagTree=null;
    private TagCache tagCache = null;


    @Bean
    public TST<String> tagTree() {
        if(tagTree == null)
            return new TST<>();

        return tagTree;
    }

    @Bean
    public TagCache tagCache() {
        if(tagTree == null)
            return new InfinispanTagCacheImpl();

        return tagCache;
    }


}
