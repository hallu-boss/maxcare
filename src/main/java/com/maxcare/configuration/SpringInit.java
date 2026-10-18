package com.maxcare.configuration;

import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?> @Nullable [] getRootConfigClasses() {
        return new Class[]{SpringConfiguration.class, HibernatePersistenceConfiguration.class};
    }

    protected Class<?> @Nullable [] getServletConfigClasses() {
        return new Class[0];
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
