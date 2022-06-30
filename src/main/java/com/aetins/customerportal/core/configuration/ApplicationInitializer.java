/*
 * package com.aetins.customerportal.core.configuration;
 * 
 * import javax.servlet.ServletContext; import javax.servlet.ServletException;
 * 
 * import org.springframework.web.servlet.support.
 * AbstractAnnotationConfigDispatcherServletInitializer;
 * 
 * import com.aetins.customerportal.core.listeners.ResetInputAjaxActionListener;
 * 
 * public class ApplicationInitializer extends
 * AbstractAnnotationConfigDispatcherServletInitializer {
 * 
 * @Override protected Class<?>[] getRootConfigClasses() {
 * 
 * return new Class[] { ApplicationConfiguration.class }; }
 * 
 * @Override protected Class<?>[] getServletConfigClasses() { // TODO
 * Auto-generated method stub return null; }
 * 
 * @Override protected String[] getServletMappings() { // TODO Auto-generated
 * method stub return null; }
 * 
 * @Override public void onStartup(ServletContext servletContext) throws
 * ServletException {
 * 
 * // Use JSF view templates saved as *.xhtml, for use with Facelets
 * 
 * servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
 * 
 * // Enable special Facelets debug output during development
 * servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
 * 
 * // Causes Facelets to refresh templates during development
 * servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
 * 
 * // Declare Spring Security Facelets tag library
 * servletContext.setInitParameter("javax.faces.FACELETS_LIBRARIES",
 * "/WEB-INF/springsecurity.taglib.xml");
 * 
 * servletContext.addListener(ResetInputAjaxActionListener.class);
 * 
 * // Let the DispatcherServlet be registered super.onStartup(servletContext); }
 * 
 * }
 */