/*
 * package com.aetins.customerportal.core.configuration;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.web.servlet.ViewResolver; import
 * org.thymeleaf.spring4.SpringTemplateEngine; import
 * org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver; import
 * org.thymeleaf.spring4.view.ThymeleafViewResolver; import
 * org.thymeleaf.templateresolver.ITemplateResolver;
 * 
 *//**
	 * @author avinash
	 *
	 *//*
		 * @Configuration public class ThymeleafConfiguration {
		 * 
		 * private static Logger logger =
		 * LoggerFactory.getLogger(ThymeleafConfiguration.class);
		 * 
		 * @Bean public ITemplateResolver templateResolver() {
		 * SpringResourceTemplateResolver templateResolver = new
		 * SpringResourceTemplateResolver(); templateResolver.setPrefix(
		 * "/registration/" ); templateResolver.setSuffix( ".xhtml" );
		 * //templateResolver.setTemplateMode( "HTML5" );
		 * logger.info("----Thymeleaf Configuration----"); return templateResolver; }
		 * 
		 * @Bean public SpringTemplateEngine templateEngine() { SpringTemplateEngine
		 * templateEngine = new SpringTemplateEngine();
		 * templateEngine.setTemplateResolver( templateResolver() );
		 * 
		 * return templateEngine; }
		 * 
		 * @Bean public ViewResolver viewResolver() { ThymeleafViewResolver viewResolver
		 * = new ThymeleafViewResolver(); viewResolver.setTemplateEngine(
		 * templateEngine() ); viewResolver.setOrder( 1 ); return viewResolver; } }
		 */