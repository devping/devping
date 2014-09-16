package org.jbug.devping.configuration;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;
import com.google.common.base.Objects;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;

/**
 * Created by nadal on 2014. 9. 15..
 *
 * 먼저 loadRootApplicationContext를 실행하고,
 * 후에 loadDefaultFilters를 실행한 다음,
 * addDispatcherServlet으로 원하는 Spring Dispatcher Servlet을 추가한다.
 *
 */
public abstract class AbstractDevPingWebApplicationInitializer implements WebApplicationInitializer {

    public static final String LOGBACK_CONFIG_LOCATION_PARAM_NAME = "logbackConfigLocation";
    public static final String LOGBACK_CONFIG_LOCATION_PARAM_VALUE = "WEB-INF/spring/logback/logback-${" + ACTIVE_PROFILES_PROPERTY_NAME
            + "}.groovy";
    public static final String LOGBACK_OVERRIDE_PROPERTY_NAME = "logback.override";

    /** Root Application Context 적재시 Initializer Class */
//    public static final Class<?> APPLICATION_CONTEXT_INITIALIZER_CLASS = InterfacesConfigurationPropertiesApplicationContextInitializer.class;

    /**
     * Logback 설정을 프로필에 따라 차등 적재한다.
     * logback.override 는 classpath: 로 시작하면 classpath:를 그대로 두고 그렇지 않으면 강제로 file:을 붙인다.
     */
    protected void loadLogbackConfigListener(ServletContext servletContext) {
        String logbackOverride = System.getProperty(LOGBACK_OVERRIDE_PROPERTY_NAME);

        if (logbackOverride != null && !logbackOverride.startsWith("classpath:")) {
            logbackOverride = "file:" + logbackOverride;
        }

        String logbackConfig = Objects.firstNonNull(logbackOverride, LOGBACK_CONFIG_LOCATION_PARAM_VALUE);

        servletContext.setInitParameter(LOGBACK_CONFIG_LOCATION_PARAM_NAME, logbackConfig);
        servletContext.addListener(LogbackConfigListener.class);
    }

    /**
     * Spring Root Application Context를 적재한다.
     *
     * @param servletContext servletContext
     * @param configClasses    Root Application Context Config classes
     * @return Spring Root Application Context
     */
    protected ApplicationContext loadRootApplicationContext(ServletContext servletContext, Class<?>... configClasses) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(configClasses);

        servletContext.addListener(new ContextLoaderListener(rootContext));

//        servletContext.setInitParameter(ContextLoader.CONTEXT_INITIALIZER_CLASSES_PARAM, APPLICATION_CONTEXT_INITIALIZER_CLASS.getName());

        return rootContext;
    }

    /**
     * 모든 프로젝트의 기본 필터를 적재한다.
     *
     * @param servletContext servletContext
     */
    protected void loadDefaultFilters(ServletContext servletContext) {
        addEncodingFilter(servletContext);
    }

    /** Character Encoding Filter */
    protected void addEncodingFilter(ServletContext servletContext) {
        FilterRegistration encodingFilter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), true, "/*");
    }


    /**
     * Spring {@link org.springframework.web.servlet.DispatcherServlet DispatcherServlet} 추가
     *
     * @param servletContext servletContext
     * @param servletName 서블릿 이름. 기본은 'webServlet'
     * @param servletContextConfigClass Spring Servlet Application Context Config class
     * @param mappings Servlet Mappings
     * @return Servlet 객체
     */
    protected ServletRegistration.Dynamic addDispatcherServlet(ServletContext servletContext, String servletName, Class<?> servletContextConfigClass,
                                                               String... mappings) {
        return addDispatcherServlet(servletContext, servletName, new Class<?>[] {servletContextConfigClass}, mappings);
    }

    /**
     * Spring {@link org.springframework.web.servlet.DispatcherServlet DispatcherServlet} 추가
     *
     * @param servletContext servletContext
     * @param servletName 서블릿 이름. 기본은 'webServlet'
     * @param servletContextConfigClasses Spring Servlet Application Context Config classes
     * @param mappings Servlet Mappings
     * @return Servlet 객체
     */
    protected ServletRegistration.Dynamic addDispatcherServlet(ServletContext servletContext, String servletName,
                                                               Class<?>[] servletContextConfigClasses, String... mappings) {
        Assert.notNull(servletName, "servletName을 지정하세요.");
        Assert.notEmpty(servletContextConfigClasses, "Servlet을 위한 Spring ApplicationContext Config Class들을 지정하세요.");
        Assert.notEmpty(mappings, "Servlet 매핑을 지정하세요.");

        AnnotationConfigWebApplicationContext servletApplicationContext = new AnnotationConfigWebApplicationContext();
        servletApplicationContext.register(servletContextConfigClasses);

        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet(servletName, new DispatcherServlet(servletApplicationContext));

        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping(mappings);

        return dispatcherServlet;
    }

}