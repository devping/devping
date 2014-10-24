package org.jbug.devping.channel;

import org.jbug.devping.vo.PingToServerRequsetVo;
import org.jbug.devping.vo.PingToServerResponseVo;
import org.jbug.devping.websocket.ChannelManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by jhouse on 10/12/14.
 */
@Component
public class ChannelService {

    @Autowired
    ChannelManager cm;

    public PingToServerResponseVo ping(PingToServerRequsetVo pingToServerRequsetVo){
        //리턴 Vo 생성
        PingToServerResponseVo pingToServerResponseVo = new PingToServerResponseVo();
        //채널 ID 생성
        String channelId = cm.generateChannelId(pingToServerRequsetVo.getUserId());
        String result = "fail";
        pingToServerResponseVo.setFunc("pingToServer");
        pingToServerResponseVo.setChannelId(channelId);
        pingToServerResponseVo.setDate((new Date()).toString());

        List<String> selectedUserList = pingToServerRequsetVo.getUserIdsWithTag();
        PingClientRequestCmd pingClientRequestCmd;
        for(String pongUserId:selectedUserList) {
            pingClientRequestCmd = new PingClientRequestCmd(pingToServerRequsetVo,pongUserId,channelId);
            pingClientRequestCmd.execute();
        }
        result = "success";
        pingToServerResponseVo.setResult(result);

        return pingToServerResponseVo;
    }
}
