package org.jbug.devping.configuration;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * Created by nadal on 2014. 9. 15..
 */



/**
* {@link org.springframework.web.servlet.DispatcherServlet DispatcherServlet}은 이 클래스를 상속한 설정을 통해 만든다.
*
* TODO ExcelView와 JsonView 기본으로 넣기.
*/
@Configuration
@Import(PropertySourcesPlaceHolderConfig.class)
public abstract class AbstractDevPingWebMvcConfig extends WebMvcConfigurationSupport {

    public static final String RESOURCE_PATH_BASE = "/resources/";
    public static final String DEFAULT_RESOURCES_PATH = RESOURCE_PATH_BASE + "**";
    public static final String DEFAULT_RESOURCE_LOCATION = "/resources/";

    public static final String ERROR_RESOURCE_PATH = "/error-resources/**";
    public static final String ERROR_RESOURCE_LOCATION = "/WEB-INF/error-resources/";

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 기본적으로 웹 정적 리소스는 <code>/WEB-INF/resources/</code> 아래에만 넣고, <code>/resouces/**</code> 로 매핑한다.
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler(DEFAULT_RESOURCES_PATH).addResourceLocations(DEFAULT_RESOURCE_LOCATION);
//        addErrorResourceHandler(registry);
//        super.addResourceHandlers(registry);
    }

    /**
     * Resource handler for Error page.
     */
    private void addErrorResourceHandler(ResourceHandlerRegistry registry) {
        ResourceHandlerRegistration resourceHandlerRegistration = registry.addResourceHandler(ERROR_RESOURCE_PATH);
        resourceHandlerRegistration.addResourceLocations(ERROR_RESOURCE_LOCATION);
        resourceHandlerRegistration.setCachePeriod(0);
    }

    /**
     * 인터셉터를 추가하되, /resources/** 는 제외하고 추가한다.
     */
    protected InterceptorRegistration addInterceptorExceptResources(InterceptorRegistry registry, HandlerInterceptor interceptor,
                                                                    String... patterns) {
        Preconditions.checkNotNull(patterns, "패턴을 지정해주세요.");

        InterceptorRegistration registration = registry.addInterceptor(interceptor);
        registration.addPathPatterns(patterns);
        registration.excludePathPatterns(DEFAULT_RESOURCES_PATH);
        return registration;
    }
}