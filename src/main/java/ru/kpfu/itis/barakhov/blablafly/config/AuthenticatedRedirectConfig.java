package ru.kpfu.itis.barakhov.blablafly.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kpfu.itis.barakhov.blablafly.handlers.AuthenticatedPagesInterceptor;

@Configuration
public class AuthenticatedRedirectConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticatedPagesInterceptor());
    }

}
