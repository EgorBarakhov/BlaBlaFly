package ru.kpfu.itis.barakhov.blablafly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kpfu.itis.barakhov.blablafly.converters.CitiesConverter;
import ru.kpfu.itis.barakhov.blablafly.handlers.AuthenticatedInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticatedInterceptor());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(citiesConverter());
    }

    @Bean
    public CitiesConverter citiesConverter() {
        return new CitiesConverter();
    }

    @Bean
    public AuthenticatedInterceptor authenticatedInterceptor() {
        return new AuthenticatedInterceptor();
    }
}
