package org.jbug.devping.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jbug.devping.interfaces.api.DevPingApis;
import org.jbug.devping.interfaces.web.DevPingWebs;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nadal on 2014. 9. 15..
 */
@Configuration
@ComponentScan(basePackages = {"org.jbug.devping"},basePackageClasses = {DevPingWebs.class, DevPingApis.class})

public class DevPingWebServletApplicationContextConfig extends AbstractDevPingWebMvcConfig {
    public static final String UPLOAD_DIR = "/devping/service/webroot/image/temp";
    public static final int MAX_UPLOAD_SIZE = 30000000;

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
        messageResource.setBasenames("classpath:devping/messages/message");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public MappingJackson2JsonView mappingJackson2JsonView() {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        jsonView.setExtractValueFromSingleKeyModel(true);
        jsonView.setContentType("text/plain");

        return jsonView;
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.addDefaultHttpMessageConverters(converters);
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ObjectMapper objectMapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
                ((MappingJackson2HttpMessageConverter) converter).setObjectMapper(objectMapper);
            }

        }
    }

//    @Bean
//    public CommonsMultipartResolver multipartResolver() throws IOException {
//        FileSystemResource fileSystemResource = new FileSystemResource(UPLOAD_DIR);
//        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//        commonsMultipartResolver.setUploadTempDir(fileSystemResource);
//        commonsMultipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE);
//        return commonsMultipartResolver;
//    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
                .useJaf(false)
                .ignoreAcceptHeader(true)
                .mediaType("html", MediaType.TEXT_HTML)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .defaultContentType(MediaType.TEXT_HTML);
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver(
            ContentNegotiationManager manager) {

        List< ViewResolver > resolvers = new ArrayList< ViewResolver >();

        InternalResourceViewResolver r1 = new InternalResourceViewResolver();
        r1.setPrefix("/WEB-INF/views/");
        r1.setSuffix(".jsp");
        r1.setViewClass(JstlView.class);
        resolvers.add(r1);

        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setViewResolvers(resolvers);
        resolver.setContentNegotiationManager(manager);
        return resolver;

    }
}
