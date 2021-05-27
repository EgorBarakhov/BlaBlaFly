package ru.kpfu.itis.barakhov.blablafly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kpfu.itis.barakhov.blablafly.converters.FormattedStringToMillisConverter;
import ru.kpfu.itis.barakhov.blablafly.converters.StringToAircraftConverter;
import ru.kpfu.itis.barakhov.blablafly.converters.StringToCityConverter;
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
        registry.addConverter(formattedStringToMillisConverter());
        registry.addConverter(stringToAircraftConverter());
    }

    @Bean
    public StringToCityConverter citiesConverter() {
        return new StringToCityConverter();
    }

    @Bean
    public FormattedStringToMillisConverter formattedStringToMillisConverter() {
        return new FormattedStringToMillisConverter();
    }

    @Bean
    public StringToAircraftConverter stringToAircraftConverter() {
        return new StringToAircraftConverter();
    }

    @Bean
    public AuthenticatedInterceptor authenticatedInterceptor() {
        return new AuthenticatedInterceptor();
    }
}
