package org.jbug.devping.vo;

import java.util.HashMap;

/**
 * Created by jhouse on 10/7/14.
 */
public class CSVo {
    public enum FUNC {
        PING, PONG, TAG_SEARCH, TAG_USER_SEARCH
    }
    private HashMap<String,Object> CSVHashMap = new HashMap<>();


}
