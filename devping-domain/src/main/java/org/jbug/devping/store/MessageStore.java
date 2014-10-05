package org.jbug.devping.store;

import org.jbug.devping.cache.IMsgCache;
import org.jbug.devping.vo.UserVo;

/**
 * Created by jhouse on 10/3/14.
 */
//@Component
public class MessageStore {
//    @Autowired
    private IMsgCache msgCache;

    public void checkIdIsUnique(String channelId, UserVo userVo) {

    }
}
