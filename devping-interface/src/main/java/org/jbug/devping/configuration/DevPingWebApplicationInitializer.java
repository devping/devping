package org.jbug.devping.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.jbug.devping.configuration.*;

/**
 * Created by nadal on 2014. 9. 15..
 */

public class DevPingWebApplicationInitializer extends AbstractDevPingWebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        loadLogbackConfigListener(servletContext);

        loadRootApplicationContext(servletContext, DevPingDomainApplicationContextConfiguration.class);
        loadDefaultFilters(servletContext);
        addDispatcherServlet(servletContext, "webServlet", DevPingWebServletApplicationContextConfig.class, "/");
        addDispatcherServlet(servletContext, "apiServlet", DevPingApiServletApplicationContextConfig.class, "/api/*");
    }
}
