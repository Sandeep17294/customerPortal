package com.aetins.customerportal.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({@PropertySource("classpath:webservices/appSettingURL.properties"),@PropertySource("classpath:spring/dbconfig.properties"),@PropertySource("classpath:spring/config.properties")})
public class ApplicationPropertyConfig {

}
