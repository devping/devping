package org.jbug.devping.store;

import org.jbug.devping.cache.IMsgCache;
import org.jbug.devping.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jhouse on 10/3/14.
 */
@Component
public class MessageStore {
    @Autowired
    private IMsgCache msgCache;

    public void checkIdIsUnique(String channelId, UserVo userVo) {

    }
}
