package com.liangchen.config;

import org.springframework.jmx.access.NotificationListenerRegistrar;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.security.auth.kerberos.DelegationPermission;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class FilterInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
       FilterRegistration.Dynamic rootFilter=servletContext.addFilter("rootFilter", DelegatingFilterProxy.class);
        rootFilter.addMappingForUrlPatterns(null,false,"/*");
//        FilterRegistration.Dynamic SecuredFilter=servletContext.addFilter("securedFilter", DelegatingFilterProxy.class);
//        rootFilter.addMappingForUrlPatterns(null,false,"/admin/*");
    }
}
