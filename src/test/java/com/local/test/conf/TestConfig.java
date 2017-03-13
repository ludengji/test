package com.local.test.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by meisei on 2017/3/10.
 */
@Configuration
@PropertySource("classpath:application-test.properties")
@ComponentScan("com.local.test.conf")
public class TestConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() throws Exception {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
