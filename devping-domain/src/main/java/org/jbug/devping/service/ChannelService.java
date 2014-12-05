package org.jbug.devping.service;

import org.jbug.devping.channel.PingClientRequestCmd;
import org.jbug.devping.vo.PingToServerRequsetVo;
import org.jbug.devping.vo.PingToServerResponseVo;
import org.jbug.devping.websocket.ChannelManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by jhouse on 10/12/14.
 * 핑 / 퐁 등 채널관련 커맨드 호출
 */
@Component
public class ChannelService {

    @Autowired
    ChannelManager cm;

    public PingToServerResponseVo ping(PingToServerRequsetVo pingToServerRequsetVo){
        //핑 요청을 한 것에 대한 리턴 Vo 생성
        PingToServerResponseVo pingToServerResponseVo = new PingToServerResponseVo();
        //핑을 요청한 사람에서 채널 ID 생성해서 부여
        String channelId = cm.generateChannelId(pingToServerRequsetVo.getUserId());
        String result = "fail";

        pingToServerResponseVo.setFunc(pingToServerRequsetVo.getFunc()+"_response");
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
