package org.jbug.devping.configuration;

import org.jbug.devping.domain.DevPingDomains;
import org.jbug.devping.utils.ConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by nadal on 2014. 9. 15..
 */

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Import({DevPingRepositoryConfig.
        class})
@ComponentScan(basePackageClasses = {DevPingDomains.class,
        DevPingCacheConfig.class})
public class DevPingDomainApplicationContextConfiguration {
    // implements TransactionManagementConfigurer {

    @Autowired
    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ConfigurationProperties configurationProperties() {
        return new ConfigurationProperties(environment);
    }

    // @Qualifier("transactionManager")
    // @Autowired
    // private PlatformTransactionManager transactionManager;
    //
    // @Override
    // public PlatformTransactionManager annotationDrivenTransactionManager() {
    // return transactionManager;
    // }
}
