package org.jbug.devping.cache.infinispan;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.tree.TreeCache;
import org.jbug.devping.cache.IMsgCache;

/**
 * Created by jhouse on 10/3/14.
 */
public class InfinispanMsgCacheImpl extends AbstractInfinispanCache implements IMsgCache {
    private RemoteCache msgRemoteCache = null;

    TreeCache treeCache = null;
    public InfinispanMsgCacheImpl(){
        msgRemoteCache = remoteCacheManager.getCache("MsgCache");
    }

    @Override
    public void createFQN(String fqn) {

    }
}
