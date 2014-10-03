package org.jbug.devping.msg;

import org.jbug.devping.vo.UserVo;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jhouse on 10/3/14.
 */
public class TestMessageBroker {
    MessageBroker mb = new MessageBroker();

    @Test
    public void testGenerateChannelID() {
        UserVo uv = new UserVo();
        uv.setName("ljhiyh");
        assertNotNull(mb.generateChannelId(uv));

    }

}
