package org.jbug.devping.tag;

import org.jbug.devping.configuration.DevPingCacheConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by jhouse on 9/27/14.
 */
@Ignore
    @RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the static inner ContextConfiguration class
    @ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes = {DevPingCacheConfig.class})
    public class TestSample {

        @Configuration
        static class ContextConfiguration {

            // this bean will be injected into the OrderServiceTest class
//            @Bean
//            public OrderService orderService() {
//                OrderService orderService = new OrderServiceImpl();
//                // set properties, etc.
//                return orderService;
//            }
//            @Bean
//            public TagCache InfinispanTagCache() {
//                TagCache InfinispanTagCacheImpl = new InfinispanTagCacheImpl();
//                // set properties, etc.
//                return InfinispanTagCacheImpl;
//
//            }
        }

//        @Autowired()
//        private TagCache InfinispanTagCache;


        @Test
        public void testOrderService() {
//            InfinispanTagCache.put("a","a");
//            Assert.assertEquals("a",InfinispanTagCache.get("a"));
        }
    }