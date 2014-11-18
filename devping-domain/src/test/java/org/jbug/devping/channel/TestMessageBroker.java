package org.jbug.devping.channel;

import org.jbug.devping.domain.UserVo;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jhouse on 10/3/14.
 */
public class TestMessageBroker {
    MessageBroker mb = new MessageBroker();

    @Test
    public void testGenerateChannelID() {
        UserVo uv = UserVo.builder()
        .email("ljhiyh").build();
        assertNotNull(mb.generateChannelId(uv));

    }

}
