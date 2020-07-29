package com.packt.webstore.util;

import com.packt.webstore.validator.ProductValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public ProductValidator productValidator() {
        return new ProductValidator();
    }

    @Bean
    public UrlBasedViewResolver viewResolver() {
        UrlBasedViewResolver resolver
                = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
}
