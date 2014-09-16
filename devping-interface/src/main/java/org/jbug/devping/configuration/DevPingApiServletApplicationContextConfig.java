package org.jbug.devping.configuration;

import org.jbug.devping.interfaces.api.DevPingApis;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * Created by nadal on 2014. 9. 15..
 */
@Configuration
@ComponentScan(basePackageClasses = {DevPingApis.class})
public class DevPingApiServletApplicationContextConfig extends AbstractDevPingWebMvcConfig {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
    }

}