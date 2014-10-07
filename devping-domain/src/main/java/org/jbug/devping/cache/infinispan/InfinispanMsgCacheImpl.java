package org.jbug.devping.cache.infinispan;

import org.jbug.devping.cache.IMsgCache;
import org.springframework.stereotype.Component;

/**
 * Created by jhouse on 10/3/14.
 */
@Component
public class InfinispanMsgCacheImpl extends AbstractInfinispanCache implements IMsgCache {
    @Override
    public void createFQN(String fqn) {

    }
}
