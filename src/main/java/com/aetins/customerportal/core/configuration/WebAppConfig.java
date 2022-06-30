/*
 * package com.aetins.customerportal.core.configuration;
 * 
 * import java.util.HashMap; import java.util.Map;
 * 
 * import org.springframework.beans.factory.config.CustomScopeConfigurer; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.faces.mvc.JsfView; import
 * org.springframework.web.servlet.view.UrlBasedViewResolver;
 * 
 * import com.aetins.customerportal.core.utils.ViewScope;
 * 
 * @Configuration public class WebAppConfig {
 * 
 * @Bean public UrlBasedViewResolver faceletsViewResolver() {
 * UrlBasedViewResolver resolver = new UrlBasedViewResolver();
 * resolver.setViewClass(JsfView.class); resolver.setPrefix("/WEB-INF/");
 * resolver.setSuffix(".xhtml"); return resolver; }
 * 
 * @Bean public CustomScopeConfigurer customScope() { CustomScopeConfigurer
 * configurer = new CustomScopeConfigurer(); Map<String, Object> customScope =
 * new HashMap<>(); customScope.put("view", new ViewScope());
 * configurer.setScopes(customScope); return configurer; }
 * 
 * }
 */