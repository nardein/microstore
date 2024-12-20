package com.microstore.configuration;

import com.microstore.model.ConfigApp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBean {

    @Bean
    public ConfigApp configurationBeans(){
        ConfigApp bean = new ConfigApp();
        bean.setAppName("Microstore");
        bean.setAppVersion("1.0.0");
        return bean;
    }

}
