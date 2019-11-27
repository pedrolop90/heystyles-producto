package com.heystyles.producto.api.config;

import com.heystyles.common.service.ConverterService;
import com.heystyles.common.service.ValidationService;
import com.heystyles.common.service.impl.ConverterServiceImpl;
import com.heystyles.common.service.impl.ValidationServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class GeneralConfig {
    @Bean
    public ConverterService converterService() {
        return new ConverterServiceImpl();
    }

    @Bean
    public ValidationService validationService() {
        return new ValidationServiceImpl();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "POST", "DELETE");
            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addRedirectViewController("/", "/swagger-ui.html");
            }

            @Override
            public Validator getValidator() {
                LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
                bean.setValidationMessageSource(messageSource());
                return bean;
            }
        };
    }

    @Bean
    public LocaleResolver localeResolver() {
        Locale defaultLocale = new Locale("es", "CO");
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(defaultLocale);
        Locale.setDefault(defaultLocale);
        return localeResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}

