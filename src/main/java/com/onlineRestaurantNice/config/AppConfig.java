package com.onlineRestaurantNice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * Created by Ник on 18.06.2018.
 */
@Configuration
@Import(PersistenceConfig.class)
@ComponentScan(basePackages = "com.onlineRestaurantNice",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class))
public class AppConfig {
}

