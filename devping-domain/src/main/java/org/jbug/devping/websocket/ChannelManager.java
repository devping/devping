package org.jbug.devping.websocket;

import org.jbug.devping.utils.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by jhouse on 10/10/14.
 */
@Component
public class ChannelManager {
    private final static Logger logger = LoggerFactory.getLogger(WSSessionManager.class);

    private static final Map<String, TreeSet<String>> channelWithUserList = Collections.synchronizedMap(new HashMap<String, TreeSet<String>>());

    public String generateChannelId(String userId) {

        StringBuilder channelIdBuilder = new StringBuilder();
        channelIdBuilder.append(userId);
        channelIdBuilder.append("/");
        channelIdBuilder.append("chat");
        channelIdBuilder.append("/");
        channelIdBuilder.append(NumberUtil.randomInteger(0, 10000000));

        String channelId = channelIdBuilder.toString();

        return channelId;
    }

    public boolean enterChannel(String channelId, String userId ){
        boolean error = false;
        if(isChannelExist(channelId)){
            TreeSet<String> userList = channelWithUserList.get(channelId);
            userList.add(userId);
            channelWithUserList.put(channelId,userList);
        }else{
            error=true;
        }

        return error;
    }

    public boolean exitChannel(String channelId, String userId){
        boolean error = false;
        if(isChannelExist(channelId)){
            TreeSet<String> userList = channelWithUserList.get(channelId);
            userList.remove(userId);
            channelWithUserList.put(channelId,userList);
        }else{
            error=true;
        }
        return error;
    }

    private boolean isChannelExist(String channelId){
        return channelWithUserList.containsKey(channelId);
    }
}
